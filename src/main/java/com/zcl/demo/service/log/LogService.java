package com.zcl.demo.service.log;

import com.zcl.demo.model.log.Log;
import com.zcl.demo.model.user.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface LogService {
    /**
     * 返回所有日志
     * @return
     */
    List<Log> queryAll();
    /**
     * 添加日志
     */
    void addLog(Log log);
    /**
     * 登录用户的校验
     * @param user
     * @param
     * @return
     */
    public User UserLogCheck(User user);

    /**
     * 用户登出
     * @param httpSession
     */
    int logout(HttpSession httpSession);
    /**
     * 查询框查询
     */
    List<Log> query(int page,int limit,String name,String date);

    /**
     * 统计今日访问次数
     */
    String statisNowDayPnum();
}
