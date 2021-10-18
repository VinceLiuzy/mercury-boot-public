package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/26 16:42
 **/
@Repository("roleDao")
public interface RoleDao extends IBaseRepository<SysRole, String> {
}
