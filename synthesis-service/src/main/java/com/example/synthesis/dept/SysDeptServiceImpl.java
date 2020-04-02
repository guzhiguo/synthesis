package com.example.synthesis.dept;

import com.example.synthesis.dao.SysDeptMapper;
import com.example.synthesis.pojo.SysDept;
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
 * @create: 2020/04/02 19:50
 */
@Service(value = "sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public String save(SysDept record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysDeptMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysDeptMapper.deleteByPrimaryKey(params);
        return params.get("id");
    }

    @Override
    public String update(SysDept record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysDeptMapper.updateByPrimaryKey(params);
        return params.get("id");
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysDeptMapper);
    }
}
