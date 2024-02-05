package com.sdjeans.sdjeans_app.cashier_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.cashier_app.Entity.Employee;
import com.sdjeans.sdjeans_app.cashier_app.Mappers.LoginMapper;

@Service
public class EmpLoginService {
    @Autowired
    LoginMapper loginMapper;
    

    public Employee empLogin(String empId,String password){

        

        return loginMapper.findByEmp(empId,password);
    }

}
