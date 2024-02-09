package com.sdjeans.sdjeans_app.C_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.sdjeans.sdjeans_app.C_app.Beans.NotificationOption;
import com.sdjeans.sdjeans_app.C_app.Services.ExpireNotificationService;
import com.sdjeans.sdjeans_app.C_app.Services.SendMailService;

import jakarta.servlet.http.HttpSession;


@Controller
public class NotifyController {

    @Autowired
    ExpireNotificationService expireNotificationService;

    @Autowired
    private SendMailService sendMailService;

    // @GetMapping("/subscribe")
    // @ResponseBody
    // public String subscirbe() {
    // return "subscribeです";
    // }

    @GetMapping("/subscribe")
    @ResponseBody
    public DeferredResult<ResponseEntity<String>> subscribe(HttpSession session) {
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();

        // 通知の許可確認
        if (session.getAttribute("checkedPush") != null) {

            expireNotificationService.checkAndNotifyExpiration(session);
            // 新しいデータがあれば、データを設定してレスポンスを返す
            if (session.getAttribute("notifyItems") != null
                    && !((String) session.getAttribute("notifyItems")).isEmpty()) {
                String notifyItem = (String) session.getAttribute("notifyItems");
                System.out.println(notifyItem + "notifyItemsだよ");
                deferredResult.setResult(ResponseEntity.ok(notifyItem));
                if (session.getAttribute("checkedMail") != null) {
                    sendMailService.sendMail(session); // メール送信
                }
                session.removeAttribute("notifyItems");
            } else {
                // データがない場合は、リクエストを保持
                System.out.println(session.getAttribute("notifyItems") + "session無");
                deferredResult.onTimeout(() -> deferredResult.setResult(ResponseEntity.ok("")));
            }
            // deferredResult.setResult(ResponseEntity.ok("ポーリング内容"));

        } else {
            // 通知の許可がない場合
            System.out.println("push許可なし");
            deferredResult.onTimeout(() -> deferredResult.setResult(ResponseEntity.ok("")));
        }

        return deferredResult;
    }

    @GetMapping("/notification")
    public String notification(@ModelAttribute NotificationOption option, Model model) {
        model.addAttribute("NotificationOption", new NotificationOption());
        return "c_temp/notification";
    }

    @PostMapping("/notification")
    public String changeNotificationOption(@ModelAttribute NotificationOption option, Model model,
            HttpSession session) {

        // push許可確認
        if(option.isCheckedPush()){
            System.out.println("pushOk");
            model.addAttribute("push", true);
            session.setAttribute("checkedPush", "pushOK");
        }else{
            System.out.println("pushNo");
            session.removeAttribute("checkedPush");
        }

        // mail許可確認
        if (option.isCheckedMail()) {
            System.out.println("mailOk");
            model.addAttribute("mail", true);
            session.setAttribute("checkedMail", "mailOK");
        } else {
            System.out.println("mailNo");
            session.removeAttribute("checkedMail");
        }

        return "c_temp/changeOpt";
    }

    // @GetMapping("/notification/changeOpt")
    // public String changeOpt(Model model) {
    //     return "c_temp/changeOpt";
    // }
    

}
