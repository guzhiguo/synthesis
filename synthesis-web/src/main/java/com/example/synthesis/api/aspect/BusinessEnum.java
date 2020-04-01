package com.example.synthesis.api.aspect;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: bill
 * @create: 2020/03/13 00:39
 */
public enum BusinessEnum {

    SYNTHESIS("synthesis", "综合体");

    private String code;

    private String desc;

    BusinessEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code查询枚举
     *
     * @param code
     * @return
     */
    public static BusinessEnum getEnumByCode(String code) {
        for (BusinessEnum value : BusinessEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
