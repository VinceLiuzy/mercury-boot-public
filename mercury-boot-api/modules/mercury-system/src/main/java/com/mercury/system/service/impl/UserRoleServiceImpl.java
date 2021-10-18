package com.mercury.system.service.impl;

import com.mercury.crud.dto.ApiFilter;
import com.mercury.crud.dto.ApiOperator;
import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.UserRoleDao;
import com.mercury.system.entity.SysUserRole;
import com.mercury.system.service.UserRoleService;
import com.mercury.system.wrapper.UserRoleWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/1/31 18:56
 **/
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, SysUserRole, String> implements UserRoleService {

    @Override
    public void roleAssign(UserRoleWrapper wrapper) {
        List<SysUserRole> list = findAll(new ArrayList<>() {{
            add(ApiFilter.getInstance("systemCode", wrapper.getSystemCode(), ApiOperator.EQ));
            add(ApiFilter.getInstance("userId", wrapper.getUserId(), ApiOperator.EQ));
        }});

        if (list.size() > 0) {
            deleteAll(list);
        }

        if (wrapper.getRoleCodes().size() > 0) {
            List<SysUserRole> userRoles = new ArrayList<>();

            for (String roleCode : wrapper.getRoleCodes()) {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleCode(roleCode);
                userRole.setUserId(wrapper.getUserId());
                userRole.setSystemCode(wrapper.getSystemCode());

                userRoles.add(userRole);
            }

            saveAll(userRoles);
        }
    }
}
