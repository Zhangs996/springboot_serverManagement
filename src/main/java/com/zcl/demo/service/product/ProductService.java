package com.zcl.demo.service.product;

import com.zcl.demo.model.product.Product;
import com.zcl.demo.po.ProducePo;
import com.zcl.demo.po.SalePo;
import com.zcl.demo.po.Series;
import com.zcl.demo.vo.product.EcharsVo;
import org.assertj.core.error.ShouldHaveAtLeastOneElementOfType;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/6/5 17:02

 * @desc 商品业务层

 **/
public interface ProductService {
    //动态展示商品
    List<Product> list(int page, int limit, String pName);
    //查询销售量
    SalePo querySalePrcByPid(String pId);
    //查询产量
    ProducePo queryPrdNumByPid(String pId);
    //初始化销售产量柱状图
    EcharsVo initPrdSalePrcChars(String pId);
}
