package com.sdjeans.sdjeans_app.C_app.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.C_app.Beans.merchandise;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;

@Mapper
public interface purchaseHistoryMapper {

    @Select("SELECT * FROM purchaseHistory WHERE member_id = #{memberId}")
    public List<purchaseHistory> selectHisById(String memberId);

    @Select("SELECT * FROM merch WHERE merch_id = #{merchId}")
    public merchandise selectMerchById(Integer merchId);
}
