package com.example.synthesis.api.biz.synthesis.dept;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.dept.request.DeptUpdateRequest;
import com.example.synthesis.controller.response.ApiResult;

/**
 * 更新部门
 *
 * @author: bill
 * @create: 2020/04/02 00:29
 */
@Api(action = "updateDept", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class DeptUpdateApi extends AbstractApi<ApiResult<String>, DeptUpdateRequest> {

    @Override
    public ApiResult<String> execute(DeptUpdateRequest request) {
        return null;
    }
}
