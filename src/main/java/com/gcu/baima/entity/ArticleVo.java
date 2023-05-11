package com.gcu.baima.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 文章vo
 * @author xiu
 * @create 2023-05-11 9:58
 */
@Data
public class ArticleVo {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "发布日期")
    private Date publicTime;

    @ApiModelProperty(value = "点击量")
    private Integer view;

    @ApiModelProperty(value = "作者名字")
    private String authorname;


    @ApiModelProperty(value = "内容，存储富文本")
    private String content;


}
