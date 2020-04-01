package com.example.synthesis.service.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页入参封封装
 *
 * @author: bill
 * @create: 2020/03/21 22:12
 */
public class PageRequest {

    /**
     * 默认页码
     */
    private int pageNum = 1;

    /**
     * 默认记录数
     */
    private int pageSize = 10;

    /**
     * 查询参数
     */
    private Map<String, Object> params = new HashMap<>();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
