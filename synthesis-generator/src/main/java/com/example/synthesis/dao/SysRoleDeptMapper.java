package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysRoleDept;
import java.util.List;

public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(String id);

    List<SysRoleDept> selectAll();

    int updateByPrimaryKey(SysRoleDept record);
}