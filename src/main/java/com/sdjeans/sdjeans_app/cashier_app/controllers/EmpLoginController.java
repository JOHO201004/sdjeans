package com.sdjeans.sdjeans_app.cashier_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sdjeans.sdjeans_app.cashier_app.Entity.Employee;
import com.sdjeans.sdjeans_app.cashier_app.Entity.LoginForm;
import com.sdjeans.sdjeans_app.cashier_app.Service.EmpLoginService;
import com.sdjeans.sdjeans_app.cashier_app.Service.RegiService;

import jakarta.servlet.http.HttpSession;



@Controller
public class EmpLoginController {

    @Autowired
    EmpLoginService empLoginService;

    @Autowired
    RegiService regiService;

    @GetMapping("/empLogin")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "cashier_temp/empLogin";
    }

    @PostMapping("/empLogin")
    public String loginLogic(@ModelAttribute LoginForm loginForm, HttpSession session, BindingResult result, Model model) {
        String empId = loginForm.getEmpId();
        String password = loginForm.getPassword();

        Employee emp = empLoginService.empLogin(empId,password);

        if (emp != null) {
            session.setAttribute("emp", emp);

            model.addAttribute("shopStock",regiService.showShopStock(emp.getShopId()));

            return "cashier_temp/regi";
        } else {
            return "cashier_temp/empLogin";
        }
    }
}
