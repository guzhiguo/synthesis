package TimerCase;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz可以满足更多复杂的调度需求 例如在执行的时间去执行任务调度 当然指定日期实现任务调度也可以是用ScheduledExecutor和Calendar组合实现
 * Quartz设计的核心类是Scheduler和Job以及Tigger 其中Job负责定义需要执行的任务 Tigger负责设置调度策略 Scheduler负责将二者组装在一起并且触发任务执行
 *
 * @author: bill
 * @create: 2024/07/14 12:52
 */
public class QuartzCaseTest {

    public static void main(String[] args) throws SchedulerException {
        //创建一个jobDetail实例 将该实例与HelloJob class绑定
        JobDetail jobDetail = JobBuilder.newJob(QuartzCase.class).withIdentity("myJob", "group1").build();
        //创建trigger实例 立即执行 每隔2秒执行
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").usingJobData("haha", 20.00).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        //下面这段代码是通过Tigger设置任务调度的策略 通过制定日期来执行任务的调度
        // 创建一个每周触发的Trigger，指明星期几几点几分执行
        //Trigger trigger = TriggerUtils.makeWeeklyTrigger(3, 16, 38);
        //trigger.setGroup("myTriggerGroup");
        // 从当前时间的下一秒开始执行
        //trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
        // 指明trigger的name
        //trigger.setName("myTrigger");

        //创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
