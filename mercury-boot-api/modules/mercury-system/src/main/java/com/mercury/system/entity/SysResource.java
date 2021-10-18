package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractLogicDeleteModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 资源(表)
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/1/15 09:58
 **/
@Entity
@Data
@Table(name = "tb_sys_resource")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysResource extends AbstractLogicDeleteModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    private String name;
    @Column(unique = true)
    private String className;
    private String systemCode;

}
