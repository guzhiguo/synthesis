package com.example.synthesis.api.aspect;

import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.controller.response.ApiResult;

/**
 * Api管理中心
 *
 * @author: bill
 * @create: 2020/03/13 00:49
 */
public interface ApiService {

    /**
     * 获取api
     *
     * @param biz     业务线
     * @param action  请求
     * @param version 版本
     * @return
     */
    OpenApi<ApiResult, RequestData> getApi(String biz, String action, String version);


    /**
     * 根绝api类获取api
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T extends OpenApi<?, ?>> T getApi(Class<T> clazz);

}
