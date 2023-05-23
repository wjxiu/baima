package com.gcu.baima.entity.DTO;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-23 22:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
@ApiModel(value = "新增课程参数 对象", description = "")
public class CourseSaveDTO implements Serializable {


    @ApiModelProperty(value = "介绍")
    private String info;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "收费")
    private BigDecimal charge;

    @ApiModelProperty(value = "最大班级人数")
    private Integer maxClassNum;

    @ApiModelProperty(value = "当前人数")
    private Integer currentNum;

    @ApiModelProperty(value = "最大班级数量")
    private Integer maxClassCount;

    @ApiModelProperty(value = "课程类型，枚举，0：前端 ，1：后端 ，2：大数据，3：UI，4：测试 ，5：运维")
    private Integer type;
    @ApiModelProperty(value = "作者id")
    String authorId;
}
