package com.example.synthesis.role;

import com.example.synthesis.dao.SysRoleMapper;
import com.example.synthesis.pojo.SysRole;
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
 * @create: 2020/04/02 20:30
 */
@Service(value = "sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public String save(SysRole record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysRoleMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysRoleMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysRole record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysRoleMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysRoleMapper);
    }
}
