package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysLoginLog;

import java.util.List;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(String id);

    List<SysLoginLog> selectAll();

    int updateByPrimaryKey(SysLoginLog record);
}