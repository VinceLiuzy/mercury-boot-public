package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.SubSystemDao;
import com.mercury.system.entity.SubSystem;
import com.mercury.system.service.SubSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/12/5 00:41
 **/
@Service("subSystemService")
@Transactional
public class SubSystemServiceImpl extends BaseServiceImpl<SubSystemDao, SubSystem, String> implements SubSystemService {
}
