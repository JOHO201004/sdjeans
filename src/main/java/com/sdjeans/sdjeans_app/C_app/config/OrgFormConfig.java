// package com.sdjeans.sdjeans_app.C_app.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

// import com.sdjeans.sdjeans_app.C_app.Beans.SecurityConfig;

// @EnableWebSecurity
// public class OrgFormConfig extends  {

//     @Bean
//     public SecurityConfig securityFilterChain(HttpSecurity http) throws Exception {

//         http.formLogin().loginPage("/login").defaultSuccessUrl("/inpg01").failureUrl("/login-error").permitAll();
// 		http.authorizeHttpRequests().requestMatchers("/css/**", "/images/**", "/js/**").permitAll().anyRequest().authenticated();
// 	}
// }