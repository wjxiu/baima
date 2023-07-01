package com.gcu.baima.service.Front.Impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Back.ManagerService;
import com.gcu.baima.service.Front.FrontSignService;
import com.gcu.baima.utils.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(loginVo.getPassword()) ||
                StringUtils.isEmpty(loginVo.getUsername())) {
            throw new BaimaException(201, "用户名或密码为空");
        }
        if (loginVo.getUsername().length()>30){
            throw new BaimaException(201, "用户名过长");
        }
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginVo.getUsername());
        Customer one = customerService.getOne(wrapper);
        if (one == null) {
            throw new BaimaException(201, "没有这个用户名");
        }
        String s = SecureUtil.md5().digestHex(loginVo.getPassword());
        if (!s.equalsIgnoreCase(one.getPassword())) {
            throw new BaimaException(201, "登录失败");
        }
        return JwtHelper.createToken(one.getId(), one.getName());
    }

    //    前台用户注册
    @Override
    public void regiesterFront(LoginVo loginVo) {
        if (StringUtils.isEmpty(loginVo.getPassword()) ||
                StringUtils.isEmpty(loginVo.getUsername())) {
            throw new BaimaException(201, "用户名或密码为空");
        }
        if (loginVo.getUsername().length()>30){
            throw new BaimaException(201, "用户名过长");
        }
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
        String digestHex = SecureUtil.md5().digestHex(customer.getPassword());
        customer.setPassword(digestHex);
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
