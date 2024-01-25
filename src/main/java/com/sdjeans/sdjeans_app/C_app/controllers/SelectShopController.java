package com.sdjeans.sdjeans_app.C_app.Controllers;  // 正しいパッケージ名に修正

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.C_app.Entity.ShopStock;
import com.sdjeans.sdjeans_app.C_app.Services.ShopSelectService;

@Controller
public class SelectShopController {

    @Autowired
    private ShopSelectService shopSelectService;

    @PostMapping("/selectShopShowItem")
public String showShopItems(@RequestParam(name = "shopId") String shopId, Model model) {
    
    // 店舗IDを使用して商品在庫リストを取得するサービスメソッドを呼び出す
    List<ShopStock> shopItems = (shopSelectService.getShopItems(Integer.parseInt(shopId)));


    //商品IDから商品名にして
    List<ShopStock> changedItems = shopSelectService.merchIdChange(shopItems);
    //いい感じのリストに作り直して

    String shopName = shopSelectService.getShopName(Integer.parseInt(shopId));
    
    
    // 取得した商品在庫リストをモデルに追加
    model.addAttribute("shopItems", changedItems);
    model.addAttribute("shopName",shopName);

    return "c_temp/ShopItems"; // shopItems.htmlを表示
}

}
