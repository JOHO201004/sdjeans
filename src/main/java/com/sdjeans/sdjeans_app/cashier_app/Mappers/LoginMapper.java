package com.sdjeans.sdjeans_app.cashier_app.Mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.cashier_app.Entity.Employee;

@Mapper
public interface LoginMapper {
    

    @Select("SELECT * FROM employee WHERE emp_id = #{empId} AND pw = #{password}")
    public Employee findByEmp(String empId,String password);
}
