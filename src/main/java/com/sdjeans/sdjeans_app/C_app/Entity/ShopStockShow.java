package com.sdjeans.sdjeans_app.C_app.Entity;

import lombok.Data;

@Data
public class ShopStockShow {
    private String merchName;
    private String deadline;
    private int quantity;
    private int price;
    private int discountRate;
}
