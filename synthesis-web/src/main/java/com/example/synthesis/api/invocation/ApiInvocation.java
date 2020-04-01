package com.example.synthesis.api.invocation;

import com.alibaba.fastjson.JSON;
import com.example.synthesis.api.aspect.ApiContext;
import com.example.synthesis.api.aspect.OpenApi;
import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.api.util.ApiUtil;
import com.example.synthesis.controller.response.ApiResult;

/**
 * Api处理器
 *
 * @author: bill
 * @create: 2020/03/13 01:17
 */
public class ApiInvocation implements Invocation {

    private ApiContext apiContext = new ApiContext();

    private OpenApi<? extends ApiResult, ? extends RequestData> api;


    @Override
    public void invoke() {
        if (apiContext.getRequest() != null && apiContext.getRequestData() == null) {
            this.apiContext.setRequestData(JSON.parseObject(apiContext.getRequest(), ApiUtil.getRequestType(this.api.getClass())));
        }
        ApiResult responseData = this.api.execute(apiContext);
        this.apiContext.setResponseData(responseData);
    }

    public ApiContext getApiContext() {
        return apiContext;
    }

    public void setApiContext(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    public OpenApi<? extends ApiResult, ? extends RequestData> getApi() {
        return api;
    }

    public void setApi(OpenApi<? extends ApiResult, ? extends RequestData> api) {
        this.api = api;
    }
}
