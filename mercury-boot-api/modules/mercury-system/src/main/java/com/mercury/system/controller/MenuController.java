package com.mercury.system.controller;

import com.mercury.crud.dto.ApiDeleteParam;
import com.mercury.crud.dto.ApiResult;
import com.mercury.system.entity.QSysMenu;
import com.mercury.system.entity.SysMenu;
import com.mercury.system.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/2/20 10:54
 **/
@Slf4j
@RestController
@RequestMapping("/menu")
@Tag(name = "菜单")
public class MenuController {

    @Resource(name = "menuService")
    private MenuService menuService;

    @PostMapping(value = "/deleteByIds")
    @Operation(summary = "根据[id]删除", description = "根据[id]删除", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult deleteByIds(@RequestBody @Validated ApiDeleteParam apiDeleteParam) {
        menuService.deleteByIds(apiDeleteParam.getIds());

        return ApiResult.success();
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询用户菜单", description = "查询用户菜单", security = {@SecurityRequirement(name = "Authorization")})
    public ApiResult list() {
        QSysMenu qSysMenu = QSysMenu.sysMenu;
        List<SysMenu> list = (List<SysMenu>) menuService.findAll(qSysMenu.sortNo.asc());

        Map<String, Map<String, Object>> map = list.stream()
                .collect(LinkedHashMap::new, (m, v) -> m.put(v.getId(), new LinkedHashMap<>() {{
                            put("id", v.getId());
                            put("parentId", v.getParentId());
                            put("level", StringUtils.split(v.getPath(), "-").length);
                            put("title", v.getName());
                            put("url", v.getUrl());
                            put("icon", v.getIcon());
                            put("open", v.getOpen());
                            put("selected", v.getSelected());
                            put("disabled", v.getDisabled());
                            put("children", v.getChildren());
                            put("sortNo", v.getSortNo());
                        }}
                ), Map::putAll);

        map.forEach((k, v) -> {
            if (null != map.get(k).get("parentId") && null != map.get(map.get(k).get("parentId"))) {
                if (map.get(map.get(k).get("parentId")).get("children") == null) {
                    map.get(map.get(k).get("parentId")).put("children", new ArrayList<>());
                }

                ((List) map.get(map.get(k).get("parentId")).get("children")).add(map.get(k));
            }
        });

        Map<String, Object> res = new HashMap<>();
        res.put("userMenus", map.values().stream()
                .filter(item -> null == item.get("parentId"))
                .toList());
        res.put("rawMenus", map);

        return ApiResult.success(res);
    }
}
