package com.mercury.system.service;

import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysDict;
import com.querydsl.core.Tuple;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/8 18:02
 **/
public interface DictService extends BaseService<SysDict, String> {
    List<Tuple> findByDictCode(String name);
}
