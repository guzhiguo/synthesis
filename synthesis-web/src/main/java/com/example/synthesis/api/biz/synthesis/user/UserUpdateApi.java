package com.example.synthesis.api.biz.synthesis.user;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.user.request.UserUpdateRequest;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.pojo.SysUser;
import com.example.synthesis.user.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: bill
 * @create: 2020/03/23 00:28
 */
@Api(action = "update", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class UserUpdateApi extends AbstractApi<ApiResult<String>, UserUpdateRequest> {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ApiResult<String> execute(UserUpdateRequest request) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(request, sysUser);
        return ApiResult.BuildSuccess(sysUserService.update(sysUser));
    }
}
