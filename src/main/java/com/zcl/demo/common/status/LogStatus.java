package com.zcl.demo.common.status;

public enum LogStatus {
    LOG_IN("0","登录"),
    LOG_OUT("1","注销");

    private String code;
    private String name;


    LogStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
