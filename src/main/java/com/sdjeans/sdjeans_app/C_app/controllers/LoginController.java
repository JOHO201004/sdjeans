package com.sdjeans.sdjeans_app.C_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdjeans.sdjeans_app.C_app.Beans.loginForm;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    String getLogin(Model model) {
        model.addAttribute("loginForm", new loginForm()); // loginFormをモデルに追加する
        return "c_temp/login";
    }

    @RequestMapping("/regi")
    public String registerMember() {
        return "c_temp/reMember";
    }
}
