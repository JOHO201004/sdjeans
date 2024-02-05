package com.sdjeans.sdjeans_app.C_app.Services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.Forms.registerForm;
import com.sdjeans.sdjeans_app.C_app.Mappers.accountMapper;

@Service
public class registerService {
    @Autowired
    accountMapper accountMapper; // 断念
    @Autowired
    MessageSource messageSource;

    public int InsertMember(memberInf memberInf) {

        int cnt = accountMapper.InsertMember(memberInf);
        if (cnt == 0) {
            throw new OptimisticLockingFailureException(
                    messageSource.getMessage("error.Optimis", null, Locale.JAPANESE));
        }
        // 二件以上あった場合
        if (cnt > 1) {
            throw new RuntimeException(
                    messageSource.getMessage("error.runtime", new String[] { "二件以上検出されました" }, Locale.JAPANESE));
        }
        return cnt;
    }

    public boolean existMemberId(registerForm registerForm){
        if (accountMapper.existMemberId(registerForm) == null) {
            System.out.println("NULLだよ");
            return false;
        }else{
            return true;
        }
    }

}
