package com.yujizi.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @ProjectName: quartz-demo
 * @Package: com.yujizi.listener
 * @ClassName: Mylistener
 * @Author: ychw
 * @Description:
 * @Date: 2020/11/12 19:14
 * @Version: 1.0
 */
public class MyListener implements JobListener {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("jobToBeExecuted===========================");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed===========================");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted===========================");
    }
}
