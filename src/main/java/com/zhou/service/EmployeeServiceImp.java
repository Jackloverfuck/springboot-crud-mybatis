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

    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public Employee selectEmployeeById(Integer id) {
        return employeeMapper.selectEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployee(id);
    }
}
