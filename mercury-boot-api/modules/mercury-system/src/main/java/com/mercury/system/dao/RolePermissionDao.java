package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysRolePermission;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/2/3 11:15
 **/
@Repository("rolePermissionDao")
public interface RolePermissionDao extends IBaseRepository<SysRolePermission, String> {
}
