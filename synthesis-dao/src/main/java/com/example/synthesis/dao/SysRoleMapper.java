package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(String id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
}