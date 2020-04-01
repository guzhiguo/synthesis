package com.example.synthesis.utils;

import java.util.UUID;

/**
 * 获取uuid
 * @author: bill
 * @create: 2020/03/30 22:53
 */
public class UUIDUtils {

    /**
     * 获取uuid
     * @return
     */
    public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "");
    }

}
