package TimerCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * @author: bill
 * @create: 2024/07/14 11:49
 */
public class TimerCase extends TimerTask {

    String jobName;

    public TimerCase(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()) + "开始执行任务-> " + jobName);
    }
}
