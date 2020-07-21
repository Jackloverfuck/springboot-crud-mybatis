package com.zhou.service;

import com.zhou.mapper.DepartmentMapper;
import com.zhou.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * User: A
 * Date: 2020/7/21 10:47
 * Description:
 */
@Service
public class DepartmentServiceImp implements DepartmentService{

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectDepartmentList() {
        return departmentMapper.selectDepartmentMapperList();
    }
}
