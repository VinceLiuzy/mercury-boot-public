package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.dictionary.AsDictionary;
import com.mercury.crud.entity.AbstractBaseModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 系统角色
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/30 10:59
 **/
@Entity
@Data
@Table(name = "tb_sys_role")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
@AsDictionary(name = "role", textProp = "roleName", valueProp = "roleCode")
public class SysRole extends AbstractBaseModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 角色名称
     */
    @Column(nullable = false)
    private String roleName;

    /**
     * 角色编码
     */
    @Column(nullable = false, unique = true)
    private String roleCode;

    /**
     * 所属系统
     */
    private String systemCode;

    /**
     * 描述
     */
    private String description;
}
