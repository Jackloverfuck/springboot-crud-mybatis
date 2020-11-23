package com.zhou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Employee implements Serializable {
    private int id;
    private String employeeName;
    private String email;
    private int gender;
    private int departmentId;
    private Date date;
}
