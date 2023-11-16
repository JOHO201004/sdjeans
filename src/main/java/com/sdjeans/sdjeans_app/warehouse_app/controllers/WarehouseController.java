package com.sdjeans.sdjeans_app.warehouse_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @Slf4j
public class WarehouseController {
    
    
    @RequestMapping("/login")
    public String login() {
        // model.addAttribute("name", name);
        return "login";
    }
    
}
