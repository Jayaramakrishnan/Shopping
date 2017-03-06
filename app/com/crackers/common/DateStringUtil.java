package com.crackers.common;

import org.springframework.stereotype.Component;

@Component
public class DateStringUtil {

	public static Long getCurrentTimestamp() {
		return new Long(System.currentTimeMillis());
	}
}