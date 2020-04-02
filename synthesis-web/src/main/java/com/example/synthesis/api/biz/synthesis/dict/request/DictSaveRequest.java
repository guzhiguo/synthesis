package com.example.synthesis.api.biz.synthesis.dict.request;

import com.example.synthesis.api.model.RequestData;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: bill
 * @create: 2020/04/02 20:06
 */
@Getter
@Setter
public class DictSaveRequest extends RequestData {

    private String value;

    private String label;

    private String type;

    private String describe;

    private Long sort;

    private String creator;

    private String lastUpdateBy;

    private String remarks;

}
