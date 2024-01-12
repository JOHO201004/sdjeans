package com.sdjeans.sdjeans_app.C_app.Forms;

import lombok.Data;

@Data
public class registerForm {
    
    //会員番号
    private String memberId;
    //パスワード
    private String pw;
    //確認パスワード
    private String cfmPw;
    //名前
    private String name;
    //住所
    private String address;
}