package com.mercury.system.controller;

import com.mercury.crud.dto.ApiParameter;
import com.mercury.crud.dto.ApiResult;
import com.mercury.crud.dto.IdWrapper;
import com.mercury.crud.exception.CustomerException;
import com.mercury.crud.exception.ExceptionEnum;
import com.mercury.system.entity.SysUser;
import com.mercury.system.enums.MercuryExceptionEnum;
import com.mercury.system.service.UserRoleService;
import com.mercury.system.service.UserService;
import com.mercury.system.wrapper.UserRoleWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/10/27 00:55
 **/
@RestController
@RequestMapping("/user")
@Validated
@Tag(name = "用户管理")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    @PostMapping(value = "/findPage")
    @Operation(summary = "分页查询", description = "分页查询", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult findPage(@RequestBody @Validated ApiParameter apiParameter) {
        return ApiResult.success(Arrays.asList(userService.findPage(apiParameter)));
    }

    @PostMapping(value = "/deleteByIds")
    @Operation(summary = "根据[id]删除", description = "根据[id]删除", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult deleteByIds(@RequestBody @Size(min = 1) List<String> ids) {
        userService.LogicDeleteByIds(ids);

        return ApiResult.success();
    }

    @PostMapping(value = "/roleAssign")
    @Operation(summary = "用户角色分配", description = "用户角色分配", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult roleAssign(@RequestBody @Validated UserRoleWrapper wrapper) {
        userRoleService.roleAssign(wrapper);

        return ApiResult.success();
    }

    @PostMapping(value = "/resetPassword")
    @Operation(summary = "重置密码", description = "重置密码", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult resetPassword(@RequestBody @Validated IdWrapper wrapper) {
        Optional<SysUser> opt = userService.findById(wrapper.getId());

        if (!opt.isPresent()) {
            throw new CustomerException(MercuryExceptionEnum.USER_NOT_EXIST);
        }

        userService.resetPassword(opt.get());

        return ApiResult.success();
    }

    @PostMapping(value = "/findPageByRoleCoe")
    @Operation(summary = "根据[roleCode]分页查询用户", description = "根据[roleCode]分页查询用户",
            security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult findPageByRoleCoe(@RequestBody @Validated ApiParameter apiParameter) {
        String roleCode = apiParameter.getExtraData() == null ? null
                : ((Map<String, String>) apiParameter.getExtraData()).get("roleCode");

        if (StringUtils.isBlank(roleCode)) {
            throw new CustomerException(ExceptionEnum.INVALID_PARAM_ERROR);
        }

        return ApiResult.success(Arrays.asList(userService.findByRoleCoe(apiParameter, roleCode)));
    }

    @PostMapping(value = "/test")
    @Operation(summary = "test", description = "test", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult test() {
        return ApiResult.success(userService.joinSelect());
    }
}
