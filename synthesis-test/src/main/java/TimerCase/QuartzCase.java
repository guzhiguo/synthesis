package TimerCase;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: bill
 * @create: 2024/07/14 12:46
 */
public class QuartzCase implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("打印时间是:" + format.format(new Date()));
        //通过Jobkey获取tigger中的参数和自定义值
        JobKey jobKey = context.getTrigger().getJobKey();
        System.out.println("jobName:" + jobKey.getName());
        System.out.println("自定义值:" + context.getTrigger().getJobDataMap().getDouble("haha"));
    }

}
