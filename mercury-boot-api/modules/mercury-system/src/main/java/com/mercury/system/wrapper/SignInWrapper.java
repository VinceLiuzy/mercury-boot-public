package com.mercury.system.wrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.sql.Select;

import javax.validation.constraints.NotBlank;

/**
 * 登陆
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/10/22 15:11
 **/
@Data
@Schema(name = "SignInWrapper", description = "ID封装类")
public class SignInWrapper {
    @NotBlank
    @Schema(description = "用户名称", required = true)
    private String username;

    @NotBlank(groups = {Select.class})
    @Schema(description = "密码", required = true)
    private String password;
}
