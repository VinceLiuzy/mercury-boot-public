package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.dictionary.AsDictionary;
import com.mercury.crud.entity.AbstractBaseModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/**
 * 系统菜单
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/19 14:50
 **/
@Entity
@Data
@Table(name = "tb_sys_menu", indexes = @Index(columnList = "systemCode"))
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
@AsDictionary(name = "sysMenu", textProp = "name", parentIdProp = "parentId")
public class SysMenu extends AbstractBaseModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    private String menuType;

    /**
     * 所属系统
     */
    @Column(nullable = false)
    private String systemCode;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由
     */
    private String url;

    /**
     * 序号
     */
    private Double sortNo;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否展开
     */
    private Boolean open;
    /**
     * 是否选中
     */
    private Boolean selected;
    /**
     * 是否禁用
     */
    private Boolean disabled;

    @Transient
    private List<SysMenu> children;

}
