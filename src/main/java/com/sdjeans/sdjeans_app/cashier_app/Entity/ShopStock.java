package com.sdjeans.sdjeans_app.cashier_app.Entity;

import lombok.Data;

@Data
public class ShopStock {
    private String shopId;
    private String merchId;
    private String deadline;
    private String quantity;
    private String discountRate;
    private String selectedQuantity;
    // GetterとSetter
}

