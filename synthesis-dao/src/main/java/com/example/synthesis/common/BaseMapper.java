package com.example.synthesis.common;

import java.util.HashMap;
import java.util.List;

/**
 * @author: bill
 * @create: 2020/03/30 22:40
 */
public interface BaseMapper<T> {

    /**
     * 逻辑删除
     *
     * @param params
     */
    void deleteByPrimaryKey(HashMap<String, String> params);

    /**
     * 新增
     *
     * @param params
     * @return
     */
    void insert(HashMap<String, String> params);

    /**
     * 按主键查询
     *
     * @param params
     * @return
     */
    T selectByPrimaryKey(HashMap<String, String> params);

    /**
     * 按主键更新
     *
     * @param params
     * @return
     */
    void updateByPrimaryKey(HashMap<String, String> params);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    List<T> findPage(HashMap<String, String> params);

}
