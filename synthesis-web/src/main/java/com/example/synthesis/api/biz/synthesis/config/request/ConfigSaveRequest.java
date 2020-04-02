package com.example.synthesis.api.biz.synthesis.config.request;

import com.example.synthesis.api.model.RequestData;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: bill
 * @create: 2020/04/01 23:29
 */
@Getter
@Setter
public class ConfigSaveRequest extends RequestData {

    private String value;

    private String label;

    private String type;

    private String describe;

    private Long sort;

    private String creator;

    private String lastUpdateBy;

    private String remarks;


}
