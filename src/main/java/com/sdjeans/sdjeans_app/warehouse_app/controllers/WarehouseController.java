package com.sdjeans.sdjeans_app.warehouse_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
// @Slf4j
public class WarehouseController {
    
    
    @RequestMapping("/merchandiseRegister")
    public String regiga() {
        // model.addAttribute("name", name);
        return "wareH_temp/merchandiseRegister";
    }

    @PostMapping("/merchandiseRegister")
    public String regi() {
        return "wareH_temp/merchandiseRegisterCfm";
    }
    
    
}
