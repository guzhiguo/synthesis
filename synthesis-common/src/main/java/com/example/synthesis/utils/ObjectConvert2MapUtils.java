package com.example.synthesis.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * @author: bill
 * @create: 2020/03/30 22:56
 */
public class ObjectConvert2MapUtils {


    /**
     * 对象转map<String,Object>
     *
     * @param o
     * @return
     */
    public static HashMap<String, Object> convertOfObject(Object o) {
        return JSON.parseObject(JSON.toJSONString(o), HashMap.class);
    }

    /**
     * 对象转map<String,String>
     *
     * @param o
     * @return
     */
    public static HashMap<String, String> convertOfString(Object o) {
        return JSON.parseObject(JSON.toJSONString(o), HashMap.class);
    }


}
