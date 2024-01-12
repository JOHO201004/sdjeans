package com.sdjeans.sdjeans_app.C_app.Beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class discountChange {
    // 店舗ID
    private Integer shopId;
    // 商品ID
    private Integer merchId;
    //期限
    private LocalDateTime deadline;
    //数量
    private Integer quantity;
    //割引率
    private Integer discountRate;
    //割引条件
    private Integer discountCriteria;
    //変更後の割引率
    private Integer nextDiscountRate;
    
}
