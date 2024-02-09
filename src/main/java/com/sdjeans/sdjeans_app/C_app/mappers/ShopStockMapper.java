// ShopStockMapper.java
package com.sdjeans.sdjeans_app.C_app.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.C_app.Entity.ShopStock;

@Mapper
public interface ShopStockMapper {
    
    @Select("SELECT * FROM shopStock WHERE shop_id = #{shopId}")
    List<ShopStock> findByShopItem(int shopId);

    @Select("SELECT shop_name FROM shop WHERE shop_id = #{shopId}")
    String findByShopName(int shopId);

    @Select("SELECT merch_name FROM merch WHERE merch_id = #{merchId}")
    String findByMerchName(int merchId);

    @Select("SELECT price FROM merch WHERE merch_id = #{merchId}")
    int findByMerchPrice(int merchId);


}