package com.example.synthesis.controller;

import com.example.synthesis.common.Result;
import com.example.synthesis.polymerize.UnifiedEntryRequest;
import com.example.synthesis.polymerize.UnifiedEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: bill
 * @create: 2020/03/12 23:54
 */
@RestController
@RequestMapping("/synthesis")
public class MainController {

    @Autowired
    private UnifiedEntryService unifiedEntryService;

    /**
     * api统一请求接口
     *
     * @return
     */
    @RequestMapping(value = "/enter.json", method = {RequestMethod.GET, RequestMethod.POST})
    public Result entry(HttpServletRequest request, HttpServletResponse response, @RequestBody UnifiedEntryEntryRequest form) {
        return Result.buildSuccess(unifiedEntryService.dispatch(convert(form)));
    }

    /**
     * 参数转换
     *
     * @return
     */
    private UnifiedEntryRequest convert(UnifiedEntryEntryRequest form) {
        UnifiedEntryRequest request = new UnifiedEntryRequest();
        request.setApiActionName(form.getAction());
        request.setApiBizName(form.getBiz());
        request.setVersion(form.getVersion());
        request.setParams(form.getParams());
        return request;
    }

}
