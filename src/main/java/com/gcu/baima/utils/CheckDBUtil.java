package com.gcu.baima.utils;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.*;

/**
 * 通用检查数据库表工具类
 * @author xiu
 * @create 2023-05-20 19:10
 */
@Component
public class CheckDBUtil {
    //spring:
//  datasource:
//    driver-class-name: com.mysql.cj.jdbc.Driver
//    url: jdbc:mysql://localhost:3306/baima?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true
//    username: root
//    password: 190112
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    private final String driverClassName_ = null;

    private String url_ = null;

    private String username_ = null;

    private String password_ = null;


    /**
     * 检查 数据库表字段为id对应的值是否存在
     *
     * @param tableName 数据库表名字
     * @param value     检查的值,必须为String,Integer
     * @return 存在，返回true,不存在返回false
     */
    public static Boolean checkIdEqual(String tableName, String value) {
        return CheckDBUtil.checkStringEqual(tableName, "id", value);
    }

    /**
     * 检查 实体类对应的数据库表的对应id字段的对应的值是否存在
     *
     * @param clazz 数据库表对应的实体类
     * @param value 检查的值,必须为String,Integer
     * @return 存在，返回true,不存在返回false
     */
    public static Boolean checkIdEqual(Class clazz, String value) {
        return CheckDBUtil.checkStringEqual(clazz, "id", value);
    }


    /**
     * 检查 数据库表字段对应的值是否存在
     *
     * @param tableName 数据库表名字
     * @param fieldName 数据库表字段，字段的类型必须为varchar,har,Integer
     * @param value     检查的值,必须为String
     * @return 存在，返回true,不存在返回false
     */
    public static Boolean checkStringEqual(String tableName, String fieldName, String value) {
        if (StringUtils.isEmpty(tableName)) throw new IllegalArgumentException("缺少表名");
        if (StringUtils.isEmpty(fieldName)) throw new IllegalArgumentException("缺少字段名");
        if (StringUtils.isEmpty(value)) throw new IllegalArgumentException("缺少检查值");
        // Open a connection
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
        ) {
            String query = String.format("select count(1) as res from %s where %s = '%s';", tableName, fieldName, value);
            System.out.println(query);
            // Let us select all the records and display them.
            ResultSet rs = stmt.executeQuery(query);
            // Extract data from result set
            rs.next();
            int res = rs.getInt("res");
            rs.close();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查 实体类对应的数据库表的对应字段的的对应的值是否存在
     *
     * @param clazz 数据库表对应的实体类，必须有@TableName注解注解，否则报错
     * @param value 检查的值,必须为String,Integer
     * @return 存在，返回true,不存在返回false
     */
    public static Boolean checkStringEqual(Class clazz, String field, String value) {
        if (!clazz.isAnnotationPresent(TableName.class)) {
            throw new IllegalArgumentException("类" + clazz.getSimpleName() + "没有@TableName注解，请检查！");
        }
        TableName declaredAnnotation = (TableName) clazz.getDeclaredAnnotation(TableName.class);
        String tableName = declaredAnnotation.value();
        return checkStringEqual(tableName, field, value);
    }


//    暂时忽略
//    public static Boolean checkFieldEqualByEntityProper(Class clazz, String property, String value)  {
//        if (!clazz.isAnnotationPresent(TableName.class)) {
//            throw new IllegalArgumentException("类" + clazz.getSimpleName() + "没有@TableName注解，请检查！");
//        }
//        TableName declaredAnnotation = (TableName) clazz.getDeclaredAnnotation(TableName.class);
//        Field field = null;
//        try {
//            field = clazz.getField(property);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        String tableName = declaredAnnotation.value();
//        return checkStringEqual(tableName, field, value);
//    }


    @Value("${spring.datasource.url}")
    public void setUrl_(String url_) {
        CheckDBUtil.url = url_;
    }

    @Value("${spring.datasource.username}")
    public void setUsername_(String username_) {
        CheckDBUtil.username = username_;
    }

    @Value("${spring.datasource.password}")
    public void setPassword_(String password_) {
        CheckDBUtil.password = password_;
    }

    @Value("${spring.datasource.driverClassName}")
    public static void setDriverClassName_(String driverClassName_) {
        CheckDBUtil.driverClassName = driverClassName_;
    }
}
