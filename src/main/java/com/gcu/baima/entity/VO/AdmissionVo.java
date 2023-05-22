package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
public class AdmissionVo {
    private String id;
    private String name;
    private String info;
    private Integer courseType;
    private Integer duration;
    private Integer year;
    private ArticleVo article;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
