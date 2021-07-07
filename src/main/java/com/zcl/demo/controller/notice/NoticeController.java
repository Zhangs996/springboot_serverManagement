package com.zcl.demo.controller.notice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zcl.demo.common.exception.ZfException;
import com.zcl.demo.common.response.CommonResponse;
import com.zcl.demo.common.status.StatusCode;
import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.email.EmailService;
import com.zcl.demo.service.notice.NoticeService;
import com.zcl.demo.service.user.UserService;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.email.ShowEmailVo;
import com.zcl.demo.vo.emil.EmailVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zcl
 * @create 2021/7/3 20:52
 * @desc 内部信控制类
 **/
@Controller
@RequestMapping(value = "/NoticeController")
public class NoticeController {

    private UserService userService;
    private EmailService emailService;
    private NoticeService noticeService;
    private RabbitTemplate rabbitTemplate;
    private static final String URL = "/notice";


    public NoticeController(UserService userService, EmailService emailService, NoticeService noticeService, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.emailService = emailService;
        this.noticeService = noticeService;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 跳转消息首页
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/showIndex.html", method = RequestMethod.GET)
    public String showIndex() {
        return URL + "/list";
    }

    /**
     * 跳转至发送邮件页面
     *
     * @return
     */
    @RequestMapping(value = "/showSendEmail.html", method = RequestMethod.GET)
    public String showSendEmail(Model model) {
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        List<User> users = userService.queryAllUser();
        model.addAttribute("users", users);
        model.addAttribute("userId", userId);
        return URL + "/sendemail";
    }

    /**
     * 发送信件（生产者）
     *
     * @param emailVo
     */
    @RequestMapping(value = "/saveEmailProducer.json", method = RequestMethod.POST)
    @ResponseBody
    public Map email(EmailVo emailVo) {
        //发送至rabbitmq的交换机“emailExchange”,队列“email.k1"
        byte[] bytes = JSON.toJSONString(emailVo).getBytes();
        rabbitTemplate.convertAndSend("emailExchange", "email.k1", bytes);
        return CommonResponse.setResponseMsg("发送成功");
    }

    /**
     * 保存信件（消费者）
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/EmailConsumer.json", method = RequestMethod.POST)
    @RabbitListener(queues = "email.k1")
    @ResponseBody
    public void saveEmail(String msg) {
        EmailVo emailVo = JSONArray.parseObject(msg, EmailVo.class);
        noticeService.saveNoticeAndEmail(emailVo);
    }


    /**
     * 根据uid查询信件
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/emailList.json", method = RequestMethod.POST)
    @ResponseBody
    public Map queryAllEmailByUid(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false) int limit, String isReaded, String sendMan, String reviceTime) {
        List<ShowEmailVo> list = emailService.list(page, limit, isReaded, sendMan, reviceTime);
        // 使用pageInfo包装查询
        PageInfo<ShowEmailVo> emails = new PageInfo<>(list);
        Map map = CommonResponse.setPageResponse(emails.getList(), emails.getTotal(), StatusCode.SUCCESS.getName(), "成功", true);
        return map;
    }

    /**
     * 查询邮件数量
     */
    @RequestMapping(value = "/getNoticeNum.json", method = RequestMethod.GET)
    @ResponseBody
    public Map getNoticeNum() {
        String userId = (String) SessionUtil.getSessionAttribute("userId");
        Integer noticeNum = noticeService.queryNoticeNumByUid(userId);
        return CommonResponse.setResponseData(noticeNum);
    }

    /**
     * 跳转消息详情界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/emailDetailPage.html",method = RequestMethod.GET)
    public String emailDetailPage(Model model,String eId) {
        if(StringUtils.isEmpty(eId)){
            throw new ZfException("信件id传输不能为空！");
        }
        //查出信件内容
        Email email=emailService.queryEmailByEid(eId);
        model.addAttribute("email",email);
        //标志位已读
        noticeService.updateNoticeReaded(eId);
        return URL + "/emaildetail";
    }
}
