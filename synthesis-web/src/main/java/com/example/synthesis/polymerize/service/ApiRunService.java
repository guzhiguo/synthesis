package com.example.synthesis.polymerize.service;

import com.example.synthesis.api.aspect.ApiContext;
import com.example.synthesis.api.engine.ApiEngine;
import com.example.synthesis.controller.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: bill
 * @create: 2020/03/13 00:28
 */
@Component
public class ApiRunService {

    @Autowired
    private ApiEngine apiEngine;

    public ApiResult runApi(ApiEngineRequest request) {
        return apiEngine.run(buildApiContext(request));
    }


    private ApiContext buildApiContext(ApiEngineRequest request) {
        ApiContext context = new ApiContext();
        context.setBiz(request.getApiBizName());
        context.setAction(request.getApiActionName());
        context.setVersion(request.getVersion());
        context.setRequest(request.getParams());
        context.setRequestId(request.getRequestId());
        return context;
    }


}
