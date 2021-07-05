package com.zcl.demo.service.notice;

import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.notice.Notice;
import com.zcl.demo.vo.emil.EmailVo;

import java.util.List;

/**

 * @author  zcl

 * @create  2021/7/3 21:53

 * @desc 消息通知层

 **/
public interface NoticeService {
    void save(Notice notice);

    /**
     * 保存至notice和email
     * @param emailVo
     */
    void saveNoticeAndEmail(EmailVo emailVo);

    Integer queryNoticeNumByUid(String getuId);

}
