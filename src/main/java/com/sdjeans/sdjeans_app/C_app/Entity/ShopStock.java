package com.sdjeans.sdjeans_app.C_app.Entity;

import lombok.Data;

@Data
public class ShopStock {
    private int shopId;
    private String merchId;
    private String deadline;
    private int quantity;
    private int discountRate;
    // ... 他のフィールドとゲッターセッター
}

