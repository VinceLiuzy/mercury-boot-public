package com.mercury.system.controller;

import com.mercury.crud.dto.ApiResult;
import com.mercury.crud.dto.IdWrapper;
import com.mercury.system.service.RolePermissionService;
import com.mercury.system.service.RoleService;
import com.mercury.system.wrapper.RolePermissionWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/2/3 11:20
 **/
@RestController
@RequestMapping("/role")
@Validated
@Tag(name = "用户角色管理")
public class RoleController {
    @Resource(name = "roleService")
    RoleService roleService;

    @Resource(name = "rolePermissionService")
    RolePermissionService rolePermissionService;

    @PostMapping(value = "/saveRolePermission")
    @Operation(summary = "保存角色权限", description = "保存角色权限", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult saveRolePermission(@RequestBody @Validated RolePermissionWrapper wrapper) {
        roleService.saveRolePermission(wrapper.getRoleId(), wrapper.getMenuList());

        return ApiResult.success();
    }

    @PostMapping(value = "/queryRolePermission")
    @Operation(summary = "查询角色权限", description = "查询角色权限", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult queryRolePermission(@RequestBody @Validated IdWrapper wrapper) {
        return ApiResult.success(rolePermissionService.findByRoleId(wrapper.getId()));
    }
}
