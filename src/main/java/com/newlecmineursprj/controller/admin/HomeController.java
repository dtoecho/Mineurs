package com.newlecmineursprj.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminHomeController")
@RequestMapping("admin")
public class HomeController{
    @GetMapping("index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("signin")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signin() {
        return "register";
    }
}
