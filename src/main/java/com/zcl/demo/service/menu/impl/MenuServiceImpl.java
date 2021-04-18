package com.zcl.demo.service.menu.impl;

import com.zcl.demo.dao.menu.MenuDao;
import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.service.menu.MenuService;
import com.zcl.demo.util.MapperFactoryUtil;
import com.zcl.demo.vo.menu.MenuVo;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;

    MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

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
            if ("0".equals(menuVo.getpId())) {
                menuVo.setOpen(true);
            }
        });
        return menuVos;
    }
}
