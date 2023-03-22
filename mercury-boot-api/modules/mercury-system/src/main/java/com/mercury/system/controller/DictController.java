package com.mercury.system.controller;

import com.mercury.crud.dto.ApiParameter;
import com.mercury.crud.dto.ApiResult;
import com.mercury.system.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/8 17:53
 **/
@RestController
@RequestMapping("/dict")
@Tag(name = "系统数据字典")
public class DictController {
    @Resource(name = "dictService")
    private DictService dictService;

    @PostMapping(value = "/findPage")
    @Operation(summary = "分页查询", description = "分页查询", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult findPage(@RequestBody @Validated ApiParameter apiParameter) {
        return ApiResult.success(Arrays.asList(dictService.findPage(apiParameter)));
    }

    @PostMapping(value = "/findByCode")
    @Operation(summary = "根据字典Code查询", description = "根据字典Code查询", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult findByCode(@RequestBody @Validated CodeWrapper wrapper) {
        return ApiResult.success(dictService.findByDictCode(wrapper.getCode()));
    }

    @Data
    static class CodeWrapper {
        @NotBlank(message = "字典编码不能为空")
        String code;
    }
}
