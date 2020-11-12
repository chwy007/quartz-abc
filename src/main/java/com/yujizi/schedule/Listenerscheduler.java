package com.yujizi.schedule;

import com.yujizi.jop.PrintWordsJob;
import com.yujizi.listener.MyListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

/**
 * @ProjectName: quartz-demo
 * @Package: com.yujizi.schedule
 * @ClassName: Myscheduler
 * @Author: ychw
 * @Description:
 * @Date: 2020/11/11 19:02
 * @Version: 1.0
 */
public class Listenerscheduler {



    public static void main(String[] args) throws SchedulerException, InterruptedException {


        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//        创建任务job
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).withIdentity("job1", "group1")
                .build();

//        创建触发器SimpleTrigger
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();


//      创建一个全局job listener
        scheduler.getListenerManager().addJobListener(new MyListener(), EverythingMatcher.allJobs());

//      创建一个局部job listener
//        scheduler.getListenerManager().addJobListener(new MyListener(), KeyMatcher.keyEquals(JobKey.jobKey("job1","group1")));


//        执行
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();


        Thread.sleep(1000*30);
        scheduler.shutdown();


    }
}
