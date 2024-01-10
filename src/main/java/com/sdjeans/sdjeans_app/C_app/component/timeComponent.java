package com.sdjeans.sdjeans_app.C_app.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class timeComponent {

    @Scheduled(cron = "0 43 15 * * *") // 16時になったら実行
    public void myTask() {
        System.out.println("じかんが経過しました");

    }
}
