package com.zhou.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhou.compoment.SqlPrintInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.zhou.mapper"},sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {
    //数据源
    @Bean(name = "dataSource")
    public DataSource dataSourceLocal() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType("POOLED");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.97.127.213:3306/zlk_springboot_crud?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        //构建sqlSessionFactory
        SqlSessionFactoryBean factory=new SqlSessionFactoryBean();

        //添加数据源
        factory.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        factory.setConfiguration(configuration);

        //标记mapper文件位置
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        String mapperLocation="classpath:mapper/*.xml";
        Resource[] mappers = resourceResolver.getResources(mapperLocation);
        factory.setMapperLocations(mappers);

        //添加拦截器
        factory.setPlugins(new SqlPrintInterceptor());

        //返回sqlSessionFactory
        return factory.getObject();
    }
}
