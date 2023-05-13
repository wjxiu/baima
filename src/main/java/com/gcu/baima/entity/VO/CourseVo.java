package com.gcu.baima.entity.VO;

import com.gcu.baima.Enum.CourseType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-12 21:08
 */
@Data
public class CourseVo {
    private String id;
    private String info;
    private String name;
    private BigDecimal charge;
    private Integer currentNum;
    //  学生人数上限
    private Integer maxNum;
    private CourseType CourseType;
    private Date startTime;

}
