package com.mercury.system.service;

import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysRolePermission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/2/3 11:16
 **/
public interface RolePermissionService extends BaseService<SysRolePermission, String> {
    @Transactional(readOnly = true)
    List<SysRolePermission> findByRoleId(String roleId);
}
