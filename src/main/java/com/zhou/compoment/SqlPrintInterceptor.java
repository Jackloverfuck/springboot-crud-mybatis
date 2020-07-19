package com.zhou.compoment;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
// 对Executor执行器中指定的方法进行拦截，不使用该注解则
// 不会
@Component
// 拦截执行器的任何方法
public class SqlPrintInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("=======intercept(Invocation invocation)======");
        Object[] objs = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objs[0];
        Object currentArgs = objs[1];
        BoundSql boundSql = ms.getBoundSql(currentArgs);
        String sql = boundSql.getSql();
        // System.out.println("sql:"+sql);
        String sqlId = ms.getId(); // 获取到节点的id,即sql语句的id
        Configuration configuration = ms.getConfiguration(); // 获取节点的配置
        Object returnValue = null;
        long start = System.currentTimeMillis();
        returnValue = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = (end - start);
//        if (time > 1) {
//            String sql = getSql(configuration, boundSql, sqlId, time);
//            System.err.println(sql);
//        }
        String sql1 = getSql(configuration, boundSql, sqlId, time); // 获取到最终的sql语句
        System.out.println("===========================================SQL开始================================================");
        System.out.println(sql1);
        System.out.println("===========================================SQL结束================================================");

        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
//		System.out.println("=======plugin(Object target)=======");
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);// 解析 @Intercepts，返回（jdk动态代理）代理对象
            // 没有则抛异常
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("======setProperties(Properties properties)=======");
    }


    // 封装了一下sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append("执行时间 = " + time + " \n");
        str.append("xml路径 = \n " + sqlId);
        str.append(" \n");
        str.append(sql);
        return str.toString();
    }

    // 进行？的替换
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject(); // 获取参数
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//		String sql = boundSql.getSql().replaceAll("[\\s]+", " "); // sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql();
        if (parameterMappings != null && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry(); // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换<br>
            // //
            // 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);// MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName); // 该分支是动态sql
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        sql = sql.replaceFirst("\\?", "缺失");
                    } // 打印出缺失，提醒该参数缺失并防止错位
                }
            }
        }
        return sql;
    }

    /*
     * <br> *如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号；
     * 对参数是null和不是null的情况作了处理<br>
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }
}