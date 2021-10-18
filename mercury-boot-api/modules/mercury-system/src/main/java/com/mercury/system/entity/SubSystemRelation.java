package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercury.crud.dictionary.AsDictionary;
import com.mercury.crud.entity.AbstractLogicDeleteModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/12/6 14:23
 **/
@Entity
@Data
@Table(name = "tb_sub_system_relation")
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
@AsDictionary(name = "subSystem", textProp = "name", valueProp = "systemCode")
public class SubSystemRelation extends AbstractLogicDeleteModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    private String systemId;
    private String userId;
    private String roleId;
    private String menuId;
}
