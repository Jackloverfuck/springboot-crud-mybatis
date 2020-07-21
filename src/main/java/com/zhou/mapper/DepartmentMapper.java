package com.zhou.mapper;

import com.zhou.pojo.Department;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> selectDepartmentMapperList();
}
