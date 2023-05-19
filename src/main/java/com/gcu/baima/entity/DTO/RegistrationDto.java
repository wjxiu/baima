package com.gcu.baima.entity.DTO;

import com.gcu.baima.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 前端参数vo
 *
 * @author xiu
 * @create 2023-05-12 19:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class RegistrationDto {
    private static final long serialVersionUID = 123L;

    Customer customer;
    String courseId;
}
