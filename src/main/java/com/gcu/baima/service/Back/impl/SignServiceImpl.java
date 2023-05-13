package com.gcu.baima.service.Back.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Back.ManagerService;
import com.gcu.baima.service.Back.SignService;
import com.gcu.baima.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 后台登录注册接口
 *
 * @author xiu
 * @create 2023-05-11 20:26
 */
@Service
public class SignServiceImpl implements SignService {
    @Autowired
    CustomerService customerService;
    @Autowired
    ManagerService managerService;

    @Override
    public String loginBack(LoginVo loginVo) {
        if (loginVo == null) {
            throw new BaimaException(201, "登录错误");
        }
        if (StringUtils.isEmpty(loginVo.getUsername()) || StringUtils.isEmpty(loginVo.getPassword())) {
            throw new BaimaException(201, "登录错误");
        }
//        管理员
        QueryWrapper<Manager> ManagerQueryWrapper = new QueryWrapper<>();
        ManagerQueryWrapper.eq("username", loginVo.getUsername());
        Manager manager = managerService.getOne(ManagerQueryWrapper);
        if (manager == null) {
            throw new BaimaException(201, "用户名或密码错误");
        }
        boolean checkpw = BCrypt.checkpw(loginVo.getPassword(), manager.getPassword());
        if (!checkpw) {
            throw new BaimaException(201, "用户名或密码错误");
        }
//        创建token,七天有效期
        return JwtHelper.createToken(manager.getId(), manager.getUsername());
    }

    //    注册,密码使用bcrypt加密
    @Override
    public void regiester(Manager manager) {
        QueryWrapper<Manager> ManagerQueryWrapper = new QueryWrapper<>();
        ManagerQueryWrapper.eq("username", manager.getUsername());
        Manager manager1 = managerService.getOne(ManagerQueryWrapper);
        if (manager1 != null) {
            throw new BaimaException(201, "用户名重复");
        }
        String hashpw = BCrypt.hashpw(manager.getPassword());
        manager.setPassword(hashpw);
        managerService.save(manager);
    }
}
