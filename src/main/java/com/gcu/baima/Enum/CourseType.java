package com.gcu.baima.Enum;

/**
 * @author xiu
 * @create 2023-05-12 21:03
 */
public enum CourseType {
    //    0：前端 ，1：后端 ，2：大数据，3：UI，4：测试 ，5：运维，Operations
    前端(0), 后端(1), 大数据(2), UI(3), 测试(4), 运维(5);
    private Integer value;

    private CourseType(Integer value) {
        this.value = value;
    }

    public static CourseType valueOf(int value) {
        switch (value) {
            case 0:
                return 前端;
            case 1:
                return 后端;
            case 2:
                return 大数据;
            case 3:
                return UI;
            case 4:
                return 测试;
            case 5:
                return 运维;
            default:
                return null;
        }
    }

    public Integer getValue() {
        return value;
    }

    public Integer value() {
        return this.value;
    }
}
