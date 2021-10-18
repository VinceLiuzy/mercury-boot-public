package com.mercury.system.wrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/1/31 18:05
 **/
@Data
@Schema(name = "UserRoleWrapper", description = "用户角色")
public class UserRoleWrapper {

    @NotBlank(message = "用户Id不能为空")
    @Schema(description = "用户Id", required = true)
    private String userId;

    @NotBlank(message = "系统编码不能为空")
    @Schema(description = "系统编码", required = true)
    private String systemCode;

    @Schema(description = "角色编码", required = true)
    private List<String> roleCodes = new ArrayList<>();
}
