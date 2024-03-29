package com.sdjeans.sdjeans_app.C_app.Beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class purchasehistoryOfView {
    //会員番号
    private String memberId;
    //商品ID
    private Integer merchId;
    // 商品名
    private String merchName;
    // 期限
    private LocalDateTime deadline;
    // 数量
    private Integer quantity;
    //期限切れフラグ
    private boolean deadlineFlag;
}
