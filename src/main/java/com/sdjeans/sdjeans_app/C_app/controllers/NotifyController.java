package com.sdjeans.sdjeans_app.C_app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.sdjeans.sdjeans_app.C_app.Services.ExpireNotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NotifyController {

    @Autowired
    ExpireNotificationService expireNotificationService;

    // @GetMapping("/subscribe")
    // @ResponseBody
    // public String subscirbe() {
    //     return "subscribeです";
    // }

    @GetMapping("/subscribe")
    @ResponseBody
    public DeferredResult<ResponseEntity<String>> subscribe(HttpSession session) {
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();

        expireNotificationService.checkAndNotifyExpiration(session);
        // 新しいデータがあれば、データを設定してレスポンスを返す
        if (session.getAttribute("notifyItems") != null && !((String) session.getAttribute("notifyItems")).isEmpty()) {
            String notifyItem = (String)session.getAttribute("notifyItems");
            System.out.println(notifyItem + "notifyItemsだよ");
            deferredResult.setResult(ResponseEntity.ok(notifyItem));
            session.removeAttribute("notifyItems");
        } else {
            // データがない場合は、リクエストを保持
            System.out.println(session.getAttribute("notifyItems") + "session無");
            deferredResult.onTimeout(() -> deferredResult.setResult(ResponseEntity.ok("")));
        }
        // deferredResult.setResult(ResponseEntity.ok("ポーリング内容"));

        return deferredResult;
    }

}
