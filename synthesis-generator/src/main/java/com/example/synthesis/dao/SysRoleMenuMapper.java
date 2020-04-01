package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysRoleMenu;
import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    List<SysRoleMenu> selectAll();

    int updateByPrimaryKey(SysRoleMenu record);
}