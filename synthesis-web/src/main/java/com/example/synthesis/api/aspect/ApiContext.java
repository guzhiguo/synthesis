package com.example.synthesis.api.aspect;


import com.alibaba.fastjson.JSONObject;
import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.controller.response.ApiResult;

/**
 * Api引擎入参
 *
 * @author: bill
 * @create: 2020/03/13 00:35
 */
public class ApiContext {

    /**
     * 业务线标示
     */
    private String biz;

    /**
     * 版本
     */
    private String version;

    /**
     * 请求头
     */
    private String action;

    /**
     * api业务
     */
    private BusinessEnum businessEnum;

    /**
     * 请求Id
     */
    private String requestId;

    /**
     * 请求参数
     */
    private String request;

    /**
     * 返回结果
     */
    private String response;

    /**
     * 扩展字段
     */
    private JSONObject unifyParams;

    /**
     * 业务返回结果对象
     */
    private ApiResult responseData;

    /**
     * 业务请求入参对象
     */
    private RequestData requestData;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 结束时间
     */
    private long endTime;

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BusinessEnum getBusinessEnum() {
        return businessEnum;
    }

    public void setBusinessEnum(BusinessEnum businessEnum) {
        this.businessEnum = businessEnum;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public JSONObject getUnifyParams() {
        return unifyParams;
    }

    public void setUnifyParams(JSONObject unifyParams) {
        this.unifyParams = unifyParams;
    }

    public ApiResult getResponseData() {
        return responseData;
    }

    public void setResponseData(ApiResult responseData) {
        this.responseData = responseData;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
