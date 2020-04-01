package com.example.synthesis.common;

import com.example.synthesis.error.TribeError;

import java.io.Serializable;

/**
 * @author: bill
 * @create: 2020/03/13 16:52
 */
public abstract class AbstractResult<T> implements Serializable {

    private TribeError error;

    private String traceId;

    private boolean success;

    private T data;

    public AbstractResult() {
    }

    public TribeError getError() {
        return error;
    }

    public void setError(TribeError error) {
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        this.setSuccess(Boolean.TRUE);
    }

    @Override
    public String toString() {
        return "AbstractResult{" +
                "error=" + error +
                ", traceId='" + traceId + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
