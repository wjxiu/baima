package com.gcu.baima.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Time;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TrialLesson对象", description="")
public class TrialLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "关联的课程id")
    private String courseId;

    @ApiModelProperty(value = "地点")
    private String location;
    @ApiModelProperty(value = "当前人数")
    private Integer currCustomerNum;
    @ApiModelProperty()
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty()
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
