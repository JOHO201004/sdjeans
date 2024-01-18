package com.sdjeans.sdjeans_app.merchChecker_app.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.StockForm;
import com.sdjeans.sdjeans_app.merchChecker_app.services.EmployeeService;

import jakarta.servlet.http.HttpSession;


@Controller
// @Slf4j
public class merchCheckerController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/login")
    public String Login(@ModelAttribute LoginForm loginForm, HttpSession session, Model model) {
        model.addAttribute("LoginForm", new LoginForm()); // loginFormをモデルに追加する
        if (session.getAttribute("employee") != null) {
            return "checker_temp/home";
        }
        return "checker_temp/login";
    }

    @PostMapping("/login")
    public String LoginCheckout(@ModelAttribute LoginForm loginForm, HttpSession session, BindingResult result,
            Model model) {
        model.addAttribute("LoginForm", new LoginForm());
        if (employeeService.checkLogin(loginForm)) {
            session.setAttribute("employee", loginForm);
            if (employeeService.checkAdmin(loginForm)) {
                model.addAttribute("admin", true);
            }
            return "checker_temp/home";
        } else {
            model.addAttribute("bad", "パスワードかIDが違います");
            return "checker_temp/login";
        }
    }

    @GetMapping("/reviewStock")
    public String reviewStock(Model model, HttpSession session) {
        LoginForm loginForm = (LoginForm)session.getAttribute("employee");
        model.addAttribute("shopStocks", employeeService.getStock(loginForm));
        return "checker_temp/reviewStock";
    }
    
    @GetMapping("/check")
    public String regiga(Model model) {
        model.addAttribute("StockForm", new StockForm());
        return "checker_temp/checkStock";
    }

    @PostMapping("/check")
    public String regi(@ModelAttribute StockForm stockForm, HttpSession session, BindingResult result, Model model) {
        model.addAttribute("StockForm", new StockForm());
        ArrayList<StockForm> shopStocks = new ArrayList<StockForm>();
        System.out.println(stockForm);
        if (session.getAttribute("checkStocksA") != null) {
            shopStocks.add((StockForm) session.getAttribute("checkStocksA"));
        }
        shopStocks.add(stockForm);
        System.out.println(shopStocks);
        session.setAttribute("checkStocksA", shopStocks);
        model.addAttribute("checkStocks", shopStocks);
        return "checker_temp/checkStock";
    }
}
