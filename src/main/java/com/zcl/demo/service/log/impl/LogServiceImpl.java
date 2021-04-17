package com.zcl.demo.service.log.impl;

import com.github.pagehelper.PageHelper;
import com.zcl.demo.common.status.LogStatus;
import com.zcl.demo.dao.log.LogDao;
import com.zcl.demo.dao.user.UserDao;
import com.zcl.demo.model.log.Log;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Log> queryAll() {
        return logDao.queryAll();
    }

    @Override
    public void addLog(Log log) {
        logDao.addLog(log);
    }

    @Override
    public User UserLogCheck(User user) {
        User user1 = userDao.queryUser(user);
        return user1;
    }

    @Override
    public int logout(HttpSession httpSession) {
        String status = (String) httpSession.getAttribute("status");
        if ("登录".equals(status)) {//退出成功
            httpSession.invalidate();
            return 0;//
        } else {//退出失败
            return 1;
        }
    }

    @Override
    public List<Log> query(int page,int limit,String name, String date) {
        List<Log> list = new ArrayList<>();
        PageHelper.startPage(page, limit);
        if (!StringUtils.isEmpty(name) &&!StringUtils.isEmpty(date) ) {
            list = logDao.queryByNameAndDate(name, date);
        } else if (StringUtils.isEmpty(name)&&!StringUtils.isEmpty(date)) {
            list = logDao.queryByDate(date);
        } else if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(date)) {
            list = logDao.queryByName(name);
        } else {
            list = logDao.queryAll();
        }
        return list;
    }


}
