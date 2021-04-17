package com.zcl.demo.service.menu.impl;

import com.zcl.demo.dao.menu.MenuDao;
import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.service.menu.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;
    MenuServiceImpl(MenuDao menuDao){
        this.menuDao=menuDao;
    }
    @Override
    public List<Menu> queryTreeAll() {
        menuDao.queryAll();
        return null;
    }
}
