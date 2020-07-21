package com.zhou.mapper;

import com.zhou.pojo.Employee;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

//    @Select("select * from employee")
    List<Employee> selectEmployeeList();


    int insertEmployee(Employee employee);

    Employee selectEmployeeById(Integer id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);
}
