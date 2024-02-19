package com.sdjeans.sdjeans_app.merchChecker_app.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryQuantityUpdate;
import com.sdjeans.sdjeans_app.merchChecker_app.Services.EmployeeService;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.StockForm;

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
            if (employeeService.checkAdmin((LoginForm) session.getAttribute("employee"))) {
                model.addAttribute("admin", true);
            }
            return "redirect:/home";
        }
        return "checker_temp/login";
    }
    @GetMapping("/home")
    public String home(HttpSession session) {
        // expireNotificationService.checkAndNotifyExpiration(session);
        return "checker_temp/home";
    }

    @PostMapping("/empLogin")
    public String LoginCheckout(@ModelAttribute LoginForm loginForm, HttpSession session, BindingResult result,
            Model model) {
        model.addAttribute("EmployeeLoginForm", new LoginForm());
        if (employeeService.checkLogin(loginForm)) {
            session.setAttribute("employee", loginForm);
            if (employeeService.checkAdmin((LoginForm) session.getAttribute("employee"))) {
                model.addAttribute("admin", true);
            }
            return "redirect:/home";
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

        if (employeeService.checkAdmin((LoginForm) session.getAttribute("employee"))) {
            model.addAttribute("admin", true);
        }
        session.removeAttribute("checkStocksA");
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
            for (int i = 0; shopStocksA.size() > i; i++) {
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

    @PostMapping("/updateDiscount")
    public String updatePurchaseHistory(
            @RequestParam("updateMerchId") Integer merchId,
            @RequestParam("updateDeadline") LocalDateTime deadline,
            @RequestParam("newDiscount") Integer discountRate,
            Model model,
            HttpSession session) {
                LoginForm loginForm = (LoginForm) session.getAttribute("employee");
        System.out.println("ここはあっぷでーとです" + "商品" + merchId + "期限" + deadline + "割引率" + discountRate);
        employeeService.changeDiscount(loginForm, merchId, discountRate, deadline);
        model.addAttribute("shopStocks", employeeService.getStock(loginForm));
        return "redirect:/reviewStock";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("employee");
        return "redirect:/EmpLogin";
    }

    @GetMapping("removeStockData")
    public String removeStockData(HttpSession session) {
        session.removeAttribute("checkStocksA");
        return "checker_temp/home";
    }
    

    public static boolean isPast(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isBefore(currentDateTime);
    }
}
