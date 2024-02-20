package com.sdjeans.sdjeans_app.cashier_app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    public int updateShopStocks(ShopStock selectedStock,String memberId) {
        return regiMapper.findBayList(selectedStock, selectedStock.getSelectedQuantity(), memberId,selectedStock.getShopId());
    }

    public int insertShopStocks(ShopStock selectedStock,String memberId){
        try {
            return regiMapper.findBBayList(selectedStock, memberId , selectedStock.getMerchId(),selectedStock.getDeadline(),selectedStock.getSelectedQuantity());
        } catch (DuplicateKeyException e) {
            // 重複エラーが発生した場合に代替処理を呼び出す
            return notInsertShopStocks(selectedStock, memberId);
        }
    }
    

    public int notInsertShopStocks(ShopStock selectedStock,String memberId) {
        return regiMapper.findBBBayList(selectedStock, selectedStock.getSelectedQuantity(), memberId);
    }

    public int deleatShopStocks(ShopStock selectedStock, String memberId) {
        Integer result = regiMapper.findBBBBayList(selectedStock, memberId);
        if (result == null) {
            System.out.println("findBBBBayList returned null");
            // nullの場合の処理を行う
            return 0; // 仮の戻り値です。適切な値に置き換えてください。
        }
        // resultを適切な値に変換して返す
        return 1; // 仮の戻り値です。適切な値に置き換えてください。
    }
}
