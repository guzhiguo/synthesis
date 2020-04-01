package com.example.synthesis.polymerize;

import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.polymerize.service.ApiEngineRequest;
import com.example.synthesis.polymerize.service.ApiRunService;
import com.taobao.hsf.app.spring.util.annotation.HSFProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: bill
 * @create: 2020/03/13 00:21
 */
@HSFProvider(serviceInterface = UnifiedEntryService.class,
        serviceVersion = "1.0.0",
        clientTimeout = 8000)
public class UnifiedEntryServiceImpl implements UnifiedEntryService {


    @Autowired
    private ApiRunService apiRunService;

    @Override
    public ApiResult dispatch(UnifiedEntryRequest request) {
        ApiEngineRequest req = new ApiEngineRequest();
        req.setApiActionName(request.getApiActionName());
        req.setApiBizName(request.getApiBizName());
        req.setParams(request.getParams());
        req.setRequestId(request.getRequestId());
        req.setVersion(request.getVersion());
        ApiResult result = apiRunService.runApi(req);
        return result;
    }
}
