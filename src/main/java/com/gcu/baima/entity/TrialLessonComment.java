package com.gcu.baima.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

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
@TableName("trial_lesson_comment")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TrialLessonComment对象", description="")
public class TrialLessonComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "评论内容")
    private String context;

    @ApiModelProperty(value = "试课课程id")
    private String trialId;

    @ApiModelProperty(value = "试课的客户id")
    private String customerId;

    @ApiModelProperty(value = "评分 [1,5]")
    private Integer score;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
}
