package com.sdjeans.sdjeans_app.merchChecker_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.merchChecker_app.Beans.employee;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;
import com.sdjeans.sdjeans_app.merchChecker_app.mappers.AccountMapper;

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
}
