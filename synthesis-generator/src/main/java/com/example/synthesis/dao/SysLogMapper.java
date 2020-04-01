package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysLog;
import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(String id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}