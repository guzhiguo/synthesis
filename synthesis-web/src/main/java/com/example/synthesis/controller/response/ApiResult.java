package com.example.synthesis.controller.response;


import com.example.synthesis.error.TribeError;

import java.io.Serializable;

/**
 * @author: bill
 * @create: 2020/03/12 23:57
 */
public class ApiResult<T> implements Serializable {

    /**
     * 业务请求成功与否
     */
    private boolean success = false;

    /**
     * 错误相关信息
     */
    private TribeError error;

    /**
     * 返回的数据
     */
    private T data;

    public ApiResult() {
        super();
    }

    public static <T> ApiResult<T> BuildSuccess(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.TRUE);
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> BuildError(TribeError error) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setError(error);
        return result;
    }

    public ApiResult(TribeError error) {
        super();
        this.success = false;
        this.error = error;
    }

    public void ApiErrorResult(TribeError error) {
        this.success = false;
        this.error = error;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TribeError getError() {
        return error;
    }

    public void setError(TribeError error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
