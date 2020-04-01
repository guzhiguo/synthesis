package com.example.synthesis.api.biz.synthesis.user.request;

import com.example.synthesis.api.model.RequestData;

/**
 * @author: bill
 * @create: 2020/03/22 17:42
 */
public class UserPageQueryRequest extends RequestData {

    private String id;

    private String nickName;

    private String pageNum;

    private String pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
