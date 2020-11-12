package com.yujizi.jop;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: quartz-demo
 * @Package: com.yujizi.jop
 * @ClassName: PrintWordsJob
 * @Author: ychw
 * @Description:
 * @Date: 2020/11/11 18:56
 * @Version: 1.0
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution//这两个注解搭配使用，防止jobdetail实例中的javaDataMap发生线程安全问题
public class IPrintWordsJob implements Job {
    private String message;
    private int count;

//优先取的是trigger中JobDataMap的值
    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("PrintWordsJob start at"+time+" prints:hello job-"+new Random().nextInt(100));

//        System.out.println(context.getJobDetail().getJobDataMap().get("message"));
//        System.out.println(context.getTrigger().getJobDataMap().get("message"));

        System.out.println(message);

        ++count;
        System.out.println(count);
        context.getJobDetail().getJobDataMap().put("count",count);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
