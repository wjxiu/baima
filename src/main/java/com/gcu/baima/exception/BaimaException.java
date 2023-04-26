package com.gcu.baima.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiu
 * @create 2023-04-26 13:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaimaException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码

    private String msg;//异常信息
}
