package com.sdjeans.sdjeans_app.C_app.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Entity.MemberChange;
import com.sdjeans.sdjeans_app.C_app.Forms.LoginForm;
import com.sdjeans.sdjeans_app.C_app.Forms.registerForm;

@Mapper
public interface accountMapper {

    @Insert("INSERT INTO member VALUES (#{memberId},#{pw},#{name},#{address})")
    public int InsertMember(memberInf memberInf);

    @Select("SELECT * FROM MEMBER WHERE member_id = #{memberId}")
    public Member FindByMemberId(LoginForm loginForm);

    @Select("SELECT * FROM MEMBER WHERE member_id = #{memberId}")
    public Member MemberInfoShowId(String memberId);

    @Select("SELECT * FROM MEMBER WHERE member_id = #{memberId}")
    public Member existMemberId(registerForm registerForm);


    @Update("UPDATE MEMBER set name = #{name}, address = #{address} WHERE member_id = #{memberId}")
    public int updateMemberInfo(MemberChange data);
}
