package com.sdjeans.sdjeans_app.cashier_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.cashier_app.Entity.Employee;
import com.sdjeans.sdjeans_app.cashier_app.Entity.RegiForm;
import com.sdjeans.sdjeans_app.cashier_app.Entity.ShopStock;
import com.sdjeans.sdjeans_app.cashier_app.service.RegiService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegiController {

// RegiController.java
@Autowired
RegiService regiService;

// RegiController.java
@PostMapping("/regiLogic")
public String regiLogic(@RequestParam("memberId") String memberId, @ModelAttribute RegiForm regiForm, HttpSession session, Model model) {
    Employee emp = (Employee) session.getAttribute("emp");
    List<ShopStock> shopItems = regiService.showShopStock(emp.getShopId());

    // 選択された商品をログに出力（オプション）
    for (ShopStock selectedStock : regiForm.getSelectedStocks()) {
        System.out.println("選択された商品 - ショップID: " + selectedStock.getShopId() +
                "、商品ID: " + selectedStock.getMerchId() +
                "、消費期限: " + selectedStock.getDeadline() +
                "、数量: " + selectedStock.getQuantity() +
                "、割引率: " + selectedStock.getDiscountRate());
    }

    // ビジネスロジックを追加

    // 成功ページにリダイレクトまたはビューを返す
    return "redirect:/successPage";
}
}

