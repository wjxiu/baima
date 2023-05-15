package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gcu.baima.entity.Customer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-13 19:10
 */
@Data
public class TrialLessonCommentVo {
    private String id;
    private String context;//内容
    private String courseName;//课程名称
    private Integer score;//分数
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;//创建时间
    private String customerName;//用户名
}
