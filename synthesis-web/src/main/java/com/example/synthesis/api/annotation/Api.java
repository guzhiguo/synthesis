package com.example.synthesis.api.annotation;

import com.example.synthesis.api.aspect.BusinessEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author: bill
 * @create: 2020/03/13 02:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface Api {

    /**
     * api名称 如:/aaa/ddd
     *
     * @return
     */
    String action();

    /**
     * api版本号
     *
     * @return
     */
    String version();

    /**
     * api业务说明 打印异常日志会使用
     *
     * @return
     */
    BusinessEnum business();

}
