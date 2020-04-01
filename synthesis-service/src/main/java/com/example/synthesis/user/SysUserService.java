package com.example.synthesis.user;

import com.example.synthesis.pojo.SysUser;
import com.example.synthesis.service.CurdService;

/**
 * @author: bill
 * @create: 2020/03/21 17:14
 */
public interface SysUserService extends CurdService<SysUser> {

    SysUser findByName(String name);
}
