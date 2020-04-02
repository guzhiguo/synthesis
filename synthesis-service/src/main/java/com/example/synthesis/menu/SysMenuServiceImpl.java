package com.example.synthesis.menu;

import com.example.synthesis.dao.SysMenuMapper;
import com.example.synthesis.pojo.SysMenu;
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
 * @create: 2020/04/02 20:23
 */
@Service(value = "sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public String save(SysMenu record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        params.put("id", UUIDUtils.getUUID());
        sysMenuMapper.insert(params);
        return params.get("id");
    }

    @Override
    public String delete(String id) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("id", id);
        sysMenuMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysMenu record) {
        HashMap<String, String> params = ObjectConvert2MapUtils.convertOfString(record);
        sysMenuMapper.updateByPrimaryKey(params);
        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysMenuMapper);
    }
}
