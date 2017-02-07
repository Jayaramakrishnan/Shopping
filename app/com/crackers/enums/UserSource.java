package com.crackers.enums;

public enum UserSource {
    FORM(1);

    private Integer code;

    private UserSource(Integer code) {
        this.code = code;
    }

    public static Integer getCode(UserSource res)
    {
        return res.code;
    }
}