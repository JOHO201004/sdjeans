package com.sdjeans.sdjeans_app.merchChecker_app.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Insert("INSERT INTO shopstock VALUES (#{shopId},#{merchId},#{deadline},#{quantity},#{discountRate})")
    public int InsertShopstock(shopStock shopStock);

    @Update("UPDATE shopstock SET discount_rate = #{discountRate} WHERE shop_id = #{shopId} AND merch_id = #{merchId} AND deadline = #{deadline}")
    public int updateDiscount(int merchId,int discountRate, LocalDateTime deadline,int shopId);
}
