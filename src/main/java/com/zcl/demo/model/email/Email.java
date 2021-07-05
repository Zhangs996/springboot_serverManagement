package com.zcl.demo.model.email;

import lombok.Data;

/**

 * @author  zcl

 * @create  2021/7/3 21:39

 * @desc 信件实体类

 **/
@Data
public class Email {
    private String eId;
    private String eTopic;
    private String eUrl;
    private String eContent;
    private String createUser;
    private String createTime;
}
