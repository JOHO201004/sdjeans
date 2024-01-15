package com.sdjeans.sdjeans_app.merchChecker_app.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sdjeans.sdjeans_app.merchChecker_app.Beans.employee;
import com.sdjeans.sdjeans_app.merchChecker_app.forms.LoginForm;

@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO employee VALUES (#{empId},#{pw},#{name},#{isAdmin})")
    public int InsertMember(employee employee);

    @Select("SELECT * FROM employee WHERE emp_id = #{empId}")
    public employee findByEmployeeId(LoginForm loginForm);
}
