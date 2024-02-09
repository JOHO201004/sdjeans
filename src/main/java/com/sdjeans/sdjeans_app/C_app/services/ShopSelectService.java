
// SelectShopService.java
package com.sdjeans.sdjeans_app.C_app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Entity.ShopStock; // ShopStockのimportを修正
import com.sdjeans.sdjeans_app.C_app.Mappers.ShopStockMapper; // ShopStockMapperのimportを修正

@Service
public class ShopSelectService {

    @Autowired
    private ShopStockMapper shopStockMapper;

    public List<ShopStock> getShopItems(int shopId) {
        // 店舗IDを使用して商品在庫リストをデータベースから取得するロジック
        return shopStockMapper.findByShopItem(shopId);
    }

    public String getShopName(int shopId){
        return shopStockMapper.findByShopName(shopId);
    }


    public String merchIdChange(String id) {
        // 店舗IDを使用して商品在庫リストをデータベースから取得するロジック
        // for(ShopStock item:changeList){
        //     item.setMerchId(shopStockMapper.findByMerchName(item.getMerchId()));
        // }
        //  return changeList;
        
        return shopStockMapper.findByMerchName(Integer.parseInt(id));
    }

    public int getPrice(int merchId){
        return shopStockMapper.findByMerchPrice(merchId);
    }


}