package com.zhou.service;

import com.zhou.mapper.DepartmentMapper;
import com.zhou.pojo.Department;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Map<Object, Object> map= new HashMap<>();
        map.put(new User(), "www");
        return departmentMapper.selectDepartmentMapperList();
    }
}
