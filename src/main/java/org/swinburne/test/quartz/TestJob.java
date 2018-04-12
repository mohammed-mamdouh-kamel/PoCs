package org.swinburne.test.quartz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
	
	Logger logger =  Logger.getLogger(TestJob.class);

    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {

        System.out.println("Executing Job");
        logger.error("Executing Job");
    }

}