package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.entity.AbstractBaseModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2020/12/28 12:12
 **/
@Entity
@Data
@Table(name = "tb_sys_department")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDepartment extends AbstractBaseModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;
    /**
     * 部门名称
     */
    @Column(nullable = false)
    private String name;
    /**
     * 部门编号
     */
    @Column(nullable = false)
    private String code;
    /**
     * 字典项序号
     */
    private Integer sortNo;
    /**
     * 描述
     */
    private String description;
    /**
     * 部门领导
     */
    private String deptLeader;
    /**
     * 联系电话
     */
    private String contactNumber;

    private String parentId;
    private String path;
}
