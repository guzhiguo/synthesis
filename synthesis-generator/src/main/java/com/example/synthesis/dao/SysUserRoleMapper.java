package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
}