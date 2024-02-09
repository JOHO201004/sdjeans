package com.sdjeans.sdjeans_app.C_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Entity.MemberChange;
import com.sdjeans.sdjeans_app.C_app.Services.MemberInfoShowService;

import jakarta.servlet.http.HttpSession;
//import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MemberInfoReriteController {

    @Autowired
    MemberInfoShowService memberInfoShowService;

    @PostMapping("/memberInfoRerite")
    public String memberInfoRerite(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");
        Member member = memberInfoShowService.getMemberInfo(memberId);

        model.addAttribute("member", member);
        model.addAttribute("memberId", memberId);
        
        return "c_temp/memberInfoChange";
    }
    

    @PostMapping("/memberChange")
    public String memberInfoChangeLogic(@ModelAttribute MemberChange formData,Model model,HttpSession session) {
        
        int comp = memberInfoShowService.updateMemberInfo(formData);
        String memberId = (String) session.getAttribute("memberId");
        Member member = memberInfoShowService.getMemberInfo(memberId);

        System.out.println(formData.getMemberId());
        System.out.println(formData.getAddress());
        System.out.println(formData.getName());
        if(comp == 1){
            model.addAttribute("member",member);
            model.addAttribute("memberId", memberId);
        }else{
            System.out.println("naaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
        return "c_temp/memberInfo";
    }
    

}
