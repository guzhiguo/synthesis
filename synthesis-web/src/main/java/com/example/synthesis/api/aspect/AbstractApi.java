package com.example.synthesis.api.aspect;

import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.common.Result;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.error.TribeError;

import java.io.Serializable;

/**
 * @author: bill
 * @create: 2020/03/13 16:46
 */
public abstract class AbstractApi<M extends ApiResult, N extends RequestData> implements OpenApi<M, N> {

    @Override
    public M execute(ApiContext apiContext) {
        N requestData = (N) apiContext.getRequestData();
        return execute(requestData);
    }

    @Override
    public void initAfterStart() {

    }

    /**
     * 构建成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> T buildSuccessResponse(Serializable data) {
        Result response = new Result();
        response.setSuccess(Boolean.TRUE);
        response.setData(data);
        return (T) response;
    }

    /**
     * 构建失败返回结果
     *
     * @param error
     * @param <T>
     * @return
     */
    public <T> T buildFailResponse(TribeError error) {
        Result response = new Result();
        response.setSuccess(Boolean.FALSE);
        response.setError(error);
        return (T) response;
    }


}
