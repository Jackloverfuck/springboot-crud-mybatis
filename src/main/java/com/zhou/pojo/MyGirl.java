package com.zhou.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要将类标记为容器中的组件才能获取到值
 */

@Data
@Component
@ConfigurationProperties(prefix = "mygirl")//绑定配置文件中的内容
@PropertySource(value = {"classpath:mygirl.properties"})//指定要读取的配置文件
public class MyGirl {

    private int id;
    private String name;
    private int age;
    private double height;
    private double weight;
    private User user;
    private List<String> list;
}

