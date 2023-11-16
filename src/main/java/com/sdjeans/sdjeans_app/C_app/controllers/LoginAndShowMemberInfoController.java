package com.sdjeans.sdjeans_app.C_app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginAndShowMemberInfoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login-and-show-info")
    public String loginAndShowInfo(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ユーザー名とパスワードを元に会員IDを取得するクエリ
        String query = "SELECT member_id FROM members WHERE username = ? AND password = ?";
        String memberId = jdbcTemplate.queryForObject(query, String.class, username, password);

        if (memberId != null) {
            // 会員情報を取得するクエリ
            String memberInfoQuery = "SELECT * FROM members WHERE member_id = ?";
            MemberInfo memberInfo = jdbcTemplate.queryForObject(memberInfoQuery, new MemberInfoMapper(), memberId);

            if (memberInfo != null) {
                // 会員情報をセッションに保存
                HttpSession session = request.getSession();
                session.setAttribute("memberInfo", memberInfo);

                // 画面に表示するためにモデルに追加
                model.addAttribute("memberInfo", memberInfo);

                return "member-info"; // 会員情報表示画面のテンプレート名
            }
        }

        return "redirect:/login.html"; // ログイン失敗時のリダイレクト先
    }
}