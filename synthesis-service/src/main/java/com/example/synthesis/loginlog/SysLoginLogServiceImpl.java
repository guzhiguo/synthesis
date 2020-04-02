package com.example.synthesis.loginlog;

import com.example.synthesis.dao.SysLoginLogMapper;
import com.example.synthesis.pojo.SysLoginLog;
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
 * @create: 2020/04/02 20:15
 */
@Service(value = "sysLoginLogService")
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public String save(SysLoginLog record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysLoginLogMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysLoginLogMapper.deleteByPrimaryKey(params);
        return params.get("id");
    }

    @Override
    public String update(SysLoginLog record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysLoginLogMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysLoginLogMapper);
    }
}
