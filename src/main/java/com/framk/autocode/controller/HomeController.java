package com.framk.autocode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "/index.html";
    }

    @GetMapping("/login")
    public String login(){
        return "/login.html";
    }

}


