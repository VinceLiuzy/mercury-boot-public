package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractBaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 字典项
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/11/8 13:46
 **/
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_sys_dict_item")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDictItem extends AbstractBaseModel<String> {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典编号
     */
    private String dictCode;

    /**
     * 字典项标签
     */
    @Column(nullable = false)
    private String itemText;

    /**
     * 字典项值
     */
    @Column(nullable = false)
    private String itemValue;

    /**
     * 字典项描述
     */
    private String description;

    /**
     * 字典项序号
     */
    private Integer sortNo;

    /**
     * 字典项值状态
     */
    private Boolean status = true;

}
