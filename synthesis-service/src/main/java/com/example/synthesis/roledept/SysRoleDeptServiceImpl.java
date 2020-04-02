package com.example.synthesis.roledept;

import com.example.synthesis.dao.SysRoleDeptMapper;
import com.example.synthesis.pojo.SysRoleDept;
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
 * @create: 2020/04/02 20:27
 */
@Service(value = "sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {

    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;

    @Override
    public String save(SysRoleDept record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysRoleDeptMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysRoleDeptMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysRoleDept record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysRoleDeptMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysRoleDeptMapper);
    }
}
