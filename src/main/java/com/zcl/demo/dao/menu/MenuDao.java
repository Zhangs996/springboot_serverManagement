package com.zcl.demo.dao.menu;

import com.zcl.demo.model.menu.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcl
 * @create 2021/4/17 21:24
 * @desc 菜单管理持久层
 **/

@Component
public interface MenuDao {
    /**
     * 查询所有菜单
     *
     * @return
     */
    List<Menu> queryAll();

    /**
     * 根据id查名称
     *
     * @param treeId
     */
    String queryNodeNameById(String treeId);

    /**
     * 添加菜单
     *
     * @param menu
     */
    void addUrl(Menu menu);

    /**
     * 删除菜单
     * @param mId
     */
    void removeUrl(String mId);
}
