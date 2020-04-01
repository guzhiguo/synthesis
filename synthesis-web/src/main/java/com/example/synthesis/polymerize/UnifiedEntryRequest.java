package com.example.synthesis.polymerize;

import java.util.Map;

/**
 * @author: bill
 * @create: 2020/03/13 00:17
 */
public class UnifiedEntryRequest {

    /**
     * 接口名称
     */
    private String apiActionName;

    /**
     * api 所在的package-info 的value
     */
    private String apiBizName;

    /**
     * 幂等id
     */
    private String requestId;

    /**
     * 入参
     */
    private String params;

    /**
     * 版本
     */
    private String version;

    /**
     * 扩展
     */
    private Map<String,String> extInfo;


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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
