package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysDict;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/8 18:03
 **/
@Repository("dictDao")
public interface DictDao extends IBaseRepository<SysDict, String> {
}
