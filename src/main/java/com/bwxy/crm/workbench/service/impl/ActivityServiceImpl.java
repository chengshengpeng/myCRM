package com.bwxy.crm.workbench.service.impl;

import com.bwxy.crm.base.constants.CrmExceptionEnum;
import com.bwxy.crm.base.exception.CrmException;
import com.bwxy.crm.base.util.DateTimeUtil;
import com.bwxy.crm.base.util.UUIDUtil;
import com.bwxy.crm.settings.bean.User;
import com.bwxy.crm.settings.mapper.UserMapper;
import com.bwxy.crm.workbench.bean.Activity;
import com.bwxy.crm.workbench.bean.ActivityQueryVo;
import com.bwxy.crm.workbench.mapper.ActivityMapper;
import com.bwxy.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/18 21:46
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Activity> listAll(ActivityQueryVo activityQueryVo) {
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if (activityQueryVo.getName() != null && activityQueryVo.getName() != "" ) {
            criteria.andLike("name","%" + activityQueryVo.getName() + "%");
        }
        //owen条件查询：由于市场活动表中的owen字段中是一串数字，不方便输入，因此需要输入的是user表中的name值
        //所以在查询的时候，先去user表中查询出id主键，根据id主键关联查询市场活动表中的owner
        if (activityQueryVo.getOwner() != null && activityQueryVo.getOwner() != "" ) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + activityQueryVo.getOwner() + "%");
            List<User> users = userMapper.selectByExample(example1);
            //在根据查询出来的用户的主键，关联查询市场活动的信息
            for (User user : users) {
                criteria.andEqualTo("owner",user.getId());
            }
        }
        //如果开始时间不为null，并且不等于""
        if (activityQueryVo.getStartDate() != null && activityQueryVo.getStartDate() !="") {
            criteria.andGreaterThanOrEqualTo("startDate",activityQueryVo.getStartDate());
        }
        if (activityQueryVo.getEndDate() != null && activityQueryVo.getEndDate() !="") {
            criteria.andLessThan("endDate",activityQueryVo.getEndDate());
        }

        //查询出所有的市场活动
        List<Activity> activities = activityMapper.selectByExample(example);
        //因为页面上显示的是所有者的名字，但owner是外键
        for (Activity activity : activities) {
            //先查询出所有的owner
            String owner = activity.getOwner();
            //根据owner外键查询出用户表中的用户
            User user = userMapper.selectByPrimaryKey(owner);
            //取出用户的名字，设置到市场活动的owner中
            activity.setOwner(user.getName());
        }
        return activities;
    }

    @Override
    public void addOrUpdateActivity(Activity activity) {
        int count=0;
        //区分是创建还是修改操作
        if (activity.getId() ==null){
            //创建
            //设置市场活动的id
            activity.setId(UUIDUtil.getUUID());
            //获取创建时间，添加到市场活动对象中
            activity.setCreateTime(DateTimeUtil.getSysTime());
            //在创建市场活动时，需要将修改人置空
            activity.setEditBy(null);
            //调用mapper中的方法
            count = activityMapper.insertSelective(activity);
            if (count == 0) {
                //如果市场活动创建失败，抛出异常，添加失败
                throw new CrmException(CrmExceptionEnum.ACTIVITY_CREATE_EXCEPTION);
            }
        }else {
            //修改
            //设置一个修改时间
            activity.setEditTime(DateTimeUtil.getSysTime());
            count = activityMapper.updateByPrimaryKeySelective(activity);
            if (count==0) {
                throw new CrmException(CrmExceptionEnum.ACTIVITY_EDIT_EXCEPTION);
            }


        }


    }

    @Override
    public Activity queryById(String id) {
        return activityMapper.selectByPrimaryKey(id);
    }
}
