package com.mercury.system.service;


import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysMenu;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/20 11:31
 **/
public interface MenuService extends BaseService<SysMenu, String> {

    /**
     * 根据id删除
     *
     * @param ids
     */
    void deleteByIds(List ids);
}
