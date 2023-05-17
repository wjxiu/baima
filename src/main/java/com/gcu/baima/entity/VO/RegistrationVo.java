package com.gcu.baima.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gcu.baima.Enum.EnrollStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author xiu
 * @create 2023-05-13 14:06
 */
@Data
public class RegistrationVo {
    private String id;
    private String customerId;
    private String customerName;
    private String courseId;
    private String courseName;
    private String managerId;
    private String managerName;
    private EnrollStatus enrollStatus;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
