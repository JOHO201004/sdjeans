package com.sdjeans.sdjeans_app.C_app.Services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Forms.LoginForm;
import com.sdjeans.sdjeans_app.C_app.Mappers.accountMapper;

@Service
public class LoginService {
    @Autowired
    accountMapper accountMapper;
    @Autowired
    MessageSource messageSource;

    public Member FindByMemberId(LoginForm loginForm) {
        Member foundAccount = accountMapper.FindByMemberId(loginForm);
        
        if (foundAccount == null) {
            throw new OptimisticLockingFailureException(
                    messageSource.getMessage("error.bad", new String[] { "0件見つかったよ" }, Locale.JAPANESE));
        }
        System.out.println("サービスの中です"+foundAccount.getId());
                System.out.println("サービスの中です"+foundAccount.getId());
        // if (foundAccount > 1) {
        // throw new RuntimeException(
        // messageSource.getMessage("error.runtime", new String[] { "二件以上検出されました" },
        // Locale.JAPANESE));
        // }
        return foundAccount;
    }

    public Boolean CheckPw(Member foundAccount, LoginForm loginForm) {
        if (foundAccount.getPw().equals(loginForm.getPw())) {
            return true;
        } else {
            return false;
        }
    }
}