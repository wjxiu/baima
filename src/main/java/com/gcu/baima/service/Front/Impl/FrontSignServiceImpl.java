package com.gcu.baima.service.Front.Impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Front.FrontSignService;
import com.gcu.baima.utils.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiu
 * @create 2023-05-12 9:40
 */
@Service
public class FrontSignServiceImpl implements FrontSignService {
    @Autowired
    CustomerService customerService;

    //前台登录
    @Override
    public String loginFront(LoginVo loginVo) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginVo.getUsername());
        Customer one = customerService.getOne(wrapper);
        if (one == null) {
            throw new BaimaException(201, "登录失败");
        }
        if (!BCrypt.checkpw(loginVo.getPassword(), one.getPassword())) {
            throw new BaimaException(201, "登录失败");
        }
        return JwtHelper.createToken(one.getId(), one.getName());
    }

    //    前台用户注册
    @Override
    public void regiesterFront(LoginVo loginVo) {
        Customer customer = new Customer();
        customer.setName(loginVo.getUsername());
        customer.setPassword(loginVo.getPassword());
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginVo.getUsername());
        Customer one = customerService.getOne(wrapper);
//        判断用户名是否已经被使用
        if (one != null) {
            throw new BaimaException(201, "用户名重复");
        }
        customer.setPassword(BCrypt.hashpw(customer.getPassword()));
        customerService.save(customer);
    }

    @Override
    public Customer getCustomerByUserName(String UserName) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", UserName);
        Customer one = customerService.getOne(wrapper);
        one.setPassword("");
        return one;
    }
}
