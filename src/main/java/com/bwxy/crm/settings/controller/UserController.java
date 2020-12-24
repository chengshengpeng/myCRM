package com.bwxy.crm.settings.controller;

import com.bwxy.crm.base.constants.CrmConstants;
import com.bwxy.crm.base.exception.CrmException;
import com.bwxy.crm.settings.bean.User;
import com.bwxy.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.settings.controller
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 16:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(User user, Model model, HttpServletRequest request){
        try{
            //获取客户端ip地址
            String ip = request.getRemoteAddr();
            //先将获取的客户端IP地址传到user中，让user带着客户端的IP地址去service里面进行校验
            user.setAllowIps(ip);
            user = userService.login(user);
            //用户登录成功，将登录信息保存到session中
            request.getSession().setAttribute(CrmConstants.LOGIN_USER,user);
            //跳转到后台首页
            return "index";
        }catch (CrmException e){
            //用户名回显
            model.addAttribute("loginAct",user.getLoginAct());
            model.addAttribute("mess",e.getMessage());
        }
        return "../../login";
    }
    //退出功能
    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute(CrmConstants.LOGIN_USER);
        return "../../login";
    }
    //异步查询所有用户信息
    @RequestMapping("/queryAllUser")
    @ResponseBody
    public List<User> queryAllUser(){

        return userService.queryAllUser();
    }
}
