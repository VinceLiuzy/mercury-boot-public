package com.mercury.system.service.impl;

import com.mercury.crud.exception.CustomerException;
import com.mercury.crud.service.impl.BaseServiceImpl;
import com.mercury.system.dao.MenuDao;
import com.mercury.system.entity.QSysMenu;
import com.mercury.system.entity.SysMenu;
import com.mercury.system.service.MenuService;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/20 11:31
 **/
@Service("menuService")
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, SysMenu, String> implements MenuService {
    private QSysMenu qSysMenu = QSysMenu.sysMenu;

    @Resource(name = "menuDao")
    MenuDao menuDao;

    @Override
    public SysMenu save(SysMenu sysMenu) {
        if (StringUtils.isBlank(sysMenu.getId())) {
            // 新增
            return _save(sysMenu);
        } else {
            // 更新
            return _update(sysMenu);
        }
    }

    @Override
    public void deleteByIds(List ids) {
        List<SysMenu> list = (List<SysMenu>) findAllById(ids);

        BooleanBuilder builder = new BooleanBuilder();
        list.forEach(item -> {
            builder.or(qSysMenu.path.like(item.getPath() + "-%"));
        });

        List<SysMenu> subs = queryFactory.selectFrom(qSysMenu).where(builder).fetch();

        deleteAll(new ArrayList<SysMenu>() {{
            addAll(list);
            addAll(subs);
        }});
    }

    /**
     * 新增
     *
     * @param sysMenu
     * @return
     */
    private SysMenu _save(SysMenu sysMenu) {
        menuDao.save(sysMenu);

        _setPath(sysMenu);

        return menuDao.save(sysMenu);
    }

    /**
     * 更新
     *
     * @param sysMenu
     * @return
     */
    private SysMenu _update(SysMenu sysMenu) {
        SysMenu oldMenu = new SysMenu();
        // 更新前的数据
        var opt = this.findById(sysMenu.getId());

        if (opt.isPresent()) {
            BeanUtils.copyProperties(opt.get(), oldMenu);
        } else {
            throw new CustomerException("菜单不存在");
        }

        _setPath(sysMenu);

        menuDao.save(sysMenu);

        if (null != oldMenu) {
            // 是否变更了上级路径
            var flag = null == oldMenu.getParentId() ? null != sysMenu.getParentId()
                    : !oldMenu.getParentId().equals(sysMenu.getParentId());

            if (flag) {
                // 查询子菜单
                var list = queryFactory.selectFrom(qSysMenu)
                        .where(qSysMenu.path.like(oldMenu.getPath() + "-%"))
                        .fetch();

                // 修改子菜单path
                for (SysMenu menu : list) {
                    menu.setPath(StringUtils.replace(menu.getPath(), oldMenu.getPath(), sysMenu.getPath()));
                }

                this.saveAll(list);
            }
        }

        return sysMenu;
    }

    /**
     * 设置路径
     *
     * @param sysMenu
     */
    public void _setPath(SysMenu sysMenu) {
        if (StringUtils.isBlank(sysMenu.getParentId())) {
            // 设置根菜单路径
            sysMenu.setPath(sysMenu.getId());
        } else {
            var parent = this.findById(sysMenu.getParentId());

            if (parent.isPresent()) {
                sysMenu.setPath(String.format("%s-%s", parent.get().getPath(), sysMenu.getId()));
            } else {
                throw new CustomerException("父菜单不存在");
            }
        }
    }
}
