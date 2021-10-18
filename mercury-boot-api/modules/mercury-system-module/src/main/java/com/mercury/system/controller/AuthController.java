package com.mercury.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.crud.constant.GlobalConst;
import com.mercury.crud.dto.ApiResult;
import com.mercury.crud.exception.CustomerException;
import com.mercury.crud.exception.ExceptionEnum;
import com.mercury.system.entity.SysUser;
import com.mercury.system.enums.MercuryExceptionEnum;
import com.mercury.system.service.UserService;
import com.mercury.system.wrapper.SignInWrapper;
import com.mercury.system.wrapper.UserWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/10 19:59
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "用户认证")
public class AuthController {
    @Resource(name = "userService")
    private UserService userService;

    @PostMapping(value = "/signIn")
    @Operation(summary = "用户登陆", description = "用户登陆")
    public ApiResult signIn(@RequestBody @Validated SignInWrapper user, HttpSession session) throws JsonProcessingException {
        SysUser loginUser = userService.findByUserName(user.getUsername());

        if (loginUser != null) {
            if (loginUser.getPassword().equals(DigestUtils.sha256Hex(user.getPassword() + loginUser.getSalt()))) {
                log.info("用户[" + loginUser.getUsername() + "]登陆成功");

                ObjectMapper mapper = new ObjectMapper();
                session.setAttribute(GlobalConst.CURRENT_USER, mapper.readValue(mapper.writeValueAsString(loginUser), Map.class));

                return ApiResult.success("登陆成功", Arrays.asList(loginUser));
            }
        }

        throw new CustomerException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
    }

    @PostMapping(value = "/register")
    @Operation(summary = "用户注册", description = "用户注册", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult register(@RequestBody @Validated UserWrapper user) {
        try {
            return ApiResult.success(Arrays.asList(userService.register(user)));
        } catch (Exception e) {
            throw new CustomerException(MercuryExceptionEnum.REGISTER_FAIL, e);
        }
    }

    @PostMapping(value = "/reLogin")
    @Operation(summary = "重新登陆", description = "重新登陆")
    public ApiResult reLogin() {
        return ApiResult.error(ExceptionEnum.UNAUTHORIZED);
    }

    @PostMapping(value = "/signOut")
    @Operation(summary = "用户注销", description = "用户注销", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult signOut(HttpSession session) {
        session.invalidate();
        return ApiResult.success("用户注销成功");
    }
}
