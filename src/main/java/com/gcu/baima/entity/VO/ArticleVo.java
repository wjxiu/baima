package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private String id;
    private String categoryId;
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

    @ApiModelProperty(value = "分类名")
    private String categoryName;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
