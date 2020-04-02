package com.example.synthesis.api.biz.synthesis.user;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.user.request.UserDeleteRequest;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除用户
 *
 * @author: bill
 * @create: 2020/03/23 00:17
 */
@Api(action = "deleteUser", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class UserDeleteApi extends AbstractApi<ApiResult<String>, UserDeleteRequest> {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ApiResult<String> execute(UserDeleteRequest request) {
        return ApiResult.BuildSuccess(sysUserService.delete(request.getId()));
    }
}
