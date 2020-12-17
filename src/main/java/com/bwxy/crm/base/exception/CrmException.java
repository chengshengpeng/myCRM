package com.bwxy.crm.base.exception;

import com.bwxy.crm.base.constants.CrmExceptionEnum;

/**
 * @ProjectName: crm
 * @Package: com.bwxy.crm.base.exception
 * @Description: 项目中所需要的异常类
 * @Author: 程生鹏
 * @CreateDate: 2020/12/17 17:06
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class CrmException extends RuntimeException{
    private CrmExceptionEnum crmExceptionEnum;

    public CrmException(CrmExceptionEnum crmExceptionEnum) {
        //如果想获取到堆栈中的异常信息必须得调用父类的构造方法
        super(crmExceptionEnum.getMess());
        this.crmExceptionEnum = crmExceptionEnum;
    }
}
