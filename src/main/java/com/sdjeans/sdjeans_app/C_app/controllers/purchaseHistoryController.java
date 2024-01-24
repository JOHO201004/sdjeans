package com.sdjeans.sdjeans_app.C_app.Controllers;

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
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryQuantityUpdate;
import com.sdjeans.sdjeans_app.C_app.Beans.purchasehistoryOfView;
import com.sdjeans.sdjeans_app.C_app.Services.purchaseHistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class purchaseHistoryController {

    @Autowired
    purchaseHistoryService purchaseHistoryService;
    memberId sessionMemberId;

    @GetMapping("/purchaseH")
    public String getPurchaseHistory(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");
        boolean sortOption = true;
        if(session.getAttribute("order") == null){
            System.out.println("初回");
            session.setAttribute("order", true);
        }else{
            sortOption = (boolean) session.getAttribute("order");
        }

        model.addAttribute("order", sortOption);
        model.addAttribute("session_memberId", memberId);
        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId,
                purchaseHistoryService.sortPurchaseHistoriesByDeadline(sortOption, memberId)));
        return "c_temp/purchaseHistory";
    }

    @PostMapping("/purchaseH/delete")
    public String deletePurchaseHistory(
            // @ModelAttribute purchaseHistoryMainKey purchaseHistoryMainKey,
            @RequestParam("memberId") String memberId,
            @RequestParam("merchId") Integer merchId,
            @RequestParam("deadline") LocalDateTime deadline,
            Model model) {
        System.out.println("delte" + memberId + merchId + deadline); // ログの出力
        // purchaseHistoryService.deletePurchaseHistory(purchaseHistoryMainKey);
        purchaseHistoryService.deletePurchaseHistory(new purchaseHistoryMainKey(memberId, merchId, deadline));
        
        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId));
        return "redirect:/purchaseH";
    }

    @PostMapping("/updateQuantity")
    public String updatePurchaseHistory(
            @RequestParam("updateMemberId") String memberId,
            @RequestParam("updateMerchId") Integer merchId,
            @RequestParam("updateDeadline") LocalDateTime deadline,
            @RequestParam("newQuantity") Integer quantity,
            Model model) {
        System.out.println("ここはあっぷでーとです" + "会員" + memberId + "商品" + merchId + "期限" + deadline + "数量" + quantity);
        purchaseHistoryService
                .updatePurchaseHistory(new purchaseHistoryQuantityUpdate(memberId, merchId, deadline, quantity));

        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId));
        return "redirect:/purchaseH";
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

    public ArrayList<purchasehistoryOfView> makePurchaseHistoryOfView(String memberId, List<purchaseHistory> sortList) {
        List<purchaseHistory> purchaseHistories = sortList;
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

    @PostMapping("/purchaseH/sort")
    public String sortPurchaseHistories(Model model, HttpSession session, @RequestParam("sortOptionList") Boolean sortOption) {
        String memberId = (String) session.getAttribute("memberId");
        System.out.println("ソート" + sortOption);

        session.setAttribute("order", sortOption); // ソート方法保存
        model.addAttribute("purchaseHistories", makePurchaseHistoryOfView(memberId,
                purchaseHistoryService.sortPurchaseHistoriesByDeadline(sortOption, memberId)));
        return "redirect:/purchaseH";
    }
}
