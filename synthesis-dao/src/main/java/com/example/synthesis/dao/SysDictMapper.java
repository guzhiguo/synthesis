package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysDict;

import java.util.List;

public interface SysDictMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDict record);

    SysDict selectByPrimaryKey(String id);

    List<SysDict> selectAll();

    int updateByPrimaryKey(SysDict record);
}