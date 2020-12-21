package com.bwxy.crm.settings.service.impl;

import com.bwxy.crm.base.constants.CrmExceptionEnum;
import com.bwxy.crm.base.exception.CrmException;
import com.bwxy.crm.base.util.DateTimeUtil;
import com.bwxy.crm.base.util.MD5Util;
import com.bwxy.crm.settings.bean.User;
import com.bwxy.crm.settings.mapper.UserMapper;
import com.bwxy.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.settings.service.impl
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 16:55
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        //登录验证之前先将密码加密：取出用户密码调用加密的方法
        String loginPass = MD5Util.getMD5(user.getLoginPwd());
        user.setLoginPwd(loginPass);
        //在查询之前，要记得把一开始放入user中的客户端ip地址取出来，因为在查询数据库的时候，user被重新赋值了
        String address = user.getAllowIps();
        //取出之后一定记得把一开始放入user对象中的客户端ip地址给置空
        user.setAllowIps(null);
        //在进行查询的时候，此时的user中包含了一定数量的ip地址
         user = userMapper.selectOne(user);
        if (user == null) {
            //用户名或密码错误
            throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_EXCEPTION);
        }else{
            /*
                比较失效日期：
                    如果两个日期的字符串比较大小，使用compareTo
                    如果返回值<0， t1<t2
                    如果返回值=0， t1==t2
                    如果返回值>0， t1>t2
            * */
            //如果用户的失效时间小于当前系统时间，证明该用户已失效
            if (user.getExpireTime().compareTo(DateTimeUtil.getSysTime()) <0) {
                throw new CrmException(CrmExceptionEnum.LOGIN_EXPIRE_EXCEPTION);
            }
            //判断该用户是否被锁定
            if ("0".equals(user.getLockState())) {
                throw new CrmException(CrmExceptionEnum.LOGIN_LOCK_EXCEPTION);
            }
            //判断ip地址是否合法
            //取出在查询的时user中的ip地址，判断客户端的IP地址是否包含在这些地址中，如果包含，说明客户端的IP地址是合法的,如果包含说明此IP地址非法
            if (!user.getAllowIps().contains(address)) {
                throw new CrmException(CrmExceptionEnum.LOGIN_IP_EXCEPTION);
            }

        }
        //登录用户的所有条件都满足了
        return user;
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }
}
