package com.gcu.baima.model;

import lombok.Data;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-16 20:57
 */
@Data
public class UserMessageModel {
    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送类型：USER，KF
     */
    private String fromType;

    /**
     * 发送端id
     */
    private String fromId;
    /**
     * 接收端id
     */
    private String toId;

    /**
     * 接收类型：USER，KF
     */
    private String toType;

    private Date date = new Date();

}
