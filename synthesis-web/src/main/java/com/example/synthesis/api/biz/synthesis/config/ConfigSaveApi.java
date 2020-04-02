package com.example.synthesis.api.biz.synthesis.config;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.aspect.AbstractApi;
import com.example.synthesis.api.aspect.BusinessEnum;
import com.example.synthesis.api.biz.synthesis.config.request.ConfigSaveRequest;
import com.example.synthesis.config.SysConfigService;
import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.pojo.SysConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存系统配置
 *
 * @author: bill
 * @create: 2020/04/01 23:28
 */
@Api(action = "saveConfig", version = "1.0.0", business = BusinessEnum.SYNTHESIS)
public class ConfigSaveApi extends AbstractApi<ApiResult<String>, ConfigSaveRequest> {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public ApiResult<String> execute(ConfigSaveRequest request) {
        SysConfig config = new SysConfig();
        BeanUtils.copyProperties(request, config);
        return ApiResult.BuildSuccess(sysConfigService.save(config));
    }
}
