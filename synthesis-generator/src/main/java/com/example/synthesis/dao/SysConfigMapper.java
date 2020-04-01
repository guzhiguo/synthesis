package com.example.synthesis.dao;

import com.example.synthesis.pojo.SysConfig;
import java.util.List;

public interface SysConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysConfig record);

    SysConfig selectByPrimaryKey(String id);

    List<SysConfig> selectAll();

    int updateByPrimaryKey(SysConfig record);
}