package com.sdjeans.sdjeans_app.C_app.Services;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
@EnableAsync
public class ExpireNotificationService {

    @Autowired
    purchaseHistoryService purchaseHistoryService;

    @Async
    public void checkAndNotifyExpiration(HttpSession session){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        String memberId = session.getAttribute("memberId").toString();

        Runnable task = () -> {
            checkExpiration(memberId);
            System.out.println("繰り返しできてるかな");
        };

        executorService.scheduleWithFixedDelay(task, 0, 60, TimeUnit.SECONDS);
    }

    public void checkExpiration(String memberId) {
        ArrayList<String> expireMerchList = purchaseHistoryService.notifyDeadline(memberId);
        // プッシュ通知の処理
            if (expireMerchList != null) {
                ArrayList<String> expireMerchNameList = purchaseHistoryService.notifyDeadline(memberId);
                for (int i = 0; expireMerchNameList.size() > i; i++) {
                    System.out.println(expireMerchNameList.get(i) + "が賞味期限または消費期限に近づいています！");
                }
            }else{
                checkExpiration(memberId);
            }
    }
}
