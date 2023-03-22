package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractLogicDeleteModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 字典
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/10/12 17:09
 **/
@Entity
@Data
@Table(name = "tb_sys_dict")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDict extends AbstractLogicDeleteModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 所属系统
     */
    private String systemCode;

    /**
     * 字典名称
     */
    @Column(nullable = false)
    private String dictName;

    /**
     * 字典编号
     */
    @Column(nullable = false, unique = true)
    private String dictCode;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 字典类型
     * {0: string, 1: number}
     */
    private Integer type;
}
