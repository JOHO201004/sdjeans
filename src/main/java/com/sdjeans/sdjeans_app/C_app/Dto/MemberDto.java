package com.sdjeans.sdjeans_app.C_app.Dto;

import jakarta.validation.constraints.NotEmpty;

public class MemberDto {  // 入力データを保持するクラス

    @NotEmpty
    private String id;

    @NotEmpty  // パスワードは空であってはならないというルール
    private String pw;

    @NotEmpty
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}



