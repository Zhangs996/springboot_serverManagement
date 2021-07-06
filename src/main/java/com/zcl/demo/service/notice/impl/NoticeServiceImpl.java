package com.zcl.demo.service.notice.impl;

import com.zcl.demo.common.exception.ZfException;
import com.zcl.demo.dao.email.EmailDao;
import com.zcl.demo.dao.notice.NoticeDao;
import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.notice.Notice;
import com.zcl.demo.service.notice.NoticeService;
import com.zcl.demo.util.DateInFo;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.emil.EmailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;
    private EmailDao emailDao;

    public NoticeServiceImpl(NoticeDao noticeDao, EmailDao emailDao) {
        this.noticeDao = noticeDao;
        this.emailDao = emailDao;
    }

    @Override
    public void save(Notice notice) {

    }

    @Override
    public void saveNoticeAndEmail(EmailVo emailVo) {
        try{

        if(StringUtils.isEmpty(emailVo.getETopic())){
            throw new ZfException("标题不能为空");
        }
        if(StringUtils.isEmpty(emailVo.getReciveMan())){
            throw new ZfException("收件人不能为空");
        }
        String eTopic = emailVo.getETopic();
        String reciveMan = emailVo.getReciveMan();
        String eContent = emailVo.getEContent();
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        //生成信件uuid
        String eId= UUID.randomUUID().toString().replace("-","");
        String nowTime = DateInFo.dateFomart();
        //存放消息表
        Notice notice = new Notice();
        notice.setSendMan(userId);
        notice.setReciveMan(reciveMan);
        notice.setIsReaded("0");
        notice.setEId(eId);
        notice.setCreateTime(nowTime);
        noticeDao.save(notice);
        //存放信件内容表
        Email email = new Email();
        email.setEId(eId);
        email.setCreateTime(nowTime);
        email.setEContent(eContent);
        email.setETopic(eTopic);
        email.setCreateUser(userId);
        emailDao.save(email);
        }catch (Exception e){
            log.error("监听异常，{}",e);
        }
    }

    @Override
    public Integer queryNoticeNumByUid(String getuId) {
        return noticeDao.queryNoticeNumByUid(getuId);
    }


}