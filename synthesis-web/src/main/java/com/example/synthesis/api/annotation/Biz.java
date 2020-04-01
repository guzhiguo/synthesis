package com.example.synthesis.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: bill
 * @create: 2020/03/13 01:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface Biz {

    /**
     * 业务代码
     *
     * @return
     */
    String value();

    /**
     * 版本好
     */
    String version();


}
