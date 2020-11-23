package com.zhou.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要将类标记为容器中的组件才能获取到值
 */

@Data
@Component
@ConfigurationProperties(prefix = "mygirl")
public class MyGirl {

    private int id;
    private String name;
    private int age;
    private double height;
    private double weight;
    private User user;
    private List<String> list;
}
