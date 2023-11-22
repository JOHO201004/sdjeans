package com.sdjeans.sdjeans_app.C_app.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.sdjeans.sdjeans_app.C_app.Beans.memberInf;
import com.sdjeans.sdjeans_app.C_app.Entity.Member;

@Mapper
public interface accountMapper {

    @Insert("INSERT INTO member VALUES (#{memberId},#{pw},#{name},#{address})")
    public int InsertMember(memberInf memberInf);

    Member findByMembername(String username);
}
