package com.viatt.zhjtpro.time;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MythproJob implements Job{

	private static final Log log = LogFactory.getLog(MythproJob.class);
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SwipCardRecord.importRecord();
		VisitorRecord.importRecord();
		FingRecord.importRecord();
		
		FingStatusJob.sendMsg() ;  
		InfoStatusJob.sendMsg();
		
		InnerStatusJob.sendMsg();
	}
}
