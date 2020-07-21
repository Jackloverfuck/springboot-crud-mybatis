package com.zhou.controller;

import com.zhou.pojo.Department;
import com.zhou.pojo.Employee;
import com.zhou.service.DepartmentService;
import com.zhou.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.print.DocFlavor;
import java.util.Collection;
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

    @Autowired
    private DepartmentService departmentService;

    //查询所有员工，返回列表页面
    @GetMapping("/emp")
    public String employeeList(Model model){
        List<Employee> employeeList = employeeService.selectEmployeeList();
        model.addAttribute("emps",employeeList);
        return "emp/list.html";
    }

    //增加员工页面
    @GetMapping("/add")
    public String toAddEmployee(Model model){
        List<Department> departmentList = departmentService.selectDepartmentList();
        model.addAttribute("departments",departmentList);
        return "emp/add.html";
    }

    //增加人员
    @PostMapping("/add")
    public  String addEmployee(Employee employee){
        employeeService.insertEmployee(employee);
        return "redirect:/emp";
    }

    //to员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
        //根据id查出来员工
        Employee employee = employeeService.selectEmployeeById(id);
        //将员工信息返回页面
        model.addAttribute("emp",employee);
        //查出所有的部门，提供修改选择
        List<Department> departments = departmentService.selectDepartmentList();
        model.addAttribute("departments",departments);

        return "emp/update.html";
    }

    //编辑人员信息
    @PostMapping("/updateEmp")
    public  String updateEmp(Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:/emp";
    }


    //删除人员
    @GetMapping("/delEmp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model){
        employeeService.deleteEmployee(id);
        return "redirect:/emp";
    }
}
