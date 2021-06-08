package com.zcl.demo.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.zcl.demo.dao.product.ProductDao;
import com.zcl.demo.model.product.Product;
import com.zcl.demo.model.user.User;
import com.zcl.demo.po.ProducePo;
import com.zcl.demo.po.SalePo;
import com.zcl.demo.po.Series;
import com.zcl.demo.service.product.ProductService;
import com.zcl.demo.vo.product.EcharsVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcl
 * @create 2021/6/5 17:03
 * @desc 商品实现
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private final static String[] MOTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 分页展示商品
     *
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
     * 查询销售量
     *
     * @param pId
     * @return
     */
    @Override
    public SalePo querySalePrcByPid(String pId) {
        SalePo salePo = productDao.querySalePrcByPid(pId);
        return salePo;
    }

    /**
     * 查询产量
     *
     * @param pId
     * @return
     */
    @Override
    public ProducePo queryPrdNumByPid(String pId) {
        ProducePo producePo = productDao.queryPrdNumByPid(pId);

        return producePo;
    }

    @Override
    public EcharsVo initPrdSalePrcChars(String pId) {
        EcharsVo echarsVo = new EcharsVo();
        //查询销售量
        SalePo salePo = productDao.querySalePrcByPid(pId);
        //查询产量
        ProducePo producePo = productDao.queryPrdNumByPid(pId);
        String[] saleNum = salePoToArray(salePo);
        String[] prdNum = prdPoToArray(producePo);
        List<Series> seriesList = new ArrayList<>();
        seriesList.add(new Series("销售量(件)", saleNum));
        seriesList.add(new Series("生产量(件)", prdNum));
        //x轴数据维护
        echarsVo.setXAxisData(MOTHS);
        //放销量和产量
        echarsVo.setSeriesList(seriesList);
        return echarsVo;
    }


    /**
     * 销售po转换成字符串数组
     *
     * @param
     * @return
     */
    public String[] salePoToArray(SalePo salePo) {
        Field[] fields = salePo.getClass().getDeclaredFields();
        String[] reslut = new String[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                //设置允许通过反射访问私有变量
                fields[i].setAccessible(true);
                //获取字段的值
                String value = fields[i].get(salePo).toString();
                reslut[i] = value;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return reslut;
    }


    /**
     * 生产po转换成字符串数组
     *
     * @param
     * @return
     */
    public String[] prdPoToArray(ProducePo producePo) {
        Field[] fields = producePo.getClass().getDeclaredFields();
        String[] reslut = new String[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                //设置允许通过反射访问私有变量
                fields[i].setAccessible(true);
                //获取字段的值
                String value = fields[i].get(producePo).toString();
                reslut[i] = value;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return reslut;
    }
}
