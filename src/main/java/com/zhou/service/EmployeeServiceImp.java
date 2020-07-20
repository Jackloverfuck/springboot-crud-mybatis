package com.zhou.service;

import com.zhou.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: A
 * Date: 2020/7/20 11:55
 * Description:
 */
@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeService employeeService;
}
