package com.example.synthesis.service;

import com.example.synthesis.service.request.PageRequest;
import com.example.synthesis.service.response.PageResult;

import java.util.List;

/**
 * 通用增删改查  需要的可以直接继承
 *
 * @author: bill
 * @create: 2020/03/21 21:55
 */
public interface CurdService<T> {

    /**
     * 保存
     *
     * @param record
     * @return
     */
    String save(T record);

    /**
     * 按条件删除
     *
     * @param id
     * @return
     */
    String delete(String id);

    /**
     * 按条件更新
     *
     * @param record
     * @return
     */
    String update(T record);

    /**
     * 按分页条件查询
     *
     * @param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);


}
