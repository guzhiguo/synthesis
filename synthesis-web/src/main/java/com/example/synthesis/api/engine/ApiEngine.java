package com.example.synthesis.api.engine;

import com.example.synthesis.api.aspect.ApiContext;
import com.example.synthesis.controller.response.ApiResult;

/**
 * Api核心引擎
 *
 * @author: bill
 * @create: 2020/03/13 00:31
 */
public interface ApiEngine {


    ApiResult run(ApiContext context);


}
