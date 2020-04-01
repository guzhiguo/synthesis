package com.example.synthesis.api.util;

import com.example.synthesis.api.annotation.Api;
import com.example.synthesis.api.annotation.Biz;
import com.example.synthesis.error.SystemErrorEnum;
import com.example.synthesis.error.TribeError;
import com.example.synthesis.error.TribeException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * api工具类
 *
 * @author: bill
 * @create: 2020/03/13 01:25
 */
public class ApiUtil {

    /**
     * api名称分割线
     */
    private static final String SPLIT = "/";

    /**
     * 日志类需要私有构造方法
     */
    private ApiUtil() {
    }


    /**
     * 拼接访问请求
     *
     * @param biz
     * @param action
     * @param version
     * @return
     */
    public static String getApiName(String biz, String action, String version) {

        StringBuilder apiName = new StringBuilder();
        apiName.append(SPLIT);
        apiName.append(biz);
        apiName.append(SPLIT);
        apiName.append(action);
        if (StringUtils.isNotBlank(version)) {
            apiName.append(SPLIT);
            apiName.append(version);
        }
        return apiName.toString();
    }

    /**
     * 根据Api的雷鸣获取apiName
     *
     * @param apiClazz
     * @return
     */
    public static String getApiName(Class<?> apiClazz) {
        Biz biz = apiClazz.getPackage().getAnnotation(Biz.class);
        //todo 这里的异常类需要补充
        isNotNull(biz, SystemErrorEnum.PACKAGE_INFO_NOT_EXIST.getError());
        String bizName = biz.value();
        Api api = apiClazz.getAnnotation(Api.class);
        String action = api.action();
        String version = api.version();
        return getApiName(bizName, action, version);
    }


    /**
     * 根据api的类名获取无版本的apiName
     *
     * @param apiClazz
     * @return
     */
    public static String getSimpleApiName(Class<?> apiClazz) {
        Biz biz = apiClazz.getPackage().getAnnotation(Biz.class);
        isNotNull(biz, SystemErrorEnum.PACKAGE_INFO_NOT_EXIST.getError());
        String bizName = biz.value();
        Api api = apiClazz.getAnnotation(Api.class);
        String action = api.action();
        return getApiName(bizName, action, "");
    }


    /**
     * 获取request泛型世纪class类型
     *
     * @return
     */
    public static Type getRequestType(Class<?> clazz) {
        Type superclass = getGenericSuperclass(clazz);
        Type[] args = ((ParameterizedType) superclass).getActualTypeArguments();
        return args[args.length - 1];

    }

    /**
     * 获取response泛型世纪class类型
     *
     * @param clazz
     * @return
     */
    public static Type getResponseType(Class<?> clazz) {
        Type superclass = getGenericSuperclass(clazz);
        Type[] args = ((ParameterizedType) superclass).getActualTypeArguments();
        return args[args.length - 2];
    }

    /**
     * 获取response泛型中实际类型中的泛型类型
     *
     * @param clazz
     * @return
     */
    public static Class<? extends Serializable> getResponseTypeClassType(Class<?> clazz) {
        Type responseType = ApiUtil.getResponseType(clazz);
        Class<? extends Serializable> modelType = (Class) ((ParameterizedType) responseType).getActualTypeArguments()[0];
        return modelType;
    }

    /**
     * 获取定义了request泛型的类
     *
     * @return
     */
    private static Type getGenericSuperclass(Class<?> clazz) {
        Type superClass = clazz.getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
            if (types != null && types.length == 2) {
                return superClass;
            }
        }
        return getGenericSuperclass(clazz.getSuperclass());
    }

    public static void isNotNull(Object object, TribeError error) {
        if (object == null) {
            throw new TribeException(error);
        }
    }


}
