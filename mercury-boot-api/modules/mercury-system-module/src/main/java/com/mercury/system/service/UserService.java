package com.mercury.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercury.crud.dto.ApiParameter;
import com.mercury.crud.service.BaseService;
import com.mercury.system.entity.SysUser;
import com.mercury.system.wrapper.UserWrapper;
import org.springframework.data.domain.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/10 19:24
 **/
public interface UserService extends BaseService<SysUser, String> {

    Page<SysUser> findByRoleCoe(ApiParameter apiParameter, String roleCode);

    SysUser findByUserName(String username);

    SysUser register(UserWrapper user) throws JsonProcessingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    void resetPassword(SysUser user);

    List<SysUser> joinSelect();
}
