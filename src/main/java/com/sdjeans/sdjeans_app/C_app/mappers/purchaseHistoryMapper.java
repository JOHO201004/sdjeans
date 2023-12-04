package com.sdjeans.sdjeans_app.C_app.mappers;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sdjeans.sdjeans_app.C_app.Beans.merchandise;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryMainKey;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryQuantityUpdate;

@Mapper
public interface purchaseHistoryMapper {

    @Select("SELECT * FROM purchaseHistory WHERE member_id = #{memberId}")
    public List<purchaseHistory> selectHisById(String memberId);

    @Select("SELECT * FROM merch WHERE merch_id = #{merchId}")
    public merchandise selectMerchById(Integer merchId);

    @Delete("DELETE FROM purchaseHistory WHERE member_id = #{memberId} AND merch_id = #{merchId} AND deadline = #{deadline}")
    public int deletePurchaseHistory(purchaseHistoryMainKey purchaseHistoryMainKey);

    @Select("SELECT * FROM purchaseHistory ORDER BY deadline ASC")
    public List<purchaseHistory> sortAscPurchaseHistories(LocalDateTime deadline);

    @Select("SELECT * FROM purchaseHistory ORDER BY deadline DESC")
    public List<purchaseHistory> sortDescPurchaseHistories(LocalDateTime deadline);

    @Update("UPDATE purchaseHistory SET quantity = #{quantity} WHERE member_id = #{memberId} AND merch_id = #{merchId} AND deadline = #{deadline}")
    public int updatePurchaseHistory(purchaseHistoryQuantityUpdate purchaseHistoryQuantityUpdate);

}
