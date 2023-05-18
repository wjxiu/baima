package com.gcu.baima.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcu.baima.entity.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 试课申请信息dto类
 *
 * @author xiu
 * @create 2023-05-17 23:39
 */
@Data
public class TrialLessonApplyDto {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String userId;
    private Integer age;

    @ApiModelProperty(value = "枚举 ,0:男，1:女")
    private Integer gender;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "qq号")
    private String qqNumber;

    @ApiModelProperty(value = "毕业学校")
    private String graduateSchool;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "学历 枚举：0：高中，1:大专，2:本科，3:研究生，4:其他")
    private Integer academicDegree;

    @ApiModelProperty(value = "自己的状态， 枚举：在读，在职，待业，其他")
    private Integer status;

    @ApiModelProperty(value = "自己的简介")
    private String profile;
    private String trialLessonId;
    //    申请试课时间段枚举类
    private Integer priod;
}
