package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-11 11:41
 */
@Data
public class AdmissionVo extends AdmissionPlan {
    @ApiModelProperty(value = "作者id")
    private String authorId;

    @ApiModelProperty(value = "作者名称")
    private String authorName;

    @ApiModelProperty(value = "内容")
    private String context;
}
