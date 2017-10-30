package com.revature.trms;

import org.apache.log4j.Logger;

public class LoggingService {
	
	private static LoggingService ls;
	
	private static Logger log = Logger.getRootLogger();
	
	private LoggingService(){
		super();
	}
	
	public static synchronized LoggingService getInstance(){
		
		if (ls==null){
			ls = new LoggingService();
		}
		
		return ls;
		
	}
	
	public Logger getLogger(){
		
		return log;
		
	}

}
