package com.sdjeans.sdjeans_app.C_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdjeans.sdjeans_app.C_app.Beans.loginForm;
import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.forms.registerForm;
import com.sdjeans.sdjeans_app.C_app.services.accountService;


@Controller
// @Slf4j
public class LoginController {
    @Autowired
    accountService accountService;


    @GetMapping("/login")
    String getLogin(Model model) {
        model.addAttribute("loginForm", new loginForm()); // loginFormをモデルに追加する
        return "c_temp/login";
    }

    @PostMapping("/home")
    public String Login(@ModelAttribute loginForm form, BindingResult result, Model model){
        try{
            if(form.getMemberId() == 1 && form.getPw().equals("p")){
                System.out.println("a");
            }
        }catch(NullPointerException e){
            
        }
        return "redirect:c_temp/home";
    }

     /**
     * ログイン成功時に呼び出されるメソッド
     * SecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
     * @param model リクエストスコープ上にオブジェクトを載せるためのmap
     * @return helloページのViewName
     */
    @RequestMapping("/home")
    private String init(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);
        return "c_temp/home";

    }




    @GetMapping("/regi")
    public String getRegisterMember(Model model) {
        model.addAttribute("registerForm", new registerForm());
        return "c_temp/reMember";
    }
    @PostMapping("/regiCfm")
    public String inputRegisterMember(@ModelAttribute registerForm form,BindingResult result,Model model){
        try{
            if(!form.getPw().equals(form.getCfmPw())){
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
        }catch(OptimisticLockingFailureException e){
            result.addError(new ObjectError("global", e.getMessage()));
            return "c_temp/reMember";
        }
    }
    @PostMapping("/regiScs")
    public String successRegisterMember(BindingResult result,Model model){
        
        try{
        accountService.InsertMember(memInf);
                }catch(OptimisticLockingFailureException e){
            result.addError(new ObjectError("global", e.getMessage()));
            return "c_temp/reMember";
        }
    }
    
}
