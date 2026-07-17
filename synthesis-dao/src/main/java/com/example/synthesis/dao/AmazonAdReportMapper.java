package com.example.synthesis.dao;

import com.example.synthesis.pojo.AmazonAdReport;

import java.util.List;

/**
 * 亚马逊广告报告数据 Mapper
 */
public interface AmazonAdReportMapper {

    /** 批量插入 */
    int batchInsert(List<AmazonAdReport> list);

    /** 清空表数据（全量覆盖前使用） */
    int deleteAll();
}
