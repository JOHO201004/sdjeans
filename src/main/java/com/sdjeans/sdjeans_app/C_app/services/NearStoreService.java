package com.sdjeans.sdjeans_app.C_app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Entity.Shop;
import com.sdjeans.sdjeans_app.C_app.Mappers.NearStoreShowMapper;

@Service
public class NearStoreService {
        @Autowired
        NearStoreShowMapper nearStoreShowMapper;

    public List<Shop> findNearStores(String memberAddress) {
        // 会員の住所を含む近くの店舗を検索するクエリ
        List<Shop> Shop = nearStoreShowMapper.nearStores(memberAddress);
       
        return Shop;
    }
}
