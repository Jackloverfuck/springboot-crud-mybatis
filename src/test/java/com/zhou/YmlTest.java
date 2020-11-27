package com.zhou;

import com.zhou.pojo.MyGirl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class YmlTest {

    @Autowired
    MyGirl myGirl;

    @Value("${mygirl.name}")
    private String name;

    @Test
    public void myTest01(){
        System.out.println(myGirl.toString());

    }

    @Test
    public void myTest02(){
        System.out.println(name);

    }
}
