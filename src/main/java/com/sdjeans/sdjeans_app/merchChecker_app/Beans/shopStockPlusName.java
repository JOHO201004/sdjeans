package com.sdjeans.sdjeans_app.merchChecker_app.Beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class shopStockPlusName {
    // 店舗ID
    private Integer shopId;
    // 商品ID
    private Integer merchId;
    //商品名
    private String merchName;
    // 期限
    private LocalDateTime deadline;
    // 数量
    private Integer quantity;
    // 割引率
    private Integer discountRate;

}
