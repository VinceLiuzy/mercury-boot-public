package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/10 19:26
 **/
@Repository("userDao")
public interface UserDao extends IBaseRepository<SysUser, String> {
}
