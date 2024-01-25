package com.sdjeans.sdjeans_app.C_app.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.C_app.Entity.Shop;

@Mapper
public interface NearStoreShowMapper {

    @Select("SELECT * FROM shop WHERE address = #{address}")
    public List<Shop> nearStores(String address);
}
