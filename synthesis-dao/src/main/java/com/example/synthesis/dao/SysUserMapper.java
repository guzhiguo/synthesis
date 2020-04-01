package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysUser;

import java.util.HashMap;
import java.util.List;

public interface SysUserMapper {

    int deleteByPrimaryKey(HashMap<String, Object> params);

    int insert(HashMap<String, Object> params);

    SysUser selectByPrimaryKey(HashMap<String, Object> params);

    List<SysUser> selectAll();

    /**
     * 更新接口 变更数据不包括status,creator
     * @param params
     * @return
     */
    int updateByPrimaryKey(HashMap<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<SysUser> findPage(HashMap<String, String> params);

    /**
     * 根据名称查询用户
     * @param params
     * @return
     */
    SysUser findByName(HashMap<String,String> params);
}