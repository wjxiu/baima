package com.gcu.baima.Enum;

import lombok.Data;

/**
 * @author xiu
 * @create 2023-05-12 20:39
 */

//报名状态枚举
public enum EnrollStatus {
    //    待审核
    待审核(0),
    //    审核中
    审核中(1),
    //    失败
    失败(2),
    //    成功
    成功(3);
    private Integer value;

    private EnrollStatus(int value) {
        this.value = value;
    }

    public static EnrollStatus valueOf(Integer value) {
        switch (value) {
            case 0:
                return 待审核;
            case 1:
                return 审核中;
            case 2:
                return 失败;
            case 3:
                return 成功;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

