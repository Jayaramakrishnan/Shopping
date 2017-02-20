package com.crackers.common;

import org.springframework.stereotype.Component;

@Component
public class DateStringUtil
{

    public static Long getCurrentLong()
    {
        return new Long(System.currentTimeMillis());
    }
}