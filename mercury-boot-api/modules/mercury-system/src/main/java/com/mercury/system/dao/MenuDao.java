package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysMenu;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/20 11:30
 **/
@Repository("menuDao")
public interface MenuDao extends IBaseRepository<SysMenu, String> {
}
