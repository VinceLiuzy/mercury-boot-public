package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysUserRole;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/1/31 18:44
 **/
@Repository("userRoleDao")
public interface UserRoleDao extends IBaseRepository<SysUserRole, String> {
}
