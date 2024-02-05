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
    

    @GetMapping("/empLogin")
    public String Login(@ModelAttribute LoginForm loginForm, HttpSession session, Model model) {
        model.addAttribute("EmployeeLoginForm", new LoginForm()); // loginFormをモデルに追加する
        if (session.getAttribute("employee") != null) {
            if (employeeService.checkAdmin((LoginForm)session.getAttribute("employee"))) {
                model.addAttribute("admin", true);
            }
            return "checker_temp/home";
        }
        return "checker_temp/login";
    }

    @PostMapping("/empLogin")
    public String LoginCheckout(@ModelAttribute LoginForm loginForm, HttpSession session, BindingResult result,
            Model model) {
        model.addAttribute("EmployeeLoginForm", new LoginForm());
        if (employeeService.checkLogin(loginForm)) {
            session.setAttribute("employee", loginForm);
            if (employeeService.checkAdmin((LoginForm)session.getAttribute("employee"))) {
                model.addAttribute("admin", true);
            }
            return "checker_temp/home";
        } else {
            model.addAttribute("bad", "パスワードかIDが違います");
            return "checker_temp/login";
        }
    }

    @PostMapping("/checkConfirm")
    public String addStock(HttpSession session, Model model) {
        Object attributeValue = session.getAttribute("checkStocksA");
        LoginForm loginForm = (LoginForm) session.getAttribute("employee");
        ArrayList<StockForm> stockForms = new ArrayList<>();
        
        if (attributeValue instanceof ArrayList<?>) {
            stockForms = (ArrayList<StockForm>) attributeValue;
            employeeService.addStock(stockForms, loginForm);
        } else {
            System.out.println("ないよっ");
            return "redirect:/check";
        }
        
        if (employeeService.checkAdmin((LoginForm)session.getAttribute("employee"))) {
            model.addAttribute("admin", true);
        }
        return "checker_temp/home";
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
            ArrayList<StockForm> shopStocksA = (ArrayList<StockForm>) session.getAttribute("checkStocksA");
            for(int i = 0;shopStocksA.size() > i;i++){
                shopStocks.add(shopStocksA.get(i));
            }

        }
        shopStocks.add(stockForm);
        System.out.println(shopStocks);
        session.setAttribute("checkStocksA", shopStocks);
        model.addAttribute("checkStocks", shopStocks);
        return "checker_temp/checkStock";
    }

    @GetMapping("/reviewStock")
    public String reviewStock(Model model, HttpSession session) {
        LoginForm loginForm = (LoginForm) session.getAttribute("employee");
        model.addAttribute("shopStocks", employeeService.getStock(loginForm));
        return "checker_temp/reviewStock";
    }
}
