package com.zcl.demo.dao.product;

import com.zcl.demo.model.product.Product;
import com.zcl.demo.po.ProducePo;
import com.zcl.demo.po.SalePo;
import org.assertj.core.error.ShouldHaveAtLeastOneElementOfType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcl
 * @create 2021/6/5 17:06
 * @desc 商品dao层
 **/
@Component
public interface ProductDao {
    /**
     * 分页展示商品
     * @return
     */
    List<Product> queryAll();

    /**
     * 根据name模糊查询商品
     * @param pName
     * @return
     */
    List<Product> queryProductByName(String pName);

    /**
     * 查询销售量
     * @param pId
     * @return
     */
    SalePo querySalePrcByPid(String pId);

    /**
     * 查询产量
     * @param pId
     */
    ProducePo queryPrdNumByPid(String pId);
}
