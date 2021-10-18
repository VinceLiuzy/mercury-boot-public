package com.mercury.system.service.impl;

import com.mercury.crud.dto.ApiFilter;
import com.mercury.crud.dto.ApiOperator;
import com.mercury.crud.dto.ApiSaveParam;
import com.mercury.crud.exception.CustomerException;
import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.RoleDao;
import com.mercury.system.entity.SysRole;
import com.mercury.system.entity.SysRolePermission;
import com.mercury.system.enums.MercuryExceptionEnum;
import com.mercury.system.service.RolePermissionService;
import com.mercury.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/26 16:43
 **/
@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, SysRole, String> implements RoleService {
    @Resource(name = "rolePermissionService")
    RolePermissionService rolePermissionService;

    @Override
    public SysRole beforeSave(ApiSaveParam apiSaveParam) {
        SysRole t = super.beforeSave(apiSaveParam);

        Optional<SysRole> opt = this.findOne(new ArrayList<ApiFilter>() {{
            add(ApiFilter.getInstance("roleCode", t.getRoleCode(), ApiOperator.EQ));
            add(ApiFilter.getInstance("id", t.getId(), ApiOperator.NE));
        }});

        if (opt.isPresent()) {
            throw new CustomerException(MercuryExceptionEnum.DUPLICATE_ROLECODE);
        }

        return t;
    }

    @Override
    public void saveRolePermission(String roleId, List<String> menuList) {
        List<SysRolePermission> list = rolePermissionService.findByRoleId(roleId);
        if (list.size() > 0) {
            // 删除已有角色菜单权限
            rolePermissionService.deleteAll(list);
        }

        if (null != menuList && menuList.size() > 0) {
            // 新增权限
            List<SysRolePermission> rolePermissions = new ArrayList<>();
            menuList.forEach(menuId -> {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setMenuId(menuId);

                rolePermissions.add(rolePermission);
            });

            rolePermissionService.saveAll(rolePermissions);
        }
    }

}
