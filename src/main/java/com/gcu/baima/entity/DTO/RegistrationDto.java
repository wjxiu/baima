package com.gcu.baima.entity.DTO;

import com.gcu.baima.entity.Customer;
import lombok.Data;

/**
 * 前端参数vo
 *
 * @author xiu
 * @create 2023-05-12 19:07
 */
@Data
public class RegistrationDto {
    Customer customer;
    String courseId;
}
