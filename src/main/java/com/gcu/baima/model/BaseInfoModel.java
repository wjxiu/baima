package com.gcu.baima.model;

/**
 * @author xiu
 * @create 2023-05-16 20:33
 */
public class BaseInfoModel<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponseMessage success(T data) {
        return BaseInfoModel.success(data);
    }
}
