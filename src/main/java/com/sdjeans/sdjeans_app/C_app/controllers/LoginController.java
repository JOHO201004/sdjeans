package com.sdjeans.sdjeans_app.C_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.forms.LoginForm;
import com.sdjeans.sdjeans_app.C_app.forms.registerForm;
import com.sdjeans.sdjeans_app.C_app.services.LoginService;
import com.sdjeans.sdjeans_app.C_app.services.registerService;

import jakarta.servlet.http.HttpSession;

@Controller
// @Slf4j
public class LoginController {

    @Autowired
    registerService registerService;

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String Login(@ModelAttribute LoginForm loginForm, HttpSession session, Model model) {
        model.addAttribute("LoginForm", new LoginForm()); // loginFormをモデルに追加する
        if(session.getAttribute("memberId") != null) {
            return "c_temp/home";
        }
        return "c_temp/login";
    }

    @PostMapping("/login")

    public String LoggedInHome(@ModelAttribute LoginForm form, HttpSession session, BindingResult result, Model model) {

        try {
            model.addAttribute("LoginForm", new LoginForm());
            Member foundAccount = loginService.FindByMemberId(form);
            if (loginService.CheckPw(foundAccount, form)) {
                session.setAttribute("memberId", form.getMemberId());
                return "c_temp/home";
            } else {
                System.out.println(foundAccount.getPw()+ foundAccount.getId());
                System.out.println(form.getPw()+form.getMemberId());
                model.addAttribute("bad", "パスワードかIDが違います");
                // result.addError(new ObjectError("notFound", "アカウントが見つかりませんでした。"));
                System.out.println("到達");
                return "c_temp/login";
            }
        } catch (OptimisticLockingFailureException e) {
            result.addError(new ObjectError("global", e.getMessage()));
            model.addAttribute("bad", "パスワードかIDが違います");
            System.out.println("キャッチ");
            return "c_temp/login";
        }
    }


    // 登録画面へ遷移します
    @GetMapping("/regi")
    public String RegisterMember(Model model) {
        model.addAttribute("registerForm", new registerForm());
        return "c_temp/reMember";

    }

    @GetMapping("/home")
    public String home() {
        return "c_temp/home";
    }

    // 登録確認画面へ遷移します
    @PostMapping("/regiCfm")
    public String inputRegisterMember(@ModelAttribute registerForm form, BindingResult result, Model model) {
        try {
            if (!form.getPw().equals(form.getCfmPw())) {
                result.rejectValue("cfmPw", "password.mismatch", "パスワードと確認パスワードが一致しません");
            }
            if (result.hasErrors()) {
                model.addAttribute("registerForm", form);
                return "c_temp/reMember";
            }
            memberInf memInf = new memberInf();
            memInf.setMemberId(form.getMemberId());
            memInf.setPw(form.getPw());
            memInf.setAddress(form.getAddress());
            memInf.setName(form.getName());
            model.addAttribute("inputInf", memInf);

            return "c_temp/reMemberCfm";
        } catch (OptimisticLockingFailureException e) {
            result.addError(new ObjectError("global", e.getMessage()));
            return "c_temp/reMember";
        }
    }

    // 登録成功画面へ遷移します
    @PostMapping("/regiScs")
    public String successRegisterMember(@ModelAttribute memberInf memInf, Model model, BindingResult result) {

        try {
            registerService.InsertMember(memInf);
            return "c_temp/reMemberScs";
        } catch (OptimisticLockingFailureException e) {
            result.addError(new ObjectError("global", e.getMessage()));
            return "c_temp/reMember";
        }
    }
    // ；。；
}
