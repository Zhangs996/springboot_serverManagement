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
    /**
     * 展现树形结构
     * @return
     */
    List<MenuVo> queryTreeAll();

    /**
     * 根据id返回中文描述
     * @param treeId
     * @return
     */
    String queryNodeNameById(String treeId);

    /**
     * 添加url
     * @param menu
     * @return
     */
    void addUrl(Menu menu);

    /**
     * 根据mid删除
     * @param mId
     */
    void removeUrl(String mId);

    /**
     * 根据mid查询menu
     * @param mId
     */
    Menu selectMenuByMid(String mId);

    /**
     * 根据mid修改菜单
     * @param menu
     * @return
     */
    void updateUrl(Menu menu);
}
