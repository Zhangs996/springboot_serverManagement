package com.zcl.demo.dao.menu;

import com.zcl.demo.model.menu.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/4/17 21:24

 * @desc 菜单管理持久层

 **/

@Component
public interface MenuDao {
    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> queryAll();
}
