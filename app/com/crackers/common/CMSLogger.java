package com.crackers.common;

import org.apache.log4j.Logger;


public final class CMSLogger
{

	public static void info(Logger logger, String message)
	{
		if (logger != null && JavaObjectUtil.printToLog)
		{
			logger.info(message);
		}
		else
		{}
	}

	public static void warn(Logger logger, String message, Exception exception)
	{
		if (logger != null && JavaObjectUtil.printToLog)
		{
			logger.warn(message, exception);
		}
		else
		{
			exception.printStackTrace();
		}
	}

	public static void debug(Logger logger, String message)
	{
		if (logger != null && JavaObjectUtil.printToLog)
		{
			logger.debug(message);
		}
		else
		{}
	}

	public static void error(Logger logger, String message, Exception exception)
	{
		if (logger != null && JavaObjectUtil.printToLog)
		{
			logger.error(message, exception);
		}
		else
		{
			exception.printStackTrace();
		}
	}
}
