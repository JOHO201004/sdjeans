// package com.sdjeans.sdjeans_app.C_app.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.elpmas.test.domain.service.UserDetailsServiceImpl;

// /**
//  * SpringSecurityを利用するための設定クラス
//  * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
//  */
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserDetailsServiceImpl userDetailsService;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     /**
//      * 認可設定を無視するリクエストを設定
//      */
//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         web.ignoring()
//         		.antMatchers("/resources/**");
//     }

//     /**
//      * 認証・認可の情報を設定する
//      */
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests()
//             	.antMatchers("/login").permitAll()
//                 .anyRequest().authenticated();
//         http
//         	.formLogin()
//         		.loginPage("/login")　//ログインページとして使用するurlを設定する
//         		.usernameParameter("username")　//Usernameのパラメータとして使用する項目のnameを設定する
// 			.passwordParameter("password")　//Passwordのパラメータとして使用する項目のnameを設定する
//                 .defaultSuccessUrl("/sample", true)
//                 .failureUrl("/eroor")　//エラー発生時として使用するurlを設定する
// 			.permitAll();　//エラー発生画面も未認証でアクセス出来るようにしないといけない。(この記述がないと指定のurlに遷移せずloginにリダイレクトされる)
//     }

//     /**
//      * 認証時に利用するデータソースを定義する設定メソッド
//      * ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている
//      */
//     @Autowired
//     public void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }
// }







// // package com.sdjeans.sdjeans_app.C_app.config;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;

// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.context.annotation.Bean;
// // // import org.springframework.context.annotation.Configuration;
// // // import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // import org.springframework.security.config.annotation.web.builders.WebSecurity;
// // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// // @Configuration
// // @EnableWebSecurity
// // public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


// //     //フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
// //     @Bean
// //     public BCryptPasswordEncoder passwordEncoder() {
// //         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
// //         return bCryptPasswordEncoder;
// //     }

// //     @Override
// //     public void configure(WebSecurity web) throws Exception {
// //         web.ignoring().antMatchers(
// //                             "/images/**",
// //                             "/css/**",
// //                             "/javascript/**"
// //                             );
// //     }

// //         @Override
// //         protected void configure(HttpSecurity http) throws Exception {
// //             http
// //                 .authorizeRequests()
// //                     //「login.html」はログイン不要でアクセス可能に設定
// //                     .antMatchers("/login").permitAll()
// //                     //上記以外は直リンク禁止
// //                     .anyRequest().authenticated()
// //                 .and()
// //                 .formLogin()
// //                     //ログイン処理のパス
// //                     .loginProcessingUrl("/login")
// //                     //ログインページ
// //                     .loginPage("/login")
// //                     //ログインエラー時の遷移先 ※パラメーターに「error」を付与
// //                     .failureUrl("/login?error")
// //                     //ログイン成功時の遷移先
// //                     .defaultSuccessUrl("/hello", true)
// //                     //ログイン時のキー：名前
// //                     .usernameParameter("username")
// //                     //ログイン時のパスワード
// //                     .passwordParameter("password")
// //                 .and()
// //                 .logout()
// //                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// //                     .logoutUrl("/logout") //ログアウトのURL
// //                     .invalidateHttpSession(true)
// //                     //ログアウト時の遷移先 POSTでアクセス
// //                     .logoutSuccessUrl("/afterLogout.html");
// //         }

// //         @Autowired
// //         LoginUserDetailsService service;
// //         @Override
// //         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// //           auth.userDetailsService(service);
// //         }


// // }
