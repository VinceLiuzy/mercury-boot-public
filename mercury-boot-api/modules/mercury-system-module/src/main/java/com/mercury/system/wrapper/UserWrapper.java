package com.mercury.system.wrapper;

import com.mercury.crud.validated.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/17 15:23
 **/
@Data
@Schema(name = "UserWrapper", description = "用户实体")
public class UserWrapper {
    @NotBlank(groups = {Update.class})
    @Schema(description = "用户[id]", required = true)
    private String id;

    @NotBlank
    @Schema(description = "用户名称", required = true)
    private String username;

//    @NotBlank(groups = {Select.class})
    @Schema(description = "密码", required = true)
    private String password;

    private String nickName;

    private String phone;
    private String birthday;
    private String email;
    private String sex;
    private String avatar;
    private String orgCode;
    private String status;
}
