package com.sdjeans.sdjeans_app.C_app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.C_app.Beans.memberId;
import com.sdjeans.sdjeans_app.C_app.Beans.merchandise;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryMainKey;
import com.sdjeans.sdjeans_app.C_app.Beans.purchasehistoryOfView;
import com.sdjeans.sdjeans_app.C_app.services.purchaseHistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class purchaseHistoryController {

    @Autowired
    purchaseHistoryService purchaseHistoryService;
    memberId sessionMemberId;

    @GetMapping("/purchaseH")
    public String getPurchaseHistory(HttpSession session, Model model) {
        // String memberId = (String)session.getAttribute("memberId");
        String memberId = "1";
        
        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId));
        return "c_temp/purchaseHistory";
    }
    

    @PostMapping("/purchaseHis")
    public String deletePurchaseHistory(
        // @ModelAttribute purchaseHistoryMainKey purchaseHistoryMainKey,
            @RequestParam("memberId") String memberId,
            @RequestParam("merchId") Integer merchId,
            @RequestParam("deadline") LocalDateTime deadline,
            Model model) {
                System.out.println("Controller reached!"); // ログの出力
        // purchaseHistoryService.deletePurchaseHistory(purchaseHistoryMainKey);
        purchaseHistoryService.deletePurchaseHistory(new purchaseHistoryMainKey(memberId,merchId,deadline));
        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId));
        return "c_temp/purchaseHistory";
    }

    public static boolean isPast(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isBefore(currentDateTime);
    }

    public ArrayList<purchasehistoryOfView> makePurchaseHistoryOfView(String memberId) {
        List<purchaseHistory> purchaseHistories = purchaseHistoryService.getPurchaseHistoriesById(memberId);
        ArrayList<purchasehistoryOfView> purchasehistoryOfViewsList = new ArrayList<purchasehistoryOfView>();
        for (int i = 0; i < purchaseHistories.size(); i++) {
            merchandise merchandises = purchaseHistoryService
                    .getMerchandisesById(purchaseHistories.get(i).getMerchId());

            purchasehistoryOfView p = new purchasehistoryOfView(
                    memberId,
                    purchaseHistories.get(i).getMerchId(),
                    merchandises.getMerchName(),
                    purchaseHistories.get(i).getDeadline(),
                    purchaseHistories.get(i).getQuantity(),
                    isPast(purchaseHistories.get(i).getDeadline()));
            purchasehistoryOfViewsList.add(p);
        }
        return purchasehistoryOfViewsList;
    }
}
