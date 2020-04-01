package com.example.synthesis.error;

/**
 * 错误日志级别
 *
 * @author: bill
 * @create: 2020/03/13 17:52
 */
public enum LogLevel {

    DEBUG("DEBUG", "debug级别的日志输出"),

    INFO("INFO", "info级别的日志输出"),

    WARN("WARN", "warn级别的日志输出"),

    ERROR("ERROR", "error级别的日志输出");

    private final String code;

    private final String description;

    LogLevel(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据错误码查询日志
     *
     * @param code
     * @return
     */
    public static LogLevel getByCode(String code) {
        LogLevel[] arr = values();
        int len = arr.length;
        for (int i = 0; i < len; ++i) {
            LogLevel logLevel = arr[i];
            if (logLevel.getCode().equals(code)) {
                return logLevel;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
