// package com.sdjeans.sdjeans_app.C_app.Filter;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class FilterConfiguration {

//     @Bean
//     public FilterRegistrationBean hogeFilter() {
//         // FilterをnewしてFilterRegistrationBeanのコンストラクタに渡す
//         FilterRegistrationBean bean = new FilterRegistrationBean(new HogeFilter());
//         // Filterのurl-patternを指定（可変長引数なので複数指定可能）
//         bean.addUrlPatterns("/*");
//         // Filterの実行順序。整数値の昇順に実行される
//         bean.setOrder(Integer.MIN_VALUE);
//         return bean;
//     }

//     @Bean
//     public FilterRegistrationBean fugaFilter() {
//         FilterRegistrationBean bean = new FilterRegistrationBean(new FugaFilter());
//         bean.addUrlPatterns("/*");
//         // HogeFilterの後に実行される
//         bean.setOrder(Integer.MIN_VALUE + 1);
//         return bean;
//     }

//     // 何個でもFilterを定義可能
// }
