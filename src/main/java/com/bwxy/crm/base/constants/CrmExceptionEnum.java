package com.bwxy.crm.base.constants;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.base.constants
 * @Description: java类作用描述
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 17:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public enum CrmExceptionEnum {
    //登录异常
    LOGIN_ACCOUNT_EXCEPTION("001","用户名或密码错误！"),
    LOGIN_EXPIRE_EXCEPTION("001","该用户已失效！"),
    LOGIN_LOCK_EXCEPTION("001","账户被锁定，请联系管理员！"),
    LOGIN_IP_EXCEPTION("001","非法的ip地址！"),

    //市场活动等异常
    ACTIVITY_CREATE_EXCEPTION("002","市场活动添加失败！"),
    ACTIVITY_EDIT_EXCEPTION("002","市场活动修改失败！");



    //业务状态码，因为项目复杂的时候需要把业务细分，此时通过状态码来区分
    private String code;
    private String mess;

    CrmExceptionEnum(String code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
