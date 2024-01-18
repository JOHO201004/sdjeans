package com.sdjeans.sdjeans_app.merchChecker_app.Beans;

import lombok.Data;

@Data
public class employee {
    //従業員ID
    private String empId;
    //店舗ID
    private Integer shopId;
    //パスワード
    private String pw;
    //名前
    private String name;
    //管理者フラグ
    private Integer isadmin;
}
