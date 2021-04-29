package com.zcl.demo.controller.menu;

import com.alibaba.fastjson.JSON;
import com.zcl.demo.common.response.CommonResponse;
import com.zcl.demo.common.status.StatusCode;
import com.zcl.demo.model.menu.Menu;
import com.zcl.demo.service.menu.MenuService;
import com.zcl.demo.vo.menu.MenuVo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zcl
 * @create 2020/12/27 19:31
 * @desc 菜单管理
 **/
@Controller
@RequestMapping(value = "/menuController")
public class MenuController {
    /**
     * 构造器方式注入，官方推荐
     */
    private MenuService menuService;

    MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    private String prief = "/menu";

    /**
     * 菜单管理主界面
     */
    @RequestMapping(value = "/showindex.html", method = RequestMethod.GET)
    public String showIndex() {
        return prief + "/index";
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @RequestMapping(value = "/queryTreeAll.json", method = RequestMethod.GET)
    @ResponseBody
    public Map queryTreeAll() {
        List<MenuVo> menuVos = menuService.queryTreeAll();
        return CommonResponse.setResponseData(menuVos);
    }

    /**
     * 菜单树添加页面
     *
     * @return
     */
    @RequestMapping(value = "/addpage.html",method = RequestMethod.GET)
    public String showAddPage() {
        return prief + "/add";
    }

}
