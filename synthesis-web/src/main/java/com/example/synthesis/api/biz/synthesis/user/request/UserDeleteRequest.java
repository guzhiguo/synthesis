package com.example.synthesis.api.biz.synthesis.user.request;

import com.example.synthesis.api.model.RequestData;

/**
 * @author: bill
 * @create: 2020/03/23 00:17
 */
public class UserDeleteRequest extends RequestData {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
