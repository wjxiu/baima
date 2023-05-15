package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-14 17:40
 */
@Data
public class TrialLessonVo {
    private String id;
    private String courseId;
    private String courseName;
    private Date date;
    //    开始时间
    private Time endTime;
    //  结束时间
    private Time startTime;
    private String location;
    //    试课人数，没有限制，仅作记录
    private Integer currNum;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
