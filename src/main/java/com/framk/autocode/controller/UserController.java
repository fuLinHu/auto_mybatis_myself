package com.framk.autocode.controller;

import com.framk.autocode.Util.EncryptUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        //model.addAttribute("username", principal.getName());
        return "/user/user.html";
    }
    @GetMapping("/test")
    public void test(){
        EncryptUtil.save();
    }


}
