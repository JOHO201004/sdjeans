package com.sdjeans.sdjeans_app.warehouse_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WarehouseController {
    
    
    @RequestMapping("/login")
    public String login() {
        // model.addAttribute("name", name);
        return "login";
    }
    

}
