package com.mercury.system.service;

import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysUserRole;
import com.mercury.system.wrapper.UserRoleWrapper;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/1/31 18:56
 **/
public interface UserRoleService extends BaseService<SysUserRole, String> {

    void roleAssign(UserRoleWrapper wrapper);
}
