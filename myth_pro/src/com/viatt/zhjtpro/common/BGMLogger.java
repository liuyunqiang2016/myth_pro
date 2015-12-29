package com.viatt.zhjtpro.common;

import org.apache.log4j.Logger;

public class BGMLogger
{
	private static Logger logger = Logger.getLogger("log4j.properties");

	public BGMLogger()
	{
	} 

	public static void LogInfo(String msg)
	{
		logger.info(msg);
	}

	public static void LogDebug(String msg)
	{
		logger.debug(msg);
	}

	public static Logger getLogger()
	{
		return logger;
	}

	public static void main(String args[])
	{
		LogInfo("Hello,I am valley.");
		LogInfo("this is the test.");
	}
}
