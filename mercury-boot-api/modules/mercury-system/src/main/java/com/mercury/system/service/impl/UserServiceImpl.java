package com.mercury.system.service.impl;

import com.mercury.crud.dto.ApiParameter;
import com.mercury.crud.dto.ApiSaveParam;
import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.constant.MercuryConst;
import com.mercury.system.dao.UserDao;
import com.mercury.system.entity.QSubSystemRelation;
import com.mercury.system.entity.QSysUser;
import com.mercury.system.entity.QSysUserRole;
import com.mercury.system.entity.SysUser;
import com.mercury.system.enums.UserStatusEnum;
import com.mercury.system.service.UserService;
import com.mercury.system.wrapper.UserWrapper;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.List;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/10 19:24
 **/
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDao, SysUser, String> implements UserService {
    private QSysUser qSysUser = QSysUser.sysUser;

    @Override
    public SysUser beforeSave(ApiSaveParam apiSaveParam) {
        SysUser t = super.beforeSave(apiSaveParam);

        if (StringUtils.isNotBlank(t.getId())) {
            SysUser u = baseDao.getById(t.getId());
            t.setPassword(u.getPassword());
            t.setSalt(u.getSalt());
        } else {
            t.setSalt(getSalt());
            t.setPassword(DigestUtils.sha256Hex(DigestUtils.md5Hex(MercuryConst.DEFAULT_PASSWORD) + t.getSalt()));
        }

        return t;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysUser> findByRoleCoe(ApiParameter apiParameter, String roleCode) {
        QSysUserRole qSysUserRole = QSysUserRole.sysUserRole;
//
        JPAQuery jpaQuery = queryFactory.selectFrom(qSysUser)
                .innerJoin(qSysUserRole)
                .on(qSysUserRole.userId.eq(qSysUser.id).and(qSysUserRole.roleCode.eq(roleCode)));
//                .where(buildPredicate(apiParameter.getGroups()));
//
//        return findPage(jpaQuery, apiParameter.toPage());

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser findByUserName(String username) {
//        Predicate predicate = qSysUser.password.eq(password)
//                .and(qSysUser.userName.eq(userName))
//                .and(qSysUser.status.eq(UserStatus.NORMAL.getCode()))
//                .and(qSysUser.isDel.eq(false));

        return queryFactory.selectFrom(qSysUser)
                .where(qSysUser.username.eq(username)
                        .and(qSysUser.userStatus.eq(UserStatusEnum.NORMAL.getCode()))
                        .and(qSysUser.delFlag.eq(false)))
                .fetchOne();
    }

    @Override
    public SysUser register(UserWrapper wrapper) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        SysUser user = new SysUser();
        PropertyUtils.copyProperties(user, wrapper);

        user.setSalt(getSalt());
        user.setPassword(DigestUtils.sha3_256(user.getPassword() + user.getSalt()).toString());

        return save(user);
    }

    @Override
    public void resetPassword(SysUser user) {
        user.setSalt(getSalt());
        user.setPassword(DigestUtils.sha256Hex(DigestUtils.md5Hex(MercuryConst.DEFAULT_PASSWORD) + user.getSalt()));

        save(user);
    }

    @Override
    public List<SysUser> joinSelect() {
        QSubSystemRelation model = QSubSystemRelation.subSystemRelation;

        return queryFactory.selectFrom(qSysUser)
                .where(qSysUser.id.in(
                        JPAExpressions.selectDistinct(qSysUser.id)
                                .from(qSysUser)
                                .innerJoin(model)
                                .on(model.userId.eq("412346870971772928").and(model.userId.eq(qSysUser.id)))
                ))
                .fetch();
    }

    /**
     * 获取盐值
     *
     * @return
     */
    private String getSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[9];
        random.nextBytes(bytes);

        return encodeBase64String(bytes);
    }
}
