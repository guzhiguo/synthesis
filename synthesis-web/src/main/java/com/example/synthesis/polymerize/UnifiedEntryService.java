package com.example.synthesis.polymerize;

import com.example.synthesis.controller.response.ApiResult;

/**
 * 统一对外入口服务
 *
 * @author: bill
 * @create: 2020/03/12 23:55
 */
public interface UnifiedEntryService {

    ApiResult dispatch(UnifiedEntryRequest request);

}
