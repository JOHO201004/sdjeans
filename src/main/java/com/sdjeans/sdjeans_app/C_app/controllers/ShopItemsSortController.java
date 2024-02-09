package com.sdjeans.sdjeans_app.C_app.Controllers;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.C_app.Entity.ShopStock;
import com.sdjeans.sdjeans_app.C_app.Entity.ShopStockShow;

import jakarta.servlet.http.HttpSession;


// ShopItemsSortControllerクラスの修正
@Controller
public class ShopItemsSortController {

    @RequestMapping(value = "/ShopItemsSort", method = {RequestMethod.GET, RequestMethod.POST})
    public String postMethodName(
            @RequestParam(required = false) String sortType,
            HttpSession session,
            Model model) {

        List<ShopStockShow> shopItems = (List<ShopStockShow>) session.getAttribute("shopItems");
        String shopName = (String)session.getAttribute("shopName");

        if (shopItems != null && sortType != null) {
            if (sortType.equals("merch")) {
                // 商品名でソート
                Collections.sort(shopItems, Comparator.comparing(ShopStockShow::getMerchName));
            } else if (sortType.equals("deadline")) {
                // 賞味期限でソート
                Collections.sort(shopItems, Comparator.comparing(ShopStockShow::getDeadline));
            } else if (sortType.equals("discount")) {
                // 割引率で降順ソート
                Collections.sort(shopItems, Comparator.comparing(ShopStockShow::getDiscountRate).reversed());
            }
        }

        model.addAttribute("shopItems", shopItems);
        model.addAttribute("shopName", shopName);
        return "c_temp/ShopItems";
    }
}
