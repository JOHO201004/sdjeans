package com.sdjeans.sdjeans_app.cashier_app.Mappers;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.cashier_app.Entity.ShopStock;

@Mapper
public interface RegiMapper {
    

    @Select("SELECT * FROM shopStock WHERE shop_id = #{shopId}")
    public List<ShopStock> findByShopStock(String shopId);
}
