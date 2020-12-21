package com.bwxy.crm.base.bean;

import com.bwxy.crm.workbench.bean.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.base.bean
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/19 17:03
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ResultVo<T> {

   /* //当前页
    private int page;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPages;
    //总记录数
    private long totalRows;
    //每页的数据
    private List<Activity> dataList;*/
    //包含了分页操作中的所有参数，以及所有的市场活动信息
    private PageInfo<Activity> pageInfo;

    public PageInfo<Activity> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<Activity> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
