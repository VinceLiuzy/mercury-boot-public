package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.dictionary.AsDictionary;
import com.mercury.crud.entity.AbstractBaseModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 子系统
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/10/12 16:30
 **/
@Entity
@Data
@Table(name = "tb_sub_system")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
@AsDictionary(name = "subSystem", textProp = "name", valueProp = "systemCode")
public class SubSystem extends AbstractBaseModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 系统名称
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * 系统编号
     */
    @Column(unique = true, nullable = false)
    private String systemCode;

    /**
     * 描述
     */
    private String description;
}
