package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gcu.baima.Enum.TrialLessonPeriod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-14 17:40
 */
@ApiModel("试课vo类")
@Data
public class TrialLessonVo {
    private String id;
    private String courseId;
    private String courseName;
    @ApiModelProperty("用户选择课程的时间段，管理员用不到")
    private TrialLessonPeriod period;
    private String location;
    //    试课人数，没有限制，仅作记录
    private Integer currNum;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
