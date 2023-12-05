package com.sdjeans.sdjeans_app.C_app.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Beans.merchandise;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistory;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryMainKey;
import com.sdjeans.sdjeans_app.C_app.Beans.purchaseHistoryQuantityUpdate;
import com.sdjeans.sdjeans_app.C_app.mappers.purchaseHistoryMapper;

@Service
public class purchaseHistoryService {
    @Autowired
    purchaseHistoryMapper purchaseHistoryMapper;

    @Autowired
    MessageSource messageSource;

    public List<purchaseHistory> getPurchaseHistoriesById(String memberId) {
        return purchaseHistoryMapper.selectHisById(memberId);
    }

    public merchandise getMerchandisesById(Integer merchId) {
        return purchaseHistoryMapper.selectMerchById(merchId);
    }

    public int deletePurchaseHistory(purchaseHistoryMainKey purchaseHistoryMainKey){
        int cnt = purchaseHistoryMapper.deletePurchaseHistory(purchaseHistoryMainKey);
                if(cnt == 0){
            throw new OptimisticLockingFailureException(messageSource.getMessage("error.Optimis", null,Locale.JAPANESE));
        }
        //二件以上あった場合
        if(cnt > 1){
            throw new RuntimeException(messageSource.getMessage("error.runtime", new String[] {"二件以上検出されました"} ,Locale.JAPANESE));
        }
        return cnt;
    }
    public List<purchaseHistory> sortPurchaseHistoriesByDeadline(Boolean sortOption,String memberId){
        if(sortOption == true){
            return purchaseHistoryMapper.sortAscPurchaseHistories(memberId);
        }else{
            return purchaseHistoryMapper.sortDescPurchaseHistories(memberId);
        }
    }


    public void updatePurchaseHistory(purchaseHistoryQuantityUpdate purchaseHistoryQuantityUpdate){
        int cnt =  purchaseHistoryMapper.updatePurchaseHistory(purchaseHistoryQuantityUpdate);
                if(cnt == 0){
            throw new OptimisticLockingFailureException(messageSource.getMessage("error.Optimis", null,Locale.JAPANESE));
        }
        //二件以上あった場合
        if(cnt > 1){
            throw new RuntimeException(messageSource.getMessage("error.runtime", new String[] {"二件以上検出されました"} ,Locale.JAPANESE));
        }

    }

}
