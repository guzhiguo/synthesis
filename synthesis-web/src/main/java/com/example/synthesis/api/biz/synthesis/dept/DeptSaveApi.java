package com.example.synthesis.api.biz.synthesis.dept;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.dept.request.DeptSaveRequest;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.dept.SysDeptService;
import com.example.synthesis.pojo.SysDept;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存部门
 *
 * @author: bill
 * @create: 2020/04/02 00:26
 */
@Api(action = "saveDept", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class DeptSaveApi extends AbstractApi<ApiResult<String>, DeptSaveRequest> {

    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public ApiResult<String> execute(DeptSaveRequest request) {
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(request, dept);

        return ApiResult.BuildSuccess(sysDeptService.save(dept));
    }
}
