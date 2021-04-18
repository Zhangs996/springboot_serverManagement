package com.zcl.demo.vo.menu;

import lombok.Data;

/**

 * @author  zcl

 * @create  2021/4/18 14:44

 * @desc 菜单vo层

 **/
@Data
public class MenuVo {
    /**
     * 菜单id
     */
    private String id;
    /**
     * 父级菜单id
     */
    private String pId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 是否默认展开
     */
    private boolean open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
