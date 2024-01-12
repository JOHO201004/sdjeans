package com.sdjeans.sdjeans_app.C_app.Beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class shopstock {
        //会員番号
    private Integer shopId;
    //商品ID
    private Integer merchId;
    //期限
    private LocalDateTime deadline;
    //数量
    private Integer quantity;
    //割引率
    private Integer discountRate;
}
