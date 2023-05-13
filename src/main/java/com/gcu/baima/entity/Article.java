package com.gcu.baima.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "发布日期")
    private Date publicTime;

    @ApiModelProperty(value = "点击量")
    private Integer view;

    @ApiModelProperty(value = "文章分类id")
    private String acId;

    @ApiModelProperty(value = "作者id")
    private String authorId;

    private String title;

    @ApiModelProperty(value = "内容，存储富文本")
    private String content;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    public void setId(String id) {
        this.id = id;
    }
}
