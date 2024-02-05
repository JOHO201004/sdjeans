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

import jakarta.servlet.http.HttpSession;



// ShopItemsSortControllerクラスの修正
@Controller
public class ShopItemsSortController {

    @RequestMapping(value = "/ShopItemsSort", method = {RequestMethod.GET, RequestMethod.POST})
    public String postMethodName(
            @RequestParam(required = false) String sortType,
            HttpSession session,
            Model model) {

                List<ShopStock> shopItems = (List<ShopStock>) session.getAttribute("shopItems");
                String shopName = (String)session.getAttribute("shopName");
                if(shopItems != null){
                    System.out.println(shopItems.size());;
                }else{
                    System.out.println("abcdenulldesu");
                }

        if (shopItems != null && sortType != null) {
            if (sortType.equals("merch")) {
                // 商品IDでソート
                Collections.sort(shopItems, Comparator.comparing(ShopStock::getMerchId));
                System.out.println(shopItems.get(0).getDeadline());
            } else if (sortType.equals("deadline")) {
                // 賞味期限でソート
                Collections.sort(shopItems, Comparator.comparing(ShopStock::getDeadline));
            } else if (sortType.equals("discount")) {
                // 割引率で降順ソート
                Collections.sort(shopItems, Comparator.comparing(ShopStock::getDiscountRate).reversed());


            }
        }

        model.addAttribute("shopItems", shopItems);
        model.addAttribute("shopName",shopName);
        return "c_temp/ShopItems";
    }
}
