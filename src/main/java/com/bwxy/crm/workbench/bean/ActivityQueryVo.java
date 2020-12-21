package com.bwxy.crm.workbench.bean;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.workbench.bean
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/21 9:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ActivityQueryVo {
    private String name;
    private String owner;
    private String startDate;
    private String endDate;


    @Override
    public String toString() {
        return "ActivityQueryVo{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
