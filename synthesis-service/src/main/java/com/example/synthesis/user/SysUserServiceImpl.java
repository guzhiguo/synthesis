package com.example.synthesis.user;

import com.example.synthesis.dao.SysUserMapper;
import com.example.synthesis.pojo.SysUser;
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
 * @create: 2020/03/21 17:15
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String save(SysUser record) {
        HashMap<String, Object> params = ObjectConvert2MapUtils.convertOfObject(record);
        params.put("userId", UUIDUtils.getUUID());
        sysUserMapper.insert(params);
        return String.valueOf(params.get("userId"));
    }

    @Override
    public String delete(String id) {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        sysUserMapper.deleteByPrimaryKey(params);
        return id;
    }

    @Override
    public String update(SysUser record) {
        HashMap<String, Object> params = ObjectConvert2MapUtils.convertOfObject(record);
//        HashMap<String, Object> params = Maps.newHashMap();
//        params.put("id", record.getId());
//        params.put("name", record.getName());
//        params.put("nickName", record.getNickName());
//        params.put("avatar", record.getAvatar());
//        params.put("password", record.getPassword());
//        params.put("salt", record.getSalt());
//        params.put("email", record.getEmail());
//        params.put("mobile", record.getMobile());
//        params.put("deptId", record.getDeptId());
//        params.put("lastUpdateBy", record.getLastUpdateBy());
        sysUserMapper.updateByPrimaryKey(params);
        return record.getUserId();
    }

    @Override
    public SysUser findByName(String name) {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("name", name);
        return sysUserMapper.findByName(map);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageQueryHelper.findPage(pageRequest, sysUserMapper);
    }
}
