package com.dnsoft.bootcrm.core.web.controller;

import com.dnsoft.bootcrm.core.pojo.User;
import com.dnsoft.bootcrm.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(String usercode, String password, Model model, HttpSession session){
        User user=userService.findUser(usercode,password);
        if(user!=null){
            //将登录的用户添加到Session;
            session.setAttribute("USER_SESSION",user);
            //重定向到客户页面
            return "redirect:list.action";
        }
        model.addAttribute("msg","账号或密码错误，请重新输入！");
        return "login";
    }

    //用户退出
    @RequestMapping(value = "/logout.action")
    public String logout(HttpSession session){
        //清除session
        session.invalidate();
        //重定向到登录页面(get请求,对应下面的GET请求方法)
        return "redirect:login.action";
    }
    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

//    测试能否让未登录的用户直接访问客户页面
//    @RequestMapping(value = "/toCustomer.action")
//    public String testLogin() {
//        return "customer";
//    }
}
