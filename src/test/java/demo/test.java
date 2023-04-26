package demo;

import com.gcu.baima.BaimaMain;
import com.gcu.baima.controller.AdmissionPlanController;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.mapper.AdmissionPlanMapper;
import com.gcu.baima.service.AdmissionPlanService;
import com.gcu.baima.service.impl.AdmissionPlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;

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
//        AdmissionPlan admissionPlan = new AdmissionPlan("Java招生计划","我是一个简介",1,new BigDecimal("123.34"),25,2022 );
//        service.save(admissionPlan);
        System.out.println(mapper.aa());
    }
}
