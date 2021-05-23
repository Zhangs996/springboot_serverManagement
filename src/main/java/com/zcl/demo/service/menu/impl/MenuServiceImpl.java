package com.zcl.demo.service.menu.impl;

import com.zcl.demo.dao.menu.MenuDao;
import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.service.menu.MenuService;
import com.zcl.demo.util.DateInFo;
import com.zcl.demo.util.MapperFactoryUtil;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.menu.MenuVo;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;

    MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /**
     * 展现树形结构
     *
     * @return
     */
    @Override
    public List<MenuVo> queryTreeAll() {
        List<Menu> menus = menuDao.queryAll();
        MapperFactory mapperFactory = MapperFactoryUtil.initMapper();
        mapperFactory.classMap(Menu.class, MenuVo.class)
                .field("mId", "id")
                .field("pMenu", "pId")
                .field("mName", "name")
                .byDefault().register();
        List<MenuVo> menuVos = mapperFactory.getMapperFacade().mapAsList(menus, MenuVo.class);
        menuVos.stream().forEach(menuVo -> {
            if ("0".equals(menuVo.getpId()) || "0".equals(menuVo.getId())) {
                menuVo.setOpen(true);
            }
        });
        return menuVos;
    }

    /**
     * 根据id返回中文描述
     *
     * @param treeId
     * @return
     */
    @Override
    public String queryNodeNameById(String treeId) {
        return menuDao.queryNodeNameById(treeId);
    }

    /**
     * 添加url
     *
     * @param menu
     * @return
     */
    @Override
    public void addUrl(Menu menu) {
        menuDao.addUrl(menu);
    }

    /**
     * 菜单删除
     *
     * @param mId
     */
    @Override
    public void removeUrl(String mId) {
        menuDao.removeUrl(mId);
    }

    /**
     * 根据mid查找menu
     *
     * @param mId
     * @return
     */
    @Override
    public Menu selectMenuByMid(String mId) {
        Menu menu = menuDao.selectMenuByMid(mId);
        return menu;
    }

    /**
     * 根据mid修改菜单
     *
     * @param menu
     * @return
     */
    @Override
    public void updateUrl(Menu menu) {
        String userName = (String) SessionUtil.getSessionAttribute("userName");
        menu.setUpdateTime(DateInFo.dateFomart());
        menu.setUpdateUser(userName);
        menuDao.updateUrl(menu);
    }
}
