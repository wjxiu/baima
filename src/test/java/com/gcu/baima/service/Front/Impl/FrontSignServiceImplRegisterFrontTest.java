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
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 测试注册方法
 *
 * @author xiu
 * @create 2023-06-04 18:39
 */
@RunWith(MockitoJUnitRunner.class)
class FrontSignServiceImplRegisterFrontTest {

    @InjectMocks
    FrontSignServiceImpl frontSignService;
    @Mock
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        // 开启mock
        MockitoAnnotations.initMocks(this);
    }

    //正确注册
    @Test
    void correctRegiesterFront() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("12345");
        loginVo.setPassword("123456");
        when(customerService.getOne(any())).thenReturn(null);
        assertDoesNotThrow(() -> {
            frontSignService.regiesterFront(loginVo);
        }, "不应该报错");
    }

    //    已经注册
    @Test
    void RegisterAlearyFront() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("12345");
        loginVo.setPassword("123456");
        Customer customer = new Customer();

        when(customerService.getOne(any())).thenReturn(customer);
        assertThrows(BaimaException.class, () -> {
            frontSignService.regiesterFront(loginVo);
        }, "应该提示用户名重复");
    }

    @Test
    void toolongUsernameRegisterFront() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("1211111111111111111111111111345");
        loginVo.setPassword("123456");
        Customer customer = new Customer();

        when(customerService.getOne(any())).thenReturn(customer);
        assertThrows(BaimaException.class, () -> {
            frontSignService.regiesterFront(loginVo);
        }, "用户名过长");
    }

    @Test
    void nullUsernameRegisterFront() {
        LoginVo loginVo = new LoginVo();
        loginVo.setPassword("123456");
        Customer customer = new Customer();
        when(customerService.getOne(any())).thenReturn(customer);
        assertThrows(BaimaException.class, () -> {
            frontSignService.regiesterFront(loginVo);
        }, "应该报错：缺少用户名");
    }


    public boolean isNumber(String s) {
        if (s==null||s.equals("")) return false;
        s = s.trim();
        if (s.equals("")) return false;
        char[] chars = s.toCharArray();
//        s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
        for (char aChar : chars) {
            if (aChar!='e'
                &&aChar!='E'
                &&aChar!='+'
                &&aChar!='-'
                &&aChar!=' '
                &&aChar!='.'
                &&(aChar<48||aChar>57)
            ){
                return false;
            }
        }
        return  isint(chars)||isdouble(chars);
    }
//整数（按顺序）可以分成以下几个部分：
//
//（可选）一个符号字符（'+' 或 '-'）
//至少一位数字
    public boolean isint(char[] chars){
        if (chars[0]!='+'&&chars[0]!='-' &&(chars[0]<48||chars[0]>57)) return false;
        String s = new String(chars);
        if (s.contains("E")) {
            int e1 = s.indexOf("E");
            String e = s.substring(e1);
            for (int i = e1; i < e.length(); i++) {
                if (e.charAt(i)<48||e.charAt(i)>57) return false;
            }
        }
        if (s.contains("e")){
            int e1 = s.indexOf("e");
            String e = s.substring(e1);
            for (int i = e1; i < e.length(); i++) {
                if (e.charAt(i)<48||e.charAt(i)>57) return false;
            }
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i]<48||chars[i]>57) return false;
        }
        return true;
    }
    public boolean isdouble(char[] chars){
        if (chars[0]!='+'&&chars[0]!='-'&&chars[0]!='.' &&(chars[0]<48||chars[0]>57)) return false;
        if(chars[0]=='.'){
            for (int i = 1; i <chars.length ; i++) {
                if (chars[i]=='.') return false;
                if (chars[i]<48||chars[i]>57) return false;
            }
        }
            String s = new String(chars);
            String substring = s.substring(0, s.indexOf("."));
            if (s.substring(s.indexOf(".")).equals(".")) {
                return false;
            }
            boolean leftIsInt = isint(substring.toCharArray());
            return leftIsInt&& isint(s.substring(s.indexOf(".")+1).toCharArray());
    }

    @Test
    void name() {
        System.out.println(isdouble("123123.".toCharArray()));
        System.out.println(isdouble("12312.12323".toCharArray()));
        System.out.println(isdouble("1231ewqew23.wqewee".toCharArray()));
        System.out.println(isdouble("+123123.1.23".toCharArray()));
        System.out.println(isdouble("-123123.131223.213".toCharArray()));
        System.out.println(isdouble(".".toCharArray()));
    }
}