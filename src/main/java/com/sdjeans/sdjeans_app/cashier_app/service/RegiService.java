package com.sdjeans.sdjeans_app.cashier_app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.cashier_app.Entity.ShopStock;
import com.sdjeans.sdjeans_app.cashier_app.Mappers.RegiMapper;


@Service
public class RegiService {
    

    @Autowired
    RegiMapper regiMapper;
    
    public List<ShopStock> showShopStock(String shopId){
        
        return regiMapper.findByShopStock(shopId);
        
    }
}
