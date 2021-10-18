package com.mercury.system.service;


import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysRole;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/26 16:43
 **/
public interface RoleService extends BaseService<SysRole, String> {
    void saveRolePermission(String roleId, List<String> menuList);
}
