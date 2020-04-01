package com.example.synthesis.configurer;

import com.example.synthesis.api.engine.ApiEngine;
import com.example.synthesis.api.engine.ApiEngineImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: bill
 * @create: 2020/03/14 16:57
 */
@Configuration
public class FrameworkConfiguration {

    @Bean("apiEngine")
    public ApiEngine apiEngine() {
        ApiEngineImpl apiEngine = new ApiEngineImpl();
        //todo 可以添加拦截器设置到apiEngine 在里面配置统一的顺序执行
        return apiEngine;
    }

}
