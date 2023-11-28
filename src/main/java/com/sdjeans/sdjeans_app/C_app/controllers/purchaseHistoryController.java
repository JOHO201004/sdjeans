package com.sdjeans.sdjeans_app.C_app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sdjeans.sdjeans_app.C_app.Beans.memberId;
import com.sdjeans.sdjeans_app.C_app.Beans.merchandise;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
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
        List<purchaseHistory> purchaseHistories = purchaseHistoryService.getPurchaseHistoriesById(memberId);
        ArrayList<purchasehistoryOfView> purchasehistoryOfViewsList = new ArrayList<purchasehistoryOfView>();
        for (int i = 0; i < purchaseHistories.size(); i++) {
            merchandise merchandises = purchaseHistoryService
                    .getMerchandisesById(purchaseHistories.get(i).getMerchId());

            purchasehistoryOfView p = new purchasehistoryOfView(
                    merchandises.getMerchName(),
                    purchaseHistories.get(i).getDeadline(),
                    purchaseHistories.get(i).getQuantity()),
                    isPast(purchaseHistories.get(i).getDeadline());
            purchasehistoryOfViewsList.add(p);
        }
        model.addAttribute("purchaseHistories", purchasehistoryOfViewsList);
        return "c_temp/purchaseHistory";
    }

        public static boolean isPast(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isBefore(currentDateTime);
    }
}
