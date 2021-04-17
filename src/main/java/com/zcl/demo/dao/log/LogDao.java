package com.zcl.demo.dao.log;

import com.zcl.demo.model.log.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LogDao {
   //查找全部
   List<Log> queryAll();
   //添加日志
   void addLog(Log log);
   //根据名称模糊查询
   List<Log> queryByName(String userName);
   //根据日期模糊查询
   List<Log> queryByDate(String createTime);
   //根据名称、日期模糊查询
   List<Log> queryByNameAndDate(@Param("userName")String userName,@Param("createTime") String createTime);
}
