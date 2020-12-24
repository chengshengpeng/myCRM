package com.bwxy.crm.settings.service;

import com.bwxy.crm.settings.bean.User;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.settings.service
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 16:55
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface UserService {
    User login(User user);

    List<User> queryAllUser();

    /*List<User> queryAllUser();*/
}
