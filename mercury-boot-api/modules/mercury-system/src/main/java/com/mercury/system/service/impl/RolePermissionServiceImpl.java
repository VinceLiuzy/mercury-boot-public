package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.RolePermissionDao;
import com.mercury.system.entity.QSysRolePermission;
import com.mercury.system.entity.SysRolePermission;
import com.mercury.system.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/2/3 11:16
 **/
@Service("rolePermissionService")
@Transactional
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionDao, SysRolePermission, String>
        implements RolePermissionService {
    private QSysRolePermission qSysRolePermission = QSysRolePermission.sysRolePermission;

    @Override
    @Transactional(readOnly = true)
    public List<SysRolePermission> findByRoleId(String roleId) {
        return queryFactory.selectFrom(qSysRolePermission)
                .where(qSysRolePermission.roleId.eq(roleId))
                .fetch();
    }
}
