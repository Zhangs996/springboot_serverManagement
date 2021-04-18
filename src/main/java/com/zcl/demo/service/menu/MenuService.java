package com.zcl.demo.service.menu;

import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.vo.menu.MenuVo;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/4/17 21:12

 * @desc 菜单管理业务层

 **/
public interface MenuService {

    List<MenuVo> queryTreeAll();
}
