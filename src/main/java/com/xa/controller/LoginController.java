package com.xa.controller;

import com.xa.pojo.User;
import com.xa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){


        boolean sucFlag = userService.checkLogin(user);
        if(sucFlag){
            session.setAttribute("user",user);
            return "index";
        }else{
            model.addAttribute("error","密码或用户名错误啦!还想随便进呀");
            return "login";
        }

    }
}
