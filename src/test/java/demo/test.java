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
        System.out.println(Ipv4Util.longToIpv4(3232257875L));

    }
}
