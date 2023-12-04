package com.sdjeans.sdjeans_app.C_app.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.forms.LoginForm;

@Mapper
public interface accountMapper {

    @Insert("INSERT INTO member VALUES (#{memberId},#{pw},#{name},#{address})")
    public int InsertMember(memberInf memberInf);

    @Select("SELECT * FROM MEMBER WHERE member_id = #{memberId}")
    public Member FindByMemberId(LoginForm loginForm);
}
