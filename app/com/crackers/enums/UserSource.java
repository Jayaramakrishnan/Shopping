package com.crackers.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserSource {
    FORM(1), AD(2), LDAP(3);

    private Integer code;

    private UserSource(Integer code) {
        this.code = code;
    }
    private static Map<Integer, UserSource> statusMaster;

    public static Map<Integer, UserSource> getStatusMaster()
    {
        if (statusMaster == null)
        {
            statusMaster = new HashMap<>();
            for (UserSource e : values())
            {
                statusMaster.put(e.code, e);
            }
        }
        return statusMaster;
    }

    public static Integer getCode(UserSource res)
    {
        return res.code;
    }
}
