package com.gcu.baima.model;

import lombok.Data;

/**
 * @author xiu
 * @create 2023-05-16 21:34
 */
@Data
public class BaseResponseMessage<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponseMessage success(T data) {
        BaseResponseMessage baseResponseMessage = new BaseResponseMessage();
        baseResponseMessage.code = "0";
        baseResponseMessage.msg = "成功";
        baseResponseMessage.data = data;
        return baseResponseMessage;
    }

    public static <T> BaseResponseMessage error(T data, String msg) {
        BaseResponseMessage baseResponseMessage = new BaseResponseMessage();
        baseResponseMessage.code = "500";
        baseResponseMessage.msg = msg;
        baseResponseMessage.data = data;
        return baseResponseMessage;
    }
}