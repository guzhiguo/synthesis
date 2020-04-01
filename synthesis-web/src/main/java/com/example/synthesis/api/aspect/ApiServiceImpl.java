package com.example.synthesis.api.aspect;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.model.RequestData;
import com.example.synthesis.api.util.ApiUtil;
import com.example.synthesis.controller.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Api管理实现类
 *
 * @author: bill
 * @create: 2020/03/13 02:39
 */
@Slf4j
@Component("apiService")
public class ApiServiceImpl implements ApiService, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    /**
     * service库
     */
    private final Map<String, OpenApi<ApiResult, RequestData>> apiMap = new HashMap<>();

    /**
     * api列表
     */
    private final Map<String, Set<String>> versionMap = new HashMap<>();

    /**
     * spring上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 标示是否已经初始化
     */
    private static volatile AtomicBoolean isInit = new AtomicBoolean(false);


    @Override
    public OpenApi<ApiResult, RequestData> getApi(String biz, String action, String version) {
        String apiName = ApiUtil.getApiName(biz, action, version);
        if (!apiMap.containsKey(apiName)) {
            String simpleApiName = ApiUtil.getApiName(biz, action, null);
            if (versionMap.containsKey(simpleApiName)) {
                Set<String> versions = versionMap.get(simpleApiName);
                for (String v : versions) {
                    if (version.compareTo(v) >= 0) {
                        apiName = ApiUtil.getApiName(biz, action, v);
                        break;
                    }
                }
            }
        }
        return apiMap.get(apiName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OpenApi<?, ?>> T getApi(Class<T> clazz) {
        String apiName = ApiUtil.getApiName(clazz);
        if (apiMap.containsKey(apiName)) {
            return (T) apiMap.get(apiName);
        }
        return null;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //防止存在多个容器多次初始化
        if (!isInit.compareAndSet(false, true)) {
            return;
        }
        init();
    }

    /**
     * spring容器启动完之后执行 初始化OpenApi先关配置
     */
    @PostConstruct
    private void init() {
        Map<String, OpenApi> openApiMap = applicationContext.getBeansOfType(OpenApi.class);
        if (CollectionUtils.isEmpty(openApiMap)) {
            return;
        }
        for (OpenApi openApi : openApiMap.values()) {
            Api api = openApi.getClass().getAnnotation(Api.class);
            String apiName = ApiUtil.getApiName(openApi.getClass());
            String simpleApiName = ApiUtil.getSimpleApiName(openApi.getClass());
            if (api == null) {
                log.error("no api annotation {0},{2}", apiName, openApi.getClass().getName());
                //todo 抛异常 没有配置注解
            }
            String version = api.version();
            if (!apiMap.containsKey(apiName)) {
                apiMap.put(apiName, openApi);
                log.info("scan api {0},{1}", apiName, openApi.getClass().getName());
            } else {
                log.info("scan api {0},{1} already exist", apiName, openApi.getClass().getName());
            }
            if (versionMap.containsKey(simpleApiName)) {
                versionMap.get(simpleApiName).add(version);
            } else {
                Set<String> versions = new TreeSet<>();
                versions.add(version);
                versionMap.put(simpleApiName, versions);
            }
            openApi.initAfterStart();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Map<String, OpenApi<ApiResult, RequestData>> getApiMap() {
        return apiMap;
    }

    public Map<String, Set<String>> getVersionMap() {
        return versionMap;
    }
}
