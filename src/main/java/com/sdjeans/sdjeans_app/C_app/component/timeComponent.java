package com.sdjeans.sdjeans_app.C_app.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdjeans.sdjeans_app.C_app.Services.runDiscountService;

@Component
public class timeComponent {

    @Autowired
    runDiscountService runDiscountService;
    @Scheduled(cron = "0 0 * * * *") // 16時になったら実行
    public void runDiscount() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH");
        String s = f.format(date);
        System.out.println(s+"時です");
        System.out.println("1時間が経過しました");
        

    }
        @Scheduled(cron = "0 * * * * *") // 16時になったら実行
    public void testesu() {
        System.out.println("てすてすしました");
        runDiscountService.expired();
        runDiscountService.updatediscount();
        

    }
}
