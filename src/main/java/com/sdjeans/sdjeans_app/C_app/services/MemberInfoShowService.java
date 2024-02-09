package com.sdjeans.sdjeans_app.C_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Entity.MemberChange;
import com.sdjeans.sdjeans_app.C_app.Exception.MemberNotFoundException;
import com.sdjeans.sdjeans_app.C_app.Mappers.accountMapper;


@Service
public class MemberInfoShowService{

    @Autowired
    accountMapper accountMapper;

    public Member getMemberInfo(String memberId) {

        Member member = accountMapper.MemberInfoShowId(memberId);

        if (member == null) {
            throw new MemberNotFoundException("Member not found for ID: " + memberId);
        }
    
        return member;
    }

    public int updateMemberInfo(MemberChange data){


        return accountMapper.updateMemberInfo(data);

    }
}