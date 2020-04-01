package com.example.synthesis.api.engine;

import com.example.synthesis.api.aspect.ApiContext;
import com.example.synthesis.api.aspect.ApiService;
import com.example.synthesis.api.aspect.OpenApi;
import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.invocation.ApiInvocation;
import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.error.TribeError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * api核心引擎实现（ApiEngine 注入看FrameworkConfiguration）
 * 这里没有使用@Service注入 看FrameworkConfiguration.java
 *
 * @author: bill
 * @create: 2020/03/13 00:48
 */
@Slf4j
public class ApiEngineImpl implements ApiEngine {

    @Autowired
    private ApiService apiService;

    @Override
    public ApiResult run(ApiContext context) {
        OpenApi<ApiResult, RequestData> openApi = this.apiService.getApi(context.getBiz(), context.getAction(), context.getVersion());
        run(openApi, context);
        return context.getResponseData();
    }

    private void run(OpenApi<? extends ApiResult, ? extends RequestData> openApi, ApiContext apiContext) {

        ApiInvocation invocation = new ApiInvocation();
        if (apiContext == null) {
            apiContext = new ApiContext();
        }
        invocation.setApiContext(apiContext);
        if (openApi != null) {
            Api api = openApi.getClass().getAnnotation(Api.class);
            if (api != null) {
                apiContext.setBusinessEnum(api.business());
            }
            invocation.setApi(openApi);
            invocation.invoke();
        } else {
            log.error("执行api无法找到");
            ApiResult response = new ApiResult(new TribeError("api not find", "api not find", "执行api无法找到"));
            apiContext.setResponseData(response);
        }

    }
}
