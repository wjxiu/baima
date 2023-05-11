package com.gcu.baima.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@ApiModel(value="Course对象", description="")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "介绍")
    private String info;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "最大班级人数")
    private Integer maxClassNum;

    @ApiModelProperty(value = "当前人数")
    private Integer currentNum;

    @ApiModelProperty(value = "最大班级数量")
    private Integer maxClassCount;

    @ApiModelProperty(value = "课程类型，枚举，0：前端 ，1：后端 ，2：大数据，3：UI，4：测试 ，5：运维")
    private Integer type;

    @ApiModelProperty(value = "开班时间")
    private Date startTime;

    private Date gmtCreate;

    private Date gmtModified;


}
