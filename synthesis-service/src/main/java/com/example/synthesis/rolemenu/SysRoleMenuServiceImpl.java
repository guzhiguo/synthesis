package com.example.synthesis.rolemenu;

import com.example.synthesis.dao.SysRoleMenuMapper;
import com.example.synthesis.pojo.SysRoleMenu;
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
 * @create: 2020/04/02 20:33
 */
@Service(value = "sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public String save(SysRoleMenu record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysRoleMenuMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysRoleMenuMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysRoleMenu record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysRoleMenuMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysRoleMenuMapper);
    }
}
