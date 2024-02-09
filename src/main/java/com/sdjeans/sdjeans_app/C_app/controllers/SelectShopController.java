package com.sdjeans.sdjeans_app.C_app.Controllers;  // 正しいパッケージ名に修正

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.C_app.Entity.ShopStock;
import com.sdjeans.sdjeans_app.C_app.Entity.ShopStockShow;
import com.sdjeans.sdjeans_app.C_app.Services.ShopSelectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SelectShopController {

    @Autowired
    private ShopSelectService shopSelectService;

 // 新しいインスタンスを作成して初期化

     // 新しいインスタンスを作成して初期化


    @PostMapping("/selectShopShowItem")
public String showShopItems(@RequestParam(name = "shopId") String shopId, Model model,HttpSession session) {
    
    // 店舗IDを使用して商品在庫リストを取得するサービスメソッドを呼び出す
    List<ShopStock> shopItems = (shopSelectService.getShopItems(Integer.parseInt(shopId)));

    

    //商品IDから商品名にして
    // List<ShopStock> changedItems = shopSelectService.merchIdChange(shopItems);

    //いい感じのリストに作り直して
     List<ShopStockShow> shopStockShows = new ArrayList<>();
    for(ShopStock s: shopItems){
        ShopStockShow kani = new ShopStockShow();
        kani.setMerchName(shopSelectService.merchIdChange(s.getMerchId()));
        System.out.println(s.getMerchId());
        kani.setDeadline(s.getDeadline());
        kani.setQuantity(s.getQuantity());
        kani.setPrice(shopSelectService.getPrice(Integer.parseInt(s.getMerchId())));

        kani.setDiscountRate(s.getDiscountRate());

        shopStockShows.add(kani);


    }
   

    String shopName = shopSelectService.getShopName(Integer.parseInt(shopId));
    
    // 取得した商品在庫リストをモデルに追加
    model.addAttribute("shopItems",shopStockShows);
    model.addAttribute("shopName",shopName);
    session.setAttribute("shopItems", shopStockShows);
    session.setAttribute("shopName", shopName);
    

    return "c_temp/ShopItems"; // shopItems.htmlを表示
}

}
