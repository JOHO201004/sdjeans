package com.sdjeans.sdjeans_app.C_app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Entity.Shop;
import com.sdjeans.sdjeans_app.C_app.Services.MemberInfoShowService;
import com.sdjeans.sdjeans_app.C_app.Services.NearStoreService;

import jakarta.servlet.http.HttpSession;


@Controller
public class NearStoreShowController {
    @Autowired
    MemberInfoShowService memberInfoShowService;

    @Autowired
    NearStoreService nearStoreService;

    @GetMapping("/showNearStores")
    public String showNearStores(HttpSession session, Model model) {
        // 会員情報を取得
        String memberId = (String) session.getAttribute("memberId");/*　IDはある */
        Member member = memberInfoShowService.getMemberInfo(memberId);

        if (member == null) {
            // 会員が存在しない場合の処理
            model.addAttribute("errorMessage", "会員が見つかりません");
            return "error-page"; // エラーページへの遷移を想定
        }

        // 会員の住所と近くの店舗を取得
        String memberAddress = member.getAddress();
        List<Shop> nearStores = nearStoreService.findNearStores(memberAddress);
       

        model.addAttribute("memberId", memberId);
        model.addAttribute("member", member);
        model.addAttribute("nearStores", nearStores);

        return "c_temp/nearStoreShow"; // 近くの店舗を表示するページへの遷移を想定
    }
}
