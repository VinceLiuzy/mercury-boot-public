package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractLogicDeleteModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 分类字典
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/12/2 14:19
 **/
@Entity
@Data
@Table(name = "tb_sys_category")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysCategory extends AbstractLogicDeleteModel {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 分类名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 拥有下级
     */
    private Integer hasChild;

    /**
     * 父id
     */
    private String parentId;
}
