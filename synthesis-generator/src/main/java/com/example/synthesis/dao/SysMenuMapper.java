package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysMenu;
import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);
}