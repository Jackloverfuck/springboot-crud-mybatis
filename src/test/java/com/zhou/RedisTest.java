package com.zhou;

import com.zhou.mapper.EmployeeMapper;
import com.zhou.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串

    @Autowired
    RedisTemplate redisTemplate;//key:value 对象


    @Autowired
    RedisTemplate<Object, Object> newRedisTemplate;


    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * redis常用数据类型测试
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue();
     * stringRedisTemplate.opsForList();
     * stringRedisTemplate.opsForSet();
     * stringRedisTemplate.opsForHash();
     * stringRedisTemplate.opsForZSet();
     */
    //字符类型测试
    @Test
    public void testStringRedis() {
        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println("msg:" + msg);

        stringRedisTemplate.opsForList().leftPush("mylist", "张三");
        stringRedisTemplate.opsForList().leftPush("mylist", "李四");
        stringRedisTemplate.opsForList().leftPush("mylist", "王五");

    }

    //对象类型测试
    @Test
    public void testRedis01() {
        Employee emp = employeeMapper.selectEmployeeById(1);
        redisTemplate.opsForValue().set("emp-01", emp);
        redisTemplate.opsForValue().set("emp-02", emp);


    }

    //对象类型测试(转化为json)
    @Test
    public void testRedis02() {
        Employee emp = employeeMapper.selectEmployeeById(13);
        newRedisTemplate.opsForValue().set(emp.getId()+emp.getEmployeeName(), emp);

    }
}