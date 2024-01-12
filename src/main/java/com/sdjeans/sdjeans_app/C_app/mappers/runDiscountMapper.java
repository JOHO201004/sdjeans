package com.sdjeans.sdjeans_app.C_app.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sdjeans.sdjeans_app.C_app.Beans.discountChange;
import com.sdjeans.sdjeans_app.C_app.Beans.shopstock;

@Mapper
public interface runDiscountMapper {

  @Select("SELECT * FROM shopstock")
  public List<shopstock> selectShopStock();  

  @Delete("DELETE FROM shopstock WHERE shop_id = #{shopId} AND merch_id = #{merchId} AND deadline = #{deadline}")
  public int delteExpireShopStock(shopstock shopstock);

  @Select("select S.shop_id,S.merch_id,S.deadline,S.quantity,S.discount_rate,G.discount_criteria,G.next_discount_rate"+
  "  FROM  shopstock AS S "+
  "JOIN merch AS M ON M.merch_id = S.merch_id "+
  "JOIN genre AS G ON M.genre_id = G.genre_id AND S.discount_rate = G.discount_rate")
  public List<discountChange> selectDiscountWakaraxn();

  @Update("UPDATE shopstock SET discount_rate =#{discountRate} WHERE shop_id = #{shopId} AND merch_id = #{merchId} AND deadline = #{deadline}")
  public int updateDiscount(shopstock shopstock);
} 
