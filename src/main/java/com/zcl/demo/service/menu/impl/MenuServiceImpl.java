package com.zcl.demo.service.menu.impl;

import com.zcl.demo.dao.menu.MenuDao;
import com.zcl.demo.enums.menu.MenuIconEnum;
import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.service.menu.MenuService;
import com.zcl.demo.util.DateInFo;
import com.zcl.demo.util.MapperFactoryUtil;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.menu.MenuCheckVo;
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
        List<MenuVo> menuVos = changeModelToVo(menus);
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

    /**
     * 根据rid查找角色绑定菜单
     *
     * @param rId
     * @return
     */
    @Override
    public List<MenuCheckVo> queryBindMenuByRid(String rId) {
        List<Menu> menus_source = menuDao.queryAll();
        List<Menu> menus = menuDao.queryBindMenuByRid(rId);
        //两集合交集需要checked
        List<MenuCheckVo> menuCheckVos = changeModelToCheckVo(menus_source);
        menuCheckVos.stream().forEach(menuCheckVo -> {
            for (Menu m :
                    menus) {
                if (m.getmId().equals(menuCheckVo.getId())) {
                    menuCheckVo.setChecked(true);
                    break;
                }
            }
        });
        return menuCheckVos;
    }

    /**
     * 根据rid删除绑定菜单
     * @param rId
     */
    @Override
    public void deleteBindMenvosByRid(String rId) {
        menuDao.deleteBindMenvosByRid(rId);
    }

    /**
     * 批量绑定菜单
     * @param menuVoList
     * @param rId
     */
    @Override
    public void insertBindMenvos(String[] menuVoList, String rId) {
        for (String m:
             menuVoList) {
            menuDao.insertBindMenvo(m,rId);
        }
    }

    public List<MenuVo> changeModelToVo(List<Menu> menus) {
        MapperFactory mapperFactory = MapperFactoryUtil.initMapper();
        mapperFactory.classMap(Menu.class, MenuVo.class)
                .field("mId", "id")
                .field("pMenu", "pId")
                .field("mName", "name")
                .field("mIcon", "icon")
                .byDefault().register();
        List<MenuVo> menuVos = mapperFactory.getMapperFacade().mapAsList(menus, MenuVo.class);
        //转换成treenodes格式
        menuVos.stream().forEach(menuVo -> {
            //转换icon成相对路径
            MenuIconEnum menuIconEnum = MenuIconEnum.getEnumByType(menuVo.getIcon());
            switch (menuIconEnum) {
                case HOME_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case ANTH_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case BASICS_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case LOG_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case MENU_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case ROLE_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case USER_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
            }
            if ("0".equals(menuVo.getpId()) || "0".equals(menuVo.getId())) {
                menuVo.setOpen(true);
            }
        });
        return menuVos;
    }

    public List<MenuCheckVo> changeModelToCheckVo(List<Menu> menus) {
        MapperFactory mapperFactory = MapperFactoryUtil.initMapper();
        mapperFactory.classMap(Menu.class, MenuCheckVo.class)
                .field("mId", "id")
                .field("pMenu", "pId")
                .field("mName", "name")
                .field("mIcon", "icon")
                .field("mIcon", "icon")
                .byDefault().register();
        List<MenuCheckVo> menuCheckVos = mapperFactory.getMapperFacade().mapAsList(menus, MenuCheckVo.class);
        //转换成treenodes格式
        menuCheckVos.stream().forEach(menuVo -> {
            //转换icon成相对路径
            MenuIconEnum menuIconEnum = MenuIconEnum.getEnumByType(menuVo.getIcon());
            switch (menuIconEnum) {
                case HOME_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case ANTH_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case BASICS_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case LOG_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case MENU_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case ROLE_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
                case USER_ICON:
                    menuVo.setIcon(menuIconEnum.getPath());
                    break;
            }
            if ("0".equals(menuVo.getpId()) || "0".equals(menuVo.getId())) {
                menuVo.setOpen(true);
            }
        });
        return menuCheckVos;
    }
}
