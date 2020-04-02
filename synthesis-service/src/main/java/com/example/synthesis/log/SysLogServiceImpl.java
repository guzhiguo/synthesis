package com.example.synthesis.log;

import com.example.synthesis.dao.SysLogMapper;
import com.example.synthesis.pojo.SysLog;
import com.example.synthesis.service.request.PageRequest;
import com.example.synthesis.service.response.PageResult;
import com.example.synthesis.utils.ObjectConvert2MapUtils;
import com.example.synthesis.utils.PageQueryHelper;
import com.example.synthesis.utils.UUIDUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: bill
 * @create: 2020/04/02 20:20
 */
@Service(value = "sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public String save(SysLog record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysLogMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysLogMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysLog record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysLogMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysLogMapper);
    }
}
