package com.sdjeans.sdjeans_app.C_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sdjeans.sdjeans_app.C_app.Beans.memberId;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
import com.sdjeans.sdjeans_app.C_app.services.purchaseHistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class purchaseHistoryController {

    @Autowired
    purchaseHistoryService purchaseHistoryService;
    memberId sessionMemberId;
    
    @GetMapping("/purchaseH")
    public String getPurchaseHistory(HttpSession session,Model model){
        String memberId = (String)session.getAttribute("memberId");
        List<purchaseHistory> purchaseHistories = purchaseHistoryService.getPurchaseHistoriesById(memberId);
        model.addAttribute("purchaseHistories", purchaseHistories);
        return "c_temp/purchaseHistory";
    }
}
