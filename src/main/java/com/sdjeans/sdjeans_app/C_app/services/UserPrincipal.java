package com.sdjeans.sdjeans_app.C_app.Services;
// package com.sdjeans.sdjeans_app.C_app.services;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.sdjeans.sdjeans_app.C_app.Entity.Member;

// public class UserPrincipal implements UserDetails{
//     private Member member;
    
//     // コンストラクタでMemberオブジェクトを受け取り、このクラスのmemberにセット
//     public UserPrincipal(Member member) {
//         this.member = member;
//     }

//     // ここから下はUserDetailsを使うにあたって必ず必要な設定
//     // 設定されていないものがあるとエラー出る
//     // 権限
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // 権限名は適当にMEMBERとする
//         return Collections.singleton(new SimpleGrantedAuthority("MEMBER"));
//     }

//     // パスワード
//     @Override
//     public String getPassword() {
//         return member.getPw();
//     }

//     // ユーザーID
//     @Override
//     public String getUsername() {
//         return member.getId();
//     }

//     // 有効期限切れていないか
//     @Override
//     public boolean isAccountNonExpired() {
//         return true; // 有効期限はどうでもいいので常にtrueとする
//     }

//     // アカウントロックされていないか
//     @Override
//     public boolean isAccountNonLocked() {
//         return true; // 常にtrue
//     }

//     // 資格情報（パスワードが有効期限切れでないか）
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     // アカウントが有効であるか
//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
