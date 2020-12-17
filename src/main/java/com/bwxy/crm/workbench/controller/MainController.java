package com.bwxy.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 19:53
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class MainController {
    @RequestMapping("/toMainIndex")
    public String toMainIndex(){
        return "/main/index";
    }

}
