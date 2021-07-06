package com.zcl.demo.service.email.impl;

import com.github.pagehelper.PageHelper;
import com.zcl.demo.dao.email.EmailDao;
import com.zcl.demo.enums.notice.NoticeReadEnum;
import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.email.EmailService;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.email.EmailSerchVo;
import com.zcl.demo.vo.email.ShowEmailVo;
import com.zcl.demo.vo.emil.EmailVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private EmailDao emailDao;

    public EmailServiceImpl(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Override
    public void save(Email email) {

    }

    @Override
    public List<Email> queryAllEmailByUid() {
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        List<Email> emails = emailDao.queryAllEmailByUid(userId);
        return emails;
    }

    @Override
    public List<ShowEmailVo> list(int page, int limit, String isReaded, String sendMan, String reviceTime) {
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        List<ShowEmailVo> emails = null;
        PageHelper.startPage(page, limit);
        //动态查询
        emails = emailDao.queryDysnEmailByVo(userId, isReaded, sendMan, reviceTime);
        //转换是否已读为中文
        emails.stream().forEach(e -> {
            String readed = e.getIsReaded();
            if ("0".equals(readed)) {//未读
                e.setIsReaded(NoticeReadEnum.NO_READ.getDesc());
            } else {//已读
                e.setIsReaded(NoticeReadEnum.READ.getDesc());
            }
        });
        return emails;
    }
}
