package com.sdjeans.sdjeans_app.C_app.Beans;

import lombok.Data;

@Data
public class merchandise {
    
    //商品ID
    private Integer merchId;
    //商品名
    private String merchName;
    //ジャンルID
    private Integer genreId;
    //値段
    private Integer price;
}
