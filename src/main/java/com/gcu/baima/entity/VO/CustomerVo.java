package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcu.baima.Enum.AcademicDegree;
import com.gcu.baima.Enum.CustomerJobStatus;
import com.gcu.baima.Enum.GenderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-16 14:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Customervo对象", description = "")
public class CustomerVo {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private Integer age;

    @ApiModelProperty(value = "枚举 ,0:男，1:女")
    private GenderType gender;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "qq号")
    private String qqNumber;

    @ApiModelProperty(value = "毕业学校")
    private String graduateSchool;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "学历 枚举：0：高中，1:大专，2:本科，3:研究生，4:其他")
    private AcademicDegree academicDegree;

    @ApiModelProperty(value = "自己的状态， 枚举：在读，在职，待业，其他")
    private CustomerJobStatus status;

    @ApiModelProperty(value = "自己的简介")
    private String profile;

    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
