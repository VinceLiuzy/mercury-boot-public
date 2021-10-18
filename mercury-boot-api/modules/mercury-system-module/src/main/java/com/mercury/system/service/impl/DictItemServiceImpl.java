package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.DictItemDao;
import com.mercury.system.entity.SysDictItem;
import com.mercury.system.service.DictItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/10 23:30
 **/
@Service("dictItemService")
@Transactional
public class DictItemServiceImpl extends BaseServiceImpl<DictItemDao, SysDictItem, String> implements DictItemService {
}
