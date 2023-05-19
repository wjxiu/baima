package com.gcu.baima.controller;

import cn.hutool.json.JSONUtil;
import com.gcu.baima.BaimaMain;
import com.gcu.baima.entity.Registration;
import com.gcu.baima.mapper.RegistrationMapper;
import com.gcu.baima.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiu
 * @create 2023-05-19 13:32
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaimaMain.class)
class RegistrationControllerTest {
    @Autowired
    RegistrationController registrationController;

    @Test
    void enroll() {
    }

    @Test
    void isRegistration() {
    }

    @Test
    void agree() {
    }

    @Test
    void deny() {
    }

    @Test
    void page() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 0);
        R page = registrationController.page(1L, 555L, map);
        log.info(JSONUtil.toJsonStr(page));
    }
}