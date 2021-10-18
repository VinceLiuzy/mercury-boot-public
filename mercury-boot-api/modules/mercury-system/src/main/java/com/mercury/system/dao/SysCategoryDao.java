package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysCategory;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/12/2 14:25
 **/
@Repository("sysCategoryDao")
public interface SysCategoryDao extends IBaseRepository<SysCategory, String> {
}
