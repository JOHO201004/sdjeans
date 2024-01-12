package com.sdjeans.sdjeans_app.merchChecker_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
// @Slf4j
public class merchCheckerController {
    
    
    @GetMapping("/merchandiseRegister")
    public String regiga(Model model) {
        // model.addAttribute("name", name);
        return "wareH_temp/merchandiseRegister";
    }

    @PostMapping("/merchandiseRegister")
    public String regi() {
        return "wareH_temp/merchandiseRegisterCfm";
    }
}
