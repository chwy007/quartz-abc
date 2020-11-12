package com.yujizi.schedule;

import com.yujizi.jop.IPrintWordsJob;
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
public class Ischeduler {



    public static void main(String[] args) throws SchedulerException, InterruptedException {

        Date startTime=new Date();
        startTime.setTime(startTime.getTime()+3000);//延迟3s
        Date endTime=new Date();
        endTime.setTime(endTime.getTime()+10000);

//        创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//        创建任务job
        JobDetail jobDetail = JobBuilder.newJob(IPrintWordsJob.class).withIdentity("job1", "group-1")
                .usingJobData("message","jobdetail中的信息")
                .usingJobData("count",2)
                .build();

//        创建触发器SimpleTrigger
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
//                .startAt(startTime).endAt(endTime)
//                .usingJobData("message","aaa")
//                .usingJobData("count",2)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();

        System.out.println(simpleTrigger.getStartTime());
        System.out.println(simpleTrigger.getEndTime());


//        执行
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();



        Thread.sleep(1000*60*2);
        scheduler.shutdown();


    }
}
