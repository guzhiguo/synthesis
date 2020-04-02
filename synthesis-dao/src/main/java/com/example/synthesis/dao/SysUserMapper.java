package com.example.synthesis.dao;

import com.example.synthesis.common.BaseMapper;
import com.example.synthesis.pojo.SysUser;

import java.util.HashMap;

public interface SysUserMapper extends BaseMapper {

    /**
     * 根据名称查询用户
     *
     * @param params
     * @return
     */
    SysUser findByName(HashMap<String, String> params);
}