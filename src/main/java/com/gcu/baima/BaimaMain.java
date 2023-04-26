package com.gcu.baima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiu
 * @create 2023-04-26 11:25
 */
@EnableSwagger2
@MapperScan("com.gcu.baima.mapper")
@SpringBootApplication(scanBasePackages = {"com.gcu"})
public class BaimaMain {
    public static void main(String[] args) {
        SpringApplication.run(BaimaMain.class,args);
    }
}
