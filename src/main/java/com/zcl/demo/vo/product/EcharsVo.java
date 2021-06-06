package com.zcl.demo.vo.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zcl
 * @create 2021/6/6 21:52
 * @desc Echars图标vo类
 **/
@Data
public class EcharsVo implements Serializable {
    private String name;
    private String type;
    private String[] data;

}
