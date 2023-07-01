package com.gcu.baima.service.Front.Impl;

import cn.hutool.crypto.SecureUtil;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiu
 * @create 2023-06-04 16:34
 */
@RunWith(MockitoJUnitRunner.class)
class FrontSignServiceImplLoginTest {
    @InjectMocks
    FrontSignServiceImpl frontSignService;
    @Mock
    CustomerService customerService;
    @BeforeEach
    void setUp() {
        // 开启mock
        MockitoAnnotations.initMocks(this);
    }
//正确登录
    @Test
    void correctloginFront() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("123");
        loginVo.setPassword("123456");
        Customer customer = new Customer();
        String s = SecureUtil.md5().digestHex(loginVo.getPassword());
        customer.setPassword(s);
        customer.setName("123");
        customer.setId("1");
        when(customerService.getOne(any())).thenReturn(customer);
//        assertThrows(BaimaException.class, () -> {
        String s1 = frontSignService.loginFront(loginVo);
//        }, "报错");
        assertNotEquals(s1,null);
    }
    @Test
    void nullLoginVoAndnullCustomer() {
        LoginVo loginVo = new LoginVo();
        when(customerService.getOne(any())).thenReturn(null);
//        String s = frontSignService.loginFront(loginVo);
        assertThrows(BaimaException.class,()->{
           frontSignService.loginFront(loginVo);
        },"应该抛出异常");
    }
//没有注册过
    @Test
    void LoginVoAndnullCustomer() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("123");
        loginVo.setPassword("123456");
        when(customerService.getOne(any())).thenReturn(null);
        assertThrows(BaimaException.class,()->{
            frontSignService.loginFront(loginVo);
        },"应该抛出baima异常");
    }
//    错误密码
    @Test
    void wrongPassword() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("123");
        loginVo.setPassword("123456");
        Customer customer = new Customer();
        customer.setPassword("qqqqq");
        customer.setName("123");
        customer.setId("1");
        when(customerService.getOne(any())).thenReturn(customer);
        assertThrows(BaimaException.class,()->{
            frontSignService.loginFront(loginVo);
        },"应该抛出baima异常");
    }
//    用户名太长报错
    @Test
    void toolongUserName(){
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("11111111111111111111111111111111111111123");
        loginVo.setPassword("123456");
        when(customerService.getOne(any())).thenReturn(null);
        assertThrows(BaimaException.class,()->{
            frontSignService.loginFront(loginVo);
        },"应该抛出baima异常");
    }
}