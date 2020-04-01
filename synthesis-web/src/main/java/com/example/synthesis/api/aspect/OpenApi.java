package com.example.synthesis.api.aspect;

import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.controller.response.ApiResult;

/**
 * openApi 业务方需实现对应服务
 *
 * @author: bill
 * @create: 2020/03/13 00:50
 */
public interface OpenApi<M extends ApiResult, N extends RequestData> {

    /**
     * Api入口
     *
     * @param apiContext
     * @return
     */
    M execute(ApiContext apiContext);

    /**
     * openApi真正执行入口
     *
     * @param request
     * @return
     */
    M execute(final N request);

    /**
     * 容器初始化完毕后启动 供框架使用 开发者无需关注
     */
    void initAfterStart();

}
