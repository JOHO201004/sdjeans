package com.sdjeans.sdjeans_app.C_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
import com.sdjeans.sdjeans_app.C_app.mappers.purchaseHistoryMapper;

@Service
public class purchaseHistoryService {
    @Autowired
    purchaseHistoryMapper purchaseHistoryMapper;

    public List<purchaseHistory> getPurchaseHistoriesById(String memberId){
        return purchaseHistoryMapper.selectById(memberId);
    }
}
