package com.example.synthesis.api.biz.synthesis.user;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.annotation.ApiResultAn;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.user.request.UserPageQueryRequest;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.service.request.PageRequest;
import com.example.synthesis.service.response.PageResult;
import com.example.synthesis.user.SysUserService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.Map;

/**
 * 用户分页列表
 *
 * @author: bill
 * @create: 2020/03/22 17:41
 */
@Api(action = "findPageUser", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class UserPageQueryApi extends AbstractApi<ApiResult<PageResult>, UserPageQueryRequest> {

    @Autowired
    private SysUserService sysUserService;

    @ApiResultAn
    @Override
    @Valid
    public ApiResult<PageResult> execute(UserPageQueryRequest request) {

        PageRequest params = new PageRequest();
        Map<String, Object> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(request.getUserId())) {
            map.put("id", request.getUserId());
        }
        if (StringUtils.isNotBlank(request.getNickName())) {
            map.put("nickName", request.getNickName());
        }
        params.setPageNum(Integer.valueOf(request.getPageNum()));
        params.setPageSize(Integer.valueOf(request.getPageSize()));
        params.setParams(map);
        return ApiResult.BuildSuccess(sysUserService.findPage(params));
    }
}
