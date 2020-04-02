package com.example.synthesis.dict;

import com.example.synthesis.dao.SysDictMapper;
import com.example.synthesis.pojo.SysDict;
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
 * @create: 2020/04/02 20:10
 */
@Service(value = "sysDictService")
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public String save(SysDict record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysDictMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysDictMapper.deleteByPrimaryKey(params);
        return params.get("id");
    }

    @Override
    public String update(SysDict record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysDictMapper.updateByPrimaryKey(params);
        return params.get("id");
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysDictMapper);
    }
}
