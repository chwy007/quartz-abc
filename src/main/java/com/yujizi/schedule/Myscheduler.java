package com.yujizi.schedule;

import com.yujizi.jop.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

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
public class Myscheduler {



    public static void main(String[] args) throws SchedulerException, InterruptedException {
//        创建调度器
//        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//        创建任务job
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).withIdentity("job1", "group1").build();

//        创建触发器SimpleTrigger
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

//        创建触发器CronTrigger 基于日历
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * 12 11 ?")).build();

//        执行
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();


//        TimeUnit.MINUTES.sleep(1);
        Thread.sleep(1000*60);
        scheduler.shutdown();


    }
}
