package com.sdjeans.sdjeans_app.C_app.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Beans.discountChange;
import com.sdjeans.sdjeans_app.C_app.Beans.shopstock;
import com.sdjeans.sdjeans_app.C_app.Mappers.runDiscountMapper;

@Service
public class runDiscountService {
    @Autowired
    runDiscountMapper runDiscountMapper;

    public void expired() {
        List<shopstock> shopstocks = runDiscountMapper.selectShopStock();
        for (int i = 0; shopstocks.size() > i; i++) {
            if (shopstocks.get(i).getDeadline().isBefore(LocalDateTime.now())) {
                runDiscountMapper.delteExpireShopStock(shopstocks.get(i));
            }
        }
    }

    public void updatediscount() {
        List<discountChange> shopstocks = runDiscountMapper.selectDiscountWakaraxn();
        for (int i = 0; shopstocks.size() > i; i++) {
            discountChange shopChange = shopstocks.get(i);
            System.out.println(LocalDateTime.now().plusHours((long) shopChange.getDiscountCriteria()) + "ひいた時間です");
            System.out.println((long) shopChange.getDiscountCriteria() + "ひく時間です");
            System.out.println(shopstocks.get(i).getDeadline() + "期限");
            // System.out.println(shopstocks.get(i).getDeadline().minusHours((long) shopChange.getDiscountCriteria())
            // .isAfter(LocalDateTime.now()) + "論理値アフター");
            // System.out.println(shopstocks.get(i).getDeadline().minusHours((long) shopChange.getDiscountCriteria())
            // .isBefore(LocalDateTime.now()) + "論理値ビフォー");
            // if(LocalDateTime.now().plusHours((long)shopChange.getDiscountCriteria()).isBefore(shopstocks.get(i).getDeadline())){
            if (shopstocks.get(i).getDeadline().minusHours((long) shopChange.getDiscountCriteria())
                    .isBefore(LocalDateTime.now())) {
                // 12-2 < 11
                // 12-2 < 9
                shopstock nextShopstock = new shopstock(shopChange.getShopId(), shopChange.getMerchId(),
                        shopChange.getDeadline(), shopChange.getQuantity(), shopChange.getNextDiscountRate());
                runDiscountMapper.updateDiscount(nextShopstock);

            }
        }
        System.out.println("割引したよ");

    }
}
