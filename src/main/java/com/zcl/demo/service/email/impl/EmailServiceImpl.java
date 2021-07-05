package com.zcl.demo.service.email.impl;

import com.github.pagehelper.PageHelper;
import com.zcl.demo.dao.email.EmailDao;
import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.email.EmailService;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.email.EmailSerchVo;
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
    public List<Email> list(int page, int limit, EmailSerchVo emailSerchVo) {
        String isReaded = emailSerchVo.getIsReaded();
        String sendMan = emailSerchVo.getSendMan();
        String reviceTime = emailSerchVo.getReviceTime();
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        List<Email> emails = null;
        PageHelper.startPage(page, limit);
        if (StringUtils.isEmpty(isReaded) && StringUtils.isEmpty(sendMan) && StringUtils.isEmpty(reviceTime)) {
            emails = emailDao.queryAllEmailByUid(userId);
        }else{//动态查询
            emails=emailDao.queryDysnEmailByVo(userId,emailSerchVo);
        }
        return emails;
    }
}
