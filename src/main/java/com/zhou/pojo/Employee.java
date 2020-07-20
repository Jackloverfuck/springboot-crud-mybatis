package com.zhou.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Employee {
    private int id;
    private String employeeName;
    private String email;
    private int gender;
    private int departmentId;
    private Date date;
}
