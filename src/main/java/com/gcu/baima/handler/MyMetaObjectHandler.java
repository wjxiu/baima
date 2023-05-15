package com.gcu.baima.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-08 14:22
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //使用mp实现添加操作时这个方法会执行
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("gmtCreate", date, metaObject);
        this.setFieldValByName("sendTime", date, metaObject);
        this.setFieldValByName("gmtModified", date, metaObject);
    }

    //使用mp实现修改操作时这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
