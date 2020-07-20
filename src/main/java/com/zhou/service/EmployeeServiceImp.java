package com.zhou.service;

import com.zhou.mapper.EmployeeMapper;
import com.zhou.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: A
 * Date: 2020/7/20 11:55
 * Description:
 */
@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectEmployeeList() {
        return employeeMapper.selectEmployeeList();
    }
}
