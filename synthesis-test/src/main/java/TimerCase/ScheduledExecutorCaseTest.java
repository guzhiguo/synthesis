package TimerCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: bill
 * @create: 2024/07/14 12:31
 */
public class ScheduledExecutorCaseTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        System.out.println(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()));
        scheduledExecutorService.scheduleAtFixedRate(new ScheduledExecutorCase("任务B"), 5, 1, TimeUnit.SECONDS);
    }
}
