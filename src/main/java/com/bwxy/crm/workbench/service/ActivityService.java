package com.bwxy.crm.workbench.service;

import com.bwxy.crm.workbench.bean.Activity;
import com.bwxy.crm.workbench.bean.ActivityQueryVo;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/18 21:45
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityService {
    List<Activity> listAll(ActivityQueryVo activityQueryVo);
}
