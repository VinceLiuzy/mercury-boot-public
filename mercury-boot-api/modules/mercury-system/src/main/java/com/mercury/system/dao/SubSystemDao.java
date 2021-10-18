package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SubSystem;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/12/5 00:40
 **/
@Repository("subSystemDao")
public interface SubSystemDao extends IBaseRepository<SubSystem, String> {
}
