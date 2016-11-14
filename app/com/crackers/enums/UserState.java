package com.crackers.enums;

public enum UserState {
    ACTIVE(1), IN_ACTIVE(2);

    private Integer code;

    private UserState(Integer code) {
        this.code = code;
    }

    public static Integer getCode(UserState userState)
    {
        return userState.code;
    }
}