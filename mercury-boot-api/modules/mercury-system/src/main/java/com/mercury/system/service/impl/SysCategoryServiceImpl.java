package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.SysCategoryDao;
import com.mercury.system.entity.SysCategory;
import com.mercury.system.service.SysCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/12/2 14:25
 **/
@Service("sysCategoryService")
@Transactional
public class SysCategoryServiceImpl extends BaseServiceImpl<SysCategoryDao, SysCategory, String>
        implements SysCategoryService {
}
