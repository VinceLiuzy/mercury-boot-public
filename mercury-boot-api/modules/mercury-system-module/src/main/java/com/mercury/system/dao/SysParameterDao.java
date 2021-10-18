package com.mercury.system.dao;

import com.mercury.crud.repository.IBaseRepository;
import com.mercury.system.entity.SysParameter;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/2/5 10:22
 **/
@Repository("sysParameterDao")
public interface SysParameterDao extends IBaseRepository<SysParameter, String> {
}
