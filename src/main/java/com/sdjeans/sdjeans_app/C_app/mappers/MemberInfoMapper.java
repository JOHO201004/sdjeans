package com.sdjeans.sdjeans_app.C_app.mappers;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberInfoMapper implements RowMapper<MemberInfo> {
    @Override
    public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(rs.getString("member_id"));
        memberInfo.setMemberName(rs.getString("member_name"));
        // 他の情報も必要に応じて追加
        return memberInfo;
    }
}