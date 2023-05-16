package demo;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.crypto.digest.BCrypt;
import com.gcu.baima.BaimaMain;
import com.gcu.baima.mapper.AdmissionPlanMapper;
import com.gcu.baima.service.Back.impl.AdmissionPlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author xiu
 * @create 2023-04-26 11:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaimaMain.class)
public class test {
    @Resource
    AdmissionPlanServiceImpl service;
    @Resource
    AdmissionPlanMapper mapper;

    @Test
    public void name() {
//        System.out.println(BCrypt.hashpw("123123"));
        for (int i = 0; i < 100; i++) {

            System.out.println(BCrypt.checkpw("123123", "$2a$10$MQX9xR7QHjFv5iqQmozYUugVQ5RQffrtliwVzH0QN8RrBo4EYLDCS"));
        }
//        System.out.println(BCrypt.checkpw("321122", "$2a$10$LmUYMqFJg1aQLiaQidCa3euwAHeLy.tuwrE1sRKI.46xLwylxX5iK"));
    }
}
