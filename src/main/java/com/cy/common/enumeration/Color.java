package com.cy.common.enumeration;

import java.util.Enumeration;

/**
 * Created by zxj on 2017/4/7.
 */
public enum Color {

    RED("1", "红色"),
    YELLOW("2", "黄色");
    private String code;
    private String desc;
    Color(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
