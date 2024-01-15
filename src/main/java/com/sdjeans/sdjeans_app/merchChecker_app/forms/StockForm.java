package com.sdjeans.sdjeans_app.merchChecker_app.forms;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StockForm {
    // 店舗ID
    private Integer shopId;
    // 商品ID
    private Integer merchId;
    // 期限
    private LocalDateTime deadline;
    //数量
    private Integer quantity;
}
