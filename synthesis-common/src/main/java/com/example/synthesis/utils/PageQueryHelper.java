package com.example.synthesis.utils;

import com.example.synthesis.service.request.PageRequest;
import com.example.synthesis.service.response.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 分页查询助手
 *
 * @author: bill
 * @create: 2020/03/21 22:17
 */
public class PageQueryHelper {

    //约定的分页查询接口
    public static final String findPage = "findPage";

    /**
     * 分页查询 约定查询方法是findPage
     *
     * @param pageRequest 查询入参
     * @param mapper      dao层接口类
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }

    /**
     * 调用分页查询进行分页查询
     *
     * @param pageRequest 分页请求
     * @param mapper      dao对象
     * @param methodName  方法名成
     * @param args        方法参数
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String methodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Method mh = ReflectionUtils.findMethod(mapper.getClass(), methodName, pageRequest.getParams().getClass());
        Object result = ReflectionUtils.invokeMethod(mh, mapper, pageRequest.getParams());
        return getPageResult(pageRequest, new PageInfo((List) result));
    }


    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalCount((int) pageInfo.getTotal());
        pageResult.setPageCount(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
