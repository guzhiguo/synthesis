package TimerCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutor 其设计的思想是 每个被调度的任务都会由线程池中的一个线程去执行 因此任务是并发执行的
 * 相互之间不会受到干扰 需要注意的是 只有当任务的执行时间来时 ScheduledExecutor才回真正启动一个线程
 * 其余时间ScheduledExecutor都是在轮询任务的状态
 *
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
