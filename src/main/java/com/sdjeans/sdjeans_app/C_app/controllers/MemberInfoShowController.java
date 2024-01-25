package com.sdjeans.sdjeans_app.C_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Exception.MemberNotFoundException;
import com.sdjeans.sdjeans_app.C_app.Services.MemberInfoShowService;

import jakarta.servlet.http.HttpSession;


// // @Controller
// // public class MemberInfoShowController {
// //     @GetMapping("/memberInfoShow")
// //     public String memberCheck(HttpSession session) {
// //         return new SomeData();
// //     }


    
// // }

@Controller
public class MemberInfoShowController {

    @Autowired
    MemberInfoShowService memberInfoShowService;

    @GetMapping("/memberInfoShow")
    public String getMemberInfo(HttpSession session, Model model) {
        // セッションからMember IDを取得
        String memberId = (String) session.getAttribute("memberId");/*　IDはある */



        try {
        // MemberInfoShowServiceからMemberオブジェクトを取得
        Member member = memberInfoShowService.getMemberInfo(memberId);/* 1/23ここでメンバーの中身がヌルになるで終了 */
        if (member != null) {
            model.addAttribute("member", member);
            model.addAttribute("memberId", memberId);
        }
        } catch (MemberNotFoundException e) {
            
            throw new MemberNotFoundException("Member not found for ID: " + memberId);
            
        }
        // Memberオブジェクトがnullでない場合のみモデルに追加
        

        // Return the view name
        return "c_temp/memberInfo";
    }
}
