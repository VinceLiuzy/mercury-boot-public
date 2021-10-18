package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysDictItem;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/10 23:28
 **/
@Repository("dictItemDao")
public interface DictItemDao extends IBaseRepository<SysDictItem, String> {
}
