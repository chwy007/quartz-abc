package com.yujizi.jop;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ProjectName: quartz-demo
 * @Package: com.yujizi.jop
 * @ClassName: PrintWordsJob
 * @Author: ychw
 * @Description:
 * @Date: 2020/11/11 18:56
 * @Version: 1.0
 */
public class PrintWordsJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("PrintWordsJob start at"+time+" prints:hello job-"+new Random().nextInt(100));

    }


}
