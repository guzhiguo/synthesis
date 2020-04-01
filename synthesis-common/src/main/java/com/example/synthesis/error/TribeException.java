package com.example.synthesis.error;

import java.io.Serializable;

/**
 * @author: bill
 * @create: 2020/03/13 17:51
 */
public class TribeException extends RuntimeException implements Serializable {

    /**
     * 错误码
     */
    private TribeError error;

    /**
     * 日志级别
     */
    private LogLevel logLevel = LogLevel.INFO;

    public TribeException(TribeError error) {
        super(error.getMessage());
        this.error = error;
    }

    public TribeException(TribeError error, LogLevel logLevel) {
        super(error.getMessage());
        this.error = error;
        this.logLevel = logLevel;
    }

    public TribeError getError() {
        return error;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public String toString() {
        return this.error.toString();
    }
}
