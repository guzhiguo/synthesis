package com.example.synthesis.error;

/**
 * @author: bill
 * @create: 2020/03/13 17:36
 */
public enum SystemErrorEnum {

    SYSTEM_ERROR("SYSTEM_ERROR", "system error", "系统繁忙"),


    PACKAGE_INFO_NOT_EXIST("PACKAGE_INFO_NOT_EXIST", "packinfo not exist", "packageInfo 文件不存在");



    private TribeError error;

    SystemErrorEnum(String code, String message, String view) {
        this.error = new TribeError(code, message, view);
    }

    public TribeError getError(Object... args) {
        return this.error;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}
