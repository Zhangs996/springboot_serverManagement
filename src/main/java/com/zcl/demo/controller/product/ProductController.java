package com.zcl.demo.controller.product;

import com.github.pagehelper.PageInfo;
import com.zcl.demo.common.response.CommonResponse;
import com.zcl.demo.common.status.StatusCode;
import com.zcl.demo.model.product.Product;
import com.zcl.demo.po.ProducePo;
import com.zcl.demo.po.SalePo;
import com.zcl.demo.po.Series;
import com.zcl.demo.service.product.ProductService;
import com.zcl.demo.vo.product.EcharsVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zcl
 * @create 2021/6/5 16:48
 * @desc 商品controller
 **/
@Controller
@RequestMapping(value = "/productController")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    String url = "/product";

    /**
     * 跳转商品页
     *
     * @return
     */
    @RequestMapping(value = "/showlist.html", method = RequestMethod.GET)
    public String showListPage() {
        return url + "/list";
    }

    /**
     * 动态查询
     *
     * @param page
     * @param limit
     * @param pName
     * @return
     */
    @RequestMapping(value = "/showlist.json", method = RequestMethod.POST)
    @ResponseBody
    public Map showList(@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false) int limit, String pName) {
        List<Product> list = productService.list(page, limit, pName);
        // 使用pageInfo包装查询
        PageInfo<Product> rolePageInfo = new PageInfo<>(list);
        Map map = CommonResponse.setPageResponse(rolePageInfo.getList(), rolePageInfo.getTotal(), StatusCode.SUCCESS.getName(), "查询成功", true);
        return map;
    }

    /**
     * 根据商品id展示商品销售产量关系图
     *
     * @param pId
     * @return
     */
    @RequestMapping(value = "/initPrdSalePrcChars.json", method = RequestMethod.GET)
    @ResponseBody
    public Map initPrdSalePrcChars(String pId) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(pId)) {
            map = CommonResponse.setResponseData(null, StatusCode.FAIL.getCode(), "查询内容不能为空！", false);
            return map;
        }
        EcharsVo echarsVo=productService.initPrdSalePrcChars(pId);
        map = CommonResponse.setResponseData(echarsVo, StatusCode.SUCCESS.getCode(), "加载成功", true);
        return map;
    }



}
