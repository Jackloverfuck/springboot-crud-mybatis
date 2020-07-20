package com.zhou.controller;

import com.zhou.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @version : V1.0
 * @ClassName: EmploeeController
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/2/25 21:48
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

}
