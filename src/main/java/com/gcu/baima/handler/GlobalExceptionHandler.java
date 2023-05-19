package com.gcu.baima.handler;



import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiu
 * @create 2023-04-23 12:50
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        Throwable cause = e.getCause();
        e.printStackTrace();
        String message = cause.getMessage();
        return R.error().message("执行了全局异常处理.." + message);
    }
    //自定义异常
    @ExceptionHandler(BaimaException.class)
    @ResponseBody
    public R gulierror(BaimaException e) {
        e.printStackTrace();
        R code = R.error().message(e.getMsg()).code(e.getCode());
        log.error(e.getMsg());
        return code;
    }
}
