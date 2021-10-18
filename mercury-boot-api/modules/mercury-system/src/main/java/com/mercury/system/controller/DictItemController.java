package com.mercury.system.controller;

import com.mercury.crud.dto.ApiParameter;
import com.mercury.crud.dto.ApiResult;
import com.mercury.system.service.DictItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/10 23:31
 **/
@RestController
@RequestMapping("/dictItem")
@Tag(name = "字典项")
public class DictItemController {
    @Resource(name = "dictItemService")
    private DictItemService dictItemService;

    @PostMapping(value = "/findPage")
    @Operation(summary = "分页查询", description = "分页查询", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult findPage(@RequestBody @Validated ApiParameter apiParameter) {
        return ApiResult.success(Arrays.asList(dictItemService.findPage(apiParameter)));
    }
}
