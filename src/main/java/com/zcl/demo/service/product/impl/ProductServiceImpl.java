package com.zcl.demo.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.zcl.demo.dao.product.ProductDao;
import com.zcl.demo.model.product.Product;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.product.ProductService;
import com.zcl.demo.vo.product.EcharsVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zcl
 * @create 2021/6/5 17:03
 * @desc 商品实现
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 分页展示商品
     * @param page
     * @param limit
     * @param pName
     * @return
     */
    @Override
    public List<Product> list(int page, int limit, String pName) {
        List<Product> products = null;
        PageHelper.startPage(page, limit);
        if (StringUtils.isEmpty(pName)) {
            products = productDao.queryAll();
        } else {
            products = productDao.queryProductByName(pName);
        }
        return products;
    }

    /**
     * 查询销售量、生产量
     * @param pId
     * @return
     */
    @Override
    public EcharsVo queryPrdSalePrcByPid(String pId) {
        return null;
    }
}
