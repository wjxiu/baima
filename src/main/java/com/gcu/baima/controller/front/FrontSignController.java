package com.gcu.baima.controller.front;

import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.LoginVo;
import com.gcu.baima.service.Front.FrontSignService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前台登录注册controller
 *
 * @author xiu
 * @create 2023-05-11 21:33
 */
@Api(tags = "前台登录注册controller")
@RequestMapping("/baima/front/sign")
@RestController()
public class FrontSignController {
    @Resource
    FrontSignService frontSignService;

    /**
     * 前台登录
     *
     * @param loginVo 登录用户vo类
     * @return
     */
    @ApiOperation("前台登录")
    @PostMapping("login")
    public R login(@ApiParam("登录用户vo类") @RequestBody LoginVo loginVo) {
        String token = frontSignService.loginFront(loginVo);
        Customer customer = frontSignService.getCustomerByUserName(loginVo.getUsername());
        return R.ok().data("token", token).data("user", customer);
    }

    //    前台注册,密码使用bcrypt加密
    @ApiOperation("前台注册")
    @PostMapping("register")
    public R regiester(@RequestBody LoginVo loginVo) {
        frontSignService.regiesterFront(loginVo);
        return R.ok();
    }
}
