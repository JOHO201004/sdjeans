package com.sdjeans.sdjeans_app.C_app.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
@Scope("singleton")
public class ExpireNotificationService {

    @Autowired
    private purchaseHistoryService purchaseHistoryService;

    // @Autowired
    // private SamplePushController samplePushController;

    private final Set<String> notifiedMerchandise = Collections.newSetFromMap(new ConcurrentHashMap<>());

    // private final AtomicBoolean isTaskRunning = new AtomicBoolean(false);

    // フィールドとして宣言することで、多重生成防止
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Async
    public void checkAndNotifyExpiration(HttpSession session) {
        String memberId = session.getAttribute("memberId").toString();

        // // 現在時刻を取得
        // LocalDateTime now = LocalDateTime.now();

        // // 次の00秒までの待機時間を計算
        // long initialDelay = 60 - now.getSecond();

        // Runnableタスクの実装
        Runnable task = () -> {
            try {
                checkExpiration(session, memberId);
                System.out.println("繰り返しできてるかな" + LocalDateTime.now());
            } catch (IOException e) {
                System.out.println(e + "IOException発生");
            }
        };

        // 最初の実行は初回の00秒になるようにinitialDelayを指定してscheduleAtFixedRateを使用
        executorService.scheduleAtFixedRate(task, 0, 60, TimeUnit.SECONDS);
    }

    public String checkExpiration(HttpSession session, String memberId) throws IOException {
        ArrayList<String> expireMerchList = purchaseHistoryService.notifyDeadline(memberId);
        String notifyItems = "";

        if (expireMerchList != null) {
            for (String expireMerch : expireMerchList) {
                if (!notifiedMerchandise.contains(expireMerch)) {
                    System.out.println(expireMerch + "が賞味期限または消費期限に近づいています！");
                    try {
                        // samplePushController.sendPush();
                        notifiedMerchandise.add(expireMerch);
                        // 通知内容追加
                        if (notifyItems.isEmpty()) {
                            notifyItems = expireMerch;
                        } else {
                            notifyItems += ", " + expireMerch;
                        }
                    } catch (Exception e) {
                        System.out.println("Exception発生" + e);
                    }
                }
            }
            if (!notifyItems.isEmpty()) {
                session.setAttribute("notifyItems", notifyItems);
            }
        }
        return notifyItems;
    }
}
