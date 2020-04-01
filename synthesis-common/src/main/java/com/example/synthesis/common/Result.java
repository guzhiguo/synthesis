package com.example.synthesis.common;

import com.example.synthesis.error.TribeError;

/**
 * @author: bill
 * @create: 2020/03/13 16:56
 */
public final class Result<T> extends AbstractResult<T> {


    public Result() {
    }


    public static <T> Result<T> buildSuccess(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setData(data);
        return result;
    }

    public static Result<Void> buildSuccess() {
        Result<Void> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> Result<T> buildFail(TribeError error) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setError(error);
        result.setData(null);
        return result;
    }

    public String toString() {
        return super.toString();
    }
}
