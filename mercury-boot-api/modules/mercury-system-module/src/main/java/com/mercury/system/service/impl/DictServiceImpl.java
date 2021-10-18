package com.mercury.system.service.impl;

import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.DictDao;
import com.mercury.system.entity.QSysDict;
import com.mercury.system.entity.QSysDictItem;
import com.mercury.system.entity.SysDict;
import com.mercury.system.service.DictService;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/8 18:02
 **/
@Service("dictService")
@Transactional
public class DictServiceImpl extends BaseServiceImpl<DictDao, SysDict, String> implements DictService {

    @Override
    @Transactional(readOnly = true)
    public List<Tuple> findByDictCode(String code) {
        return queryFactory.select(QSysDict.sysDict, QSysDictItem.sysDictItem)
                .from(QSysDict.sysDict)
                .leftJoin(QSysDictItem.sysDictItem)
                .on(QSysDictItem.sysDictItem.dictId.eq(QSysDict.sysDict.id))
                .where(QSysDict.sysDict.dictCode.eq(code))
                .fetch();
    }
}
