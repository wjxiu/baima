package com.gcu.baima.Enum;

/**
 * @author xiu
 * @create 2023-05-16 16:47
 */
public enum GenderType {
    男(0), 女(1);
    private final Integer value;

    private GenderType(Integer value) {
        this.value = value;
    }

    public static GenderType valueOf(int value) {
        switch (value) {
            case 0:
                return 男;
            case 1:
                return 女;
            default:
                return null;
        }
    }

    public Integer value() {
        return this.value;
    }
}
