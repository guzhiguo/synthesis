package com.example.synthesis.controller;

/**
 * api请求统一入参
 *
 * @author: bill
 * @create: 2020/03/13 19:10
 */
public class UnifiedEntryEntryRequest {

    /**
     * 请求接口
     */
    private String action;

    /**
     * 业务
     */
    private String biz;

    /**
     * 版本
     */
    private String version;

    /**
     * 参数
     */
    private String params;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "UnifiedEntryEntryController{" +
                "action='" + action + '\'' +
                ", biz='" + biz + '\'' +
                ", version='" + version + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
