package com.zcl.demo.dao.notice;

import com.zcl.demo.model.notice.Notice;
import org.springframework.stereotype.Component;

/**

 * @author  zcl

 * @create  2021/7/3 21:37

 * @desc 消息通知

 **/
@Component
public interface NoticeDao {

    void save(Notice notice);

    Integer queryNoticeNumByUid(String getuId);
    //更改消息为已读
    void updateNoticeReaded(String eId);
    //全部已读
    void allEmailReaded(String userId);
}
