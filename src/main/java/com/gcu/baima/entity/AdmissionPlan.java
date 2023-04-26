package com.gcu.baima.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdmissionPlan对象", description="")
public class AdmissionPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "招生计划名字")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String info;

    @ApiModelProperty(value = "课程id")
    private Integer courseId;

    @ApiModelProperty(value = "收费")
    private BigDecimal charge;

    @ApiModelProperty(value = "多久毕业，单位：月")
    private Integer duration;

    @ApiModelProperty(value = "所属年份，YYYY")
    private Integer year;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public AdmissionPlan(String name, String info, Integer courseId, BigDecimal charge, Integer duration, Integer year) {
        this.name = name;
        this.info = info;
        this.courseId = courseId;
        this.charge = charge;
        this.duration = duration;
        this.year = year;
    }

    public AdmissionPlan() {
    }
}
