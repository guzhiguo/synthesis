package TimerCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * 使用Timer实现任务调度,它的实现方式是将所有任务都塞入一个taskList中，并按照最初设定的时间排序来执行，虽然是线程安全的，
 * 但是由于整个任务过程中都是由一个线程来完成的，所以当直行到某个任务并发生一场或者延时的时候，后面的任务也会收到影响
 *
 * @author: bill
 * @create: 2024/07/14 12:04
 */
public class TimerCaseTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));
        calendar.add(Calendar.SECOND, 3);//获取距离当前时间3秒后的时间
        Timer timer = new Timer();
        //这里的参数顺序是 任务 开始时间 间隔步长
        timer.schedule(new TimerCase("任务A"), calendar.getTime(), 1000);
    }

}
