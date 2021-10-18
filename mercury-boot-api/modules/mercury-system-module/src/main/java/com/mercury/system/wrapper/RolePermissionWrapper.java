package com.mercury.system.wrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色权限
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/2/3 11:23
 **/
@Data
@Schema(name = "RolePermissionWrapper", description = "用户角色权限")
public class RolePermissionWrapper {
    @NotBlank
    @Schema(description = "用户角色Id", required = true)
    private String roleId;

    @Schema(description = "菜单列表", required = true)
    private List<String> menuList = new ArrayList<>();
}
