package com.bwxy.crm.workbench.controller;

import com.bwxy.crm.base.bean.ResultVo;
import com.bwxy.crm.base.constants.CrmConstants;
import com.bwxy.crm.base.exception.CrmException;
import com.bwxy.crm.settings.bean.User;
import com.bwxy.crm.workbench.bean.Activity;
import com.bwxy.crm.workbench.bean.ActivityQueryVo;
import com.bwxy.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/18 21:39
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    //每页显示3条
    //private int pageSize=1;


    @RequestMapping("/workbench/activity/list")
    @ResponseBody
    public ResultVo list(@RequestParam(defaultValue = "1") int page, int pageSize, ActivityQueryVo activityQueryVo){
        //等同于limit a,b
        PageHelper.startPage(page,pageSize);
        //查询出所有市场活动信息
        List<Activity> activities = activityService.listAll(activityQueryVo);
        //pageInfo:分页插件的对象，其中包含了上一页，下一页等等的关于分页的参数信息，此时将查询出来的所有市场活动信息也包含到分页插件的对象pageInfo中
        PageInfo<Activity> pageInfo = new PageInfo<>(activities);
        //创建结果集对象，将包含分页信息的对象pageInfo传给结果集对象，此时在创建结果集对象的时候，它里面的属性已经被初始化了
        ResultVo<Activity> resultVo = new ResultVo<Activity>();
        resultVo.setPageInfo(pageInfo);
        //将这个结果集对象返回给前台
        return resultVo;
    }
   /* //创建市场活动
    @RequestMapping("/workbench/activity/createActivity")

    public String createActivity(Activity activity, Model model, HttpSession session){
        try{
            //获取当前登录用户
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            //创建人
            activity.setCreateBy(user.getName());
        }catch (CrmException e){
            model.addAttribute("mess",e.getMessage());
        }

        return "index";
    }*/
 /*//创建市场活动
    @RequestMapping("/workbench/activity/createActivity")
    @ResponseBody
    public ResultVo createActivity(Activity activity, HttpSession session){
        ResultVo resultVo =null;
        try{

            //获取当前登录用户
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            //创建人
            activity.setCreateBy(user.getName());
            activityService.addActivity(activity);
             resultVo = new ResultVo(true,"添加市场活动成功！");
        }catch (CrmException e){
            resultVo = new ResultVo(false,e.getMessage());
        }
        return resultVo;
    }*/
    //将创建市场活动和修改市场活动整合在一起
    @RequestMapping("/workbench/activity/createOrUpdateActivity")
    @ResponseBody
    public ResultVo createOrUpdateActivity(Activity activity, HttpSession session){
        ResultVo resultVo =null;
        try{

            //获取当前登录用户
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            //创建人
            activity.setCreateBy(user.getName());
            //添加一个修改人
            activity.setEditBy(user.getName());
            //在调用方法之前先判断是创建还是修改操作
            String id = activity.getId();
            activityService.addOrUpdateActivity(activity);
            if (id==null) {
                //如果id为空，表示为添加操作
                resultVo = new ResultVo(true,"添加市场活动成功！");
            }else {
                resultVo = new ResultVo(true,"修改市场活动成功！");
            }
        }catch (CrmException e){
            resultVo = new ResultVo(false,e.getMessage());
        }
        return resultVo;
    }
//通过选中的市场活动的id号查询
    @RequestMapping("/workbench/activity/queryById")
    @ResponseBody
    public Activity queryById(String id){
        Activity activity = activityService.queryById(id);
        return activity;
    }

}
