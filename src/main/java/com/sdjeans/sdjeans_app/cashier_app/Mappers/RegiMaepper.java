package com.sdjeans.sdjeans_app.cashier_app.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sdjeans.sdjeans_app.cashier_app.Entity.ShopStock;

@Mapper
public interface RegiMaepper {

    @Select("SELECT * FROM shopStock WHERE shop_id = #{shopId}")
    public List<ShopStock> findByShopStock(String shopId);

    @Update("UPDATE shopstock SET quantity = CAST(quantity AS SIGNED) - CAST(#{selectedQuantity} AS SIGNED) WHERE shop_id = #{selectedStock.shopId} AND merch_id = #{selectedStock.merchId} AND deadline = #{selectedStock.deadline}")
    public int findBayList(ShopStock selectedStock, String selectedQuantity, String memberId, String shopId);

    @Insert("INSERT INTO purchaseHistory VALUES(#{memberId}, #{merchId}, #{deadline}, #{selectedQuantity})")
    public int findBBayList(ShopStock bayList, String memberId, String merchId,String deadline,String selectedQuantity);

    @Update("UPDATE purchaseHistory SET quantity = CAST(quantity AS SIGNED) + CAST(#{selectedQuantity} AS SIGNED) WHERE member_id = #{memberId} AND merch_id = #{selectedStock.merchId} AND deadline = #{selectedStock.deadline}")
    public int findBBBayList(ShopStock selectedStock, String selectedQuantity, String memberId);

    @Select("DELETE FROM shopStock WHERE quantity = 0")
    public int findBBBBayList();
}
 