package com.sdjeans.sdjeans_app.C_app.Beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class purchaseHistoryMainKey {

    //会員番号
    private String memberId;
    //商品ID
    private Integer merchId;
    //期限
    private LocalDateTime deadline;
}
