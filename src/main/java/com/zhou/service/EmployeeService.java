package com.zhou.service;

import com.zhou.pojo.Employee;

import java.util.List;

/**
 * User: A
 * Date: 2020/7/20 11:54
 * Description:
 */
public interface EmployeeService {

    List<Employee> selectEmployeeList();

    int insertEmployee(Employee employee);

    Employee selectEmployeeById(Integer id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);
}
