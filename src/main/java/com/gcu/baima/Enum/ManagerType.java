package com.gcu.baima.Enum;

/**
 * @author xiu
 * @create 2023-05-16 17:55
 */
public enum ManagerType {
    管理员(0), 推广专员(1);
    //      @ApiModelProperty(value = "枚举 ,0管理员，1 推广专员")
//    private Integer type;
    private Integer value;

    public static ManagerType valueOf(Integer value) {
        switch (value) {
            case 0:
                return 管理员;
            case 1:
                return 推广专员;
            default:
                return null;
        }
    }

    ManagerType(Integer value) {
        this.value = value;
    }

}
