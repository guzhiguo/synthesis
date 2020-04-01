package com.example.synthesis.error;

import java.io.Serializable;

/**
 * @author: bill
 * @create: 2020/03/13 01:53
 */
public class TribeError implements Serializable {

    private String code;

    private String message;

    private String view;


    public TribeError() {
    }

    public TribeError(String code, String message, String view) {
        this.code = code;
        this.message = message;
        this.view = view;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "TribeError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", view='" + view + '\'' +
                '}';
    }
}
