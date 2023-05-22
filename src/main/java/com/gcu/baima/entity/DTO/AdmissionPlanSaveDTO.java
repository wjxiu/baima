package com.gcu.baima.entity.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 保存招生计划dto
 *
 * @author xiu
 * @create 2023-05-22 15:09
 */
@Data
public class AdmissionPlanSaveDTO {
    private String name;
    private String info;
    private Integer courseType;
    private Integer duration;
    private Integer year;

    @ApiModelProperty(value = "文章标题")
    private String title;


    @ApiModelProperty(value = "点击量")
    private Integer view;
    @ApiModelProperty(value = "作者id")
    private String authorId;

    @ApiModelProperty(value = "内容，存储富文本")
    private String content;
}
