package com.zcl.demo.dao.email;

import com.zcl.demo.model.email.Email;
import com.zcl.demo.vo.email.EmailSerchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/7/3 21:39

 * @desc 信件

 **/
@Component
public interface EmailDao {
    void save(Email email);

    /**
     * 根据uID查找邮件
     * @param userId
     * @return
     */
    List<Email> queryAllEmailByUid(String userId);

    /**
     * 动态查询邮件
     * @param userId
     * @param emailSerchVo
     * @return
     */
    List<Email> queryDysnEmailByVo(@Param("userId") String userId,@Param("emailSerchVo")  EmailSerchVo emailSerchVo);
}
