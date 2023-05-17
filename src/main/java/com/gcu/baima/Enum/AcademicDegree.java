package com.gcu.baima.Enum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiu
 * @create 2023-05-16 16:43
 */
public enum AcademicDegree {
    高中(0), 大专(1), 本科(2), 研究生(3), 其他(4);
    private Integer value;

    private AcademicDegree(Integer value) {
        this.value = value;
    }

    public static AcademicDegree valueOf(Integer value) {
        switch (value) {
            case 0:
                return 高中;
            case 1:
                return 大专;
            case 2:
                return 本科;
            case 3:
                return 研究生;
            case 4:
                return 其他;
            default:
                return null;
        }
    }

    public Integer value() {
        return this.value;
    }

}