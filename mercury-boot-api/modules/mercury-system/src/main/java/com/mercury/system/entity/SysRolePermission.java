package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractBaseModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 角色功能权限
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/30 11:09
 **/
@Entity
@Data
@Table(name = "tb_sys_role_permission")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysRolePermission extends AbstractBaseModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    @Column(nullable = false)
    private String roleId;

    @Column(nullable = false)
    private String menuId;

    /**
     * 资源类型
     */
//    @Column(nullable = false)
//    private String resourceType;
}
