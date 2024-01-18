package com.sdjeans.sdjeans_app.merchChecker_app.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.merchChecker_app.Beans.employee;
import com.sdjeans.sdjeans_app.merchChecker_app.Beans.shopStock;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;

@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO employee VALUES (#{empId},#{shopId}#{pw},#{name},#{isAdmin})")
    public int InsertMember(employee employee);

    @Select("SELECT * FROM employee WHERE emp_id = #{empId}")
    public employee findByEmployeeId(LoginForm loginForm);

    @Select("SELECT * FROM shopstock WHERE shop_id = #{shopId}")
    public ArrayList<shopStock> findByshopId(employee employee);

    @Select("SELECT merch_name FROM merch WHERE merch_id = #{merchId}")
    public String findByMerchId(shopStock shopStock);
}
