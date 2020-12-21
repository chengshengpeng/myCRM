package com.bwxy.crm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.base.controller
 * @Description: 专门用来跳转视图的
 * @Author: 程生鹏
 * @CreateDate: 2020/12/18 21:20
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ViewController {
    //跳转所有视图
    @RequestMapping({"/toView/{modelView}/{view}","/toView/{view}"})
    public String toView(@PathVariable(value = "modelView",required = false) String modelView,
                         @PathVariable(value = "view") String view){
        if (modelView != null) {
            return modelView + File.separator + view;
        }else{
            return view;
        }
    }
}
