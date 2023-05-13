package com.gcu.baima.controller;

import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.service.Back.ManagerService;
import com.gcu.baima.service.Back.SignService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录注册相关控制器，后台，用户管理员都是这里
 *
 * @author xiu
 * @create 2023-05-11 20:15
 */
@RequestMapping("/sign")
@Api(tags = "后台登录注册控制器")
@RestController()
public class SignController {
    @Autowired
    SignService signService;
    @Autowired
    ManagerService managerService;

    /**
     * 后台登录,禁止用户登录
     *
     * @param loginVo 登录用户vo类
     * @return
     */
    @ApiOperation("后台登录，禁止用户登录")
    @PostMapping("login")
    public R login(@ApiParam("登录用户vo类") @RequestBody LoginVo loginVo) {
        String token = signService.loginBack(loginVo);
        Manager manager = managerService.getByUserName(loginVo.getUsername());

        return R.ok().data("token", token).data("user", manager);
    }

    //    注册,密码使用bcrypt加密
    @ApiOperation("后台创建管理员和推广专员")
    @PostMapping("register")
    public R regiester(@RequestBody Manager manager) {
        signService.regiester(manager);
        return R.ok();
    }
}
