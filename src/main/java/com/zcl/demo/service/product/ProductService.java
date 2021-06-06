package com.zcl.demo.service.product;

import com.zcl.demo.model.product.Product;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/6/5 17:02

 * @desc 商品业务层

 **/
public interface ProductService {
    //动态展示商品
    List<Product> list(int page, int limit, String pName);

}
