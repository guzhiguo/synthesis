package TimerCase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: bill
 * @create: 2024/07/14 12:31
 */
public class ScheduledExecutorCase implements Runnable {

    String jobName;

    public ScheduledExecutorCase(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()) + "开始执行任务-> " + jobName);
    }
}
