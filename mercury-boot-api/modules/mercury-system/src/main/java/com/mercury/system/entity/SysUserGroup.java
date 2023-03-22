package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractBaseModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 角色组
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/12/3 18:10
 **/
@Entity
@Data
@Table(name = "tb_sys_user_group")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserGroup extends AbstractBaseModel {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 角色组名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 描述
     */
    private String description;
}
