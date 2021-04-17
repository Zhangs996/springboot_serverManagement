package com.zcl.demo.model.menu;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/4/17 20:13

 * @desc 功能菜单树

 **/
public class Menu {
    /**自定义主键生成策略*/
    private String id;

    /**
     * 菜单功能名
     */
    private String menuName;

    /**
     * 是否用于导航（0：是 1：否）
     */
    private String isNavigtion;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单描述
     */
    private String menuDesc;

    /**
     * 菜单顺序
     */
    private Integer menuOrder;

    /**
     * 菜单URL
     */
    private String menuUrl;

    /**
     * 上级功能编号
     */
    private Menu pubMenu;

    /**
     * 子级功能
     */
    private List<Menu> pubMenus;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private String updateTime;

}
