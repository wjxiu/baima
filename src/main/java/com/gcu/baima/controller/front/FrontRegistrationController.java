package com.gcu.baima.controller.front;

import com.gcu.baima.entity.VO.RegistrationVo;
import com.gcu.baima.service.Back.RegistrationService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台报名控制器
 *
 * @author xiu
 * @create 2023-05-17 11:34
 */
@RestController
@RequestMapping("/baima/front/registraion")
public class FrontRegistrationController {
    @Autowired
    RegistrationService registrationService;

    //    查询用户的报名列表
    @ApiOperation(value = "查询用户的报名列表")
    @GetMapping("myRegist/{userId}")
    public R myRegistration(@PathVariable String userId) {
        List<RegistrationVo> l = registrationService.getUserRegistList(userId);
        return R.ok().data("list", l);
    }
}
