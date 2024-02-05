package com.sdjeans.sdjeans_app.cashier_app.Entity;

import java.util.List;

import lombok.Data;

@Data
public class RegiForm {
    private String memberId;
    private List<ShopStock> selectedStocks;
    // 他の必要なフィールド、ゲッター、セッターを追加
}
