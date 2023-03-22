package com.mercury.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercury.crud.entity.AbstractLogicDeleteModel;
import com.mercury.system.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * 用户表
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/10 17:20
 **/
@Entity
@Data
@Table(name = "tb_sys_user", indexes = {
        @Index(columnList = "lastModifiedTime")
})
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUser extends AbstractLogicDeleteModel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.mercury.crud.entity.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String id;

    /**
     * 用户账号
     */
    @Column(name = "user_name", unique = true, nullable = false, updatable = false)
    private String username;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickname;

    /**
     * 用户密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    /**
     * 盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    @Column(length = 2)
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 所属部门
     */
    private String deptCode;

    /**
     * 用户状态
     */
    @Column(length = 2)
    private String userStatus = UserStatusEnum.NORMAL.getCode();
}
