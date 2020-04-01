package com.example.synthesis.polymerize.service;

/**
 * @author: bill
 * @create: 2020/03/13 01:02
 */
public class ApiEngineRequest {

    /**
     * 接口名称
     */
    private String apiActionName;

    /**
     * api 所在的package-info 的value
     */
    private String apiBizName;

    /**
     * 版本
     */
    private String version;

    /**
     * 入参
     */
    private String params;

    /**
     * 幂等id
     */
    private String requestId;


    public String getApiActionName() {
        return apiActionName;
    }

    public void setApiActionName(String apiActionName) {
        this.apiActionName = apiActionName;
    }

    public String getApiBizName() {
        return apiBizName;
    }

    public void setApiBizName(String apiBizName) {
        this.apiBizName = apiBizName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
