package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysDept;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(String id);

    List<SysDept> selectAll();

    int updateByPrimaryKey(SysDept record);
}