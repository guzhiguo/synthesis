package log;

import java.util.logging.Logger;

/**
 * @author: bill
 * @create: 2024/07/21 10:14
 */
public class LogTest {

    private static final Logger logger = Logger.getLogger(String.valueOf(LogTest.class));

    public static void main(String[] args) {
        logger.info("测试日志");
    }
}
