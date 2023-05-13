package com.gcu.baima.Enum;

/**
 * @author xiu
 * @create 2023-05-12 20:39
 */
//报名状态枚举
public enum EnrollStatus {
    //    待审核
    WAIT(0),
    //    审核中
    CHECKING(1),
    //    失败
    FAILED(2),
    //    成功
    SUCCESS(3);
    private int value;

    private EnrollStatus(int value) {
        this.value = value;
    }

    public static EnrollStatus valueOf(int value) {
        switch (value) {
            case 0:
                return WAIT;
            case 1:
                return CHECKING;
            case 2:
                return FAILED;
            case 3:
                return SUCCESS;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }

}

