package com.zcl.demo.common.aspect;

import com.zcl.demo.common.status.LogStatus;
import com.zcl.demo.model.log.Log;
import com.zcl.demo.model.user.User;
import com.zcl.demo.util.DateInFo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**

 * @author  曾小白

 * @create  2021/7/9 16:49

 * @desc 功能日志切面

 **/
@Aspect
@Component
public class FunctionAspect {
    private HttpSession httpSession;

    //切点为loginChek()方法
    @Pointcut("@annotation(com.zcl.demo.common.annotation.PointLog)")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知：---------------------");
        //获得方法执行后的返回值
        Object proceed = pjp.proceed();//让目标方法执行

        //保存到功能日志表中
        return proceed;
    }


}


