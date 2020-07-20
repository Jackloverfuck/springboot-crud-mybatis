package com.zhou.controller;

import com.zhou.pojo.Employee;
import com.zhou.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.DocFlavor;
import java.util.List;

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

    //查询所有员工，返回列表页面
    @GetMapping("/emp")
    public String employeeList(Model model){
        List<Employee> employeeList = employeeService.selectEmployeeList();
        model.addAttribute("emps",employeeList);
        return "emp/list.html";
    }

    @GetMapping("/add")
    public String addEmployee(Model model){

        return  null;
    }
}
