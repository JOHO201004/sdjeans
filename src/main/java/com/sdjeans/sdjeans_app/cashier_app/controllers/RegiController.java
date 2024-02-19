// package com.sdjeans.sdjeans_app.cashier_app.Controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.sdjeans.sdjeans_app.cashier_app.Entity.RegiForm;
// import com.sdjeans.sdjeans_app.cashier_app.Entity.ShopStock;
// import com.sdjeans.sdjeans_app.cashier_app.service.RegiService;
// import jakarta.servlet.http.HttpSession;

// @Controller
// public class RegiController {

//     @Autowired
//     RegiService regiService;

//     @PostMapping("/regiLogic")
//     public String regiLogic(@RequestParam("memberId") String memberId, @ModelAttribute RegiForm regiForm, HttpSession session, Model model) {
//         for (ShopStock selectedStock : regiForm.getSelectedStocks()) {
//             System.out.println(selectedStock.getSelectedQuantity()+"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//             if (!selectedStock.getSelectedQuantity().equals("0")) {
//                 System.out.println("選択された商品 - ショップID: " + selectedStock.getShopId() +
//                         "、商品ID: " + selectedStock.getMerchId() +
//                         "、消費期限: " + selectedStock.getDeadline() +
//                         "、数量: " + selectedStock.getQuantity() +
//                         "、割引率: " + selectedStock.getDiscountRate() +
//                         "、選択個数: " + selectedStock.getSelectedQuantity());
//                 // 各商品ごとに更新と挿入を行う
//                 regiService.updateShopStocks(selectedStock, memberId);
//                 regiService.insertShopStocks(selectedStock, memberId);
//                 regiService.deleatShopStocks(selectedStock, memberId);
//             }
//         }
//         // ビジネスロジックを追加
//         // 成功ページにリダイレクトまたはビューを返す
//         return "redirect:/successPage";
//     }
// }

