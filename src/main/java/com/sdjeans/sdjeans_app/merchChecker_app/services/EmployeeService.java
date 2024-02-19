package com.sdjeans.sdjeans_app.merchChecker_app.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.merchChecker_app.Beans.employee;
import com.sdjeans.sdjeans_app.merchChecker_app.Beans.shopStock;
import com.sdjeans.sdjeans_app.merchChecker_app.Beans.shopStockPlusName;
import com.sdjeans.sdjeans_app.merchChecker_app.Mappers.AccountMapper;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.StockForm;

@Service
public class EmployeeService {
    
    @Autowired
    AccountMapper accountMapper;
    
    public boolean checkLogin(LoginForm loginForm){
        employee foundEmployee = accountMapper.findByEmployeeId(loginForm);
        if(foundEmployee == null){
            return false;
        }
        if(foundEmployee.getPw().equals(loginForm.getPw())){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkAdmin(LoginForm loginForm){
        employee foundEmployee = accountMapper.findByEmployeeId(loginForm);
        if (foundEmployee.getIsadmin() == 1) {
            return true;
        } else{
            return false;
        }
    }

    public ArrayList<shopStockPlusName> getStock(LoginForm loginForm){
        employee foundEmployee = accountMapper.findByEmployeeId(loginForm);
        ArrayList<shopStock> stock = accountMapper.findByshopId(foundEmployee);
        ArrayList<shopStockPlusName> namedStock = new ArrayList<shopStockPlusName>();
        for(int i = 0;stock.size() > i ;i++){
            shopStock s = stock.get(i);
            namedStock.add(new shopStockPlusName(s.getShopId(),s.getMerchId(),accountMapper.findByMerchId(s),s.getDeadline(),s.getQuantity(),s.getDiscountRate()));
        }
    
        return namedStock;
    }

    public void addStock(ArrayList<StockForm> shopStocks,LoginForm loginForm){
        employee employee = accountMapper.findByEmployeeId(loginForm);
        int shopId = employee.getShopId();
        for(int i = 0; shopStocks.size() > i;i++){
            StockForm s = shopStocks.get(i);
            shopStock shopStock = new shopStock(shopId,s.getMerchId(),s.getDeadline(),s.getQuantity(),0);
            accountMapper.InsertShopstock(shopStock);
        }
    }

    public void changeDiscount(LoginForm loginForm,int merchId,int discountRate, LocalDateTime deadline ){
        employee employee = accountMapper.findByEmployeeId(loginForm);
        int shopId = employee.getShopId();
        accountMapper.updateDiscount(merchId, discountRate, deadline, shopId);

    }
}
