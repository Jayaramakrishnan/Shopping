/**
 * @author rajagja
 * @date Nov 1, 2016
 */
package com.crackers.common;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class DateStringUtil
{

	public Timestamp getCurrentTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}
}