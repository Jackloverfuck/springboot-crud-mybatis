package com.zhou.mapper;

import com.zhou.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where user_name = #{username}")
    User selectByName(@Param("username") String username);

    User selectPasswordByName(@Param("userName") String userName,@Param("password") String password);

}
