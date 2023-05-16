package com.gcu.baima.Enum;

/**
 * @author xiu
 * @create 2023-05-16 16:49
 */
public enum CustomerJobStatus {
    在读(0), 在职(1), 待业(2), 其他(3);
    private final int value;

    public static CustomerJobStatus valueOf(int value) {
        switch (value) {
            case 0:
                return 在读;
            case 1:
                return 在职;
            case 2:
                return 待业;
            case 3:
                return 其他;
            default:
                return null;
        }
    }
//        @ApiModelProperty(value = "自己的状态， 枚举：在读，在职，待业，其他")


    CustomerJobStatus(int value) {
        this.value = value;
    }
}
