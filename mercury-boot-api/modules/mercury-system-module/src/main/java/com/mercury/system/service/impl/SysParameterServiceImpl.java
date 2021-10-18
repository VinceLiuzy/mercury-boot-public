package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.SysParameterDao;
import com.mercury.system.entity.SysParameter;
import com.mercury.system.service.SysParameterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/2/5 10:23
 **/
@Service("sysParameterService")
@Transactional
public class SysParameterServiceImpl extends BaseServiceImpl<SysParameterDao, SysParameter, String>
        implements SysParameterService {
}
