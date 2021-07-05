package com.zcl.demo.controller.notice;

import com.github.pagehelper.PageInfo;
import com.zcl.demo.common.exception.ErrorCodeEnum;
import com.zcl.demo.common.exception.ZfException;
import com.zcl.demo.common.response.CommonResponse;
import com.zcl.demo.common.status.StatusCode;
import com.zcl.demo.model.email.Email;
import com.zcl.demo.model.notice.Notice;
import com.zcl.demo.model.user.User;
import com.zcl.demo.service.email.EmailService;
import com.zcl.demo.service.notice.NoticeService;
import com.zcl.demo.service.user.UserService;
import com.zcl.demo.util.DateInFo;
import com.zcl.demo.util.SessionUtil;
import com.zcl.demo.vo.email.EmailSerchVo;
import com.zcl.demo.vo.emil.EmailVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private static final String URL = "/notice";


    public NoticeController(UserService userService, EmailService emailService, NoticeService noticeService) {
        this.userService = userService;
        this.emailService = emailService;
        this.noticeService = noticeService;
    }

    /**
     * 跳转消息首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/showIndex.html", method = RequestMethod.GET)
    public String showIndex(Model model) {
        List<User> users = userService.queryAllUser();
        model.addAttribute("users", users);
        return URL + "/list";
    }

    /**
     * 保存信件
     *
     * @param emailVo
     * @return
     */
    @RequestMapping(value = "/saveEmail.json", method = RequestMethod.POST)
    @ResponseBody
    public Map saveEmail(EmailVo emailVo) {
        noticeService.saveNoticeAndEmail(emailVo);
        return CommonResponse.setResponseMsg("发送成功");
    }

    /**
     * 根据uid查询信件
     * @param
     * @return
     */
    @RequestMapping(value = "/emailList.json",method = RequestMethod.POST)
    @ResponseBody
    public Map queryAllEmailByUid(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false) int limit,EmailSerchVo emailSerchVo){
        List<Email> list = emailService.list(page, limit, emailSerchVo);
        // 使用pageInfo包装查询
        PageInfo<Email> emails = new PageInfo<>(list);
        Map map = CommonResponse.setPageResponse(emails.getList(), emails.getTotal(), StatusCode.SUCCESS.getName(), "成功", true);
        return map;
    }

}
