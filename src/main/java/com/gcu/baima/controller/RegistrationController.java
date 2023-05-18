package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.annotation.CurrentUserId;
import com.gcu.baima.entity.Registration;
import com.gcu.baima.entity.DTO.RegistrationDto;
import com.gcu.baima.entity.VO.RegistrationVo;
import com.gcu.baima.service.Back.RegistrationService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 报名控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "报名控制器")
@RestController
@RequestMapping("/baima/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    //    提交报名课程,同时更新用户信息,课程的报名人数也加1
    @ApiOperation("提交报名课程,同时更新用户信息")
    @PostMapping("enroll")
    public R enroll(@ApiParam("报名信息，用户信息和课程id") @RequestBody RegistrationDto registrationVo) {
        registrationService.addRegistration(registrationVo);
        return R.ok();
    }

    //判断用户能否报名某门课程
    @ApiOperation("判断用户能否报名某门课程")
    @GetMapping("check")
    public R isRegistration(String userId, String courseId) {
        registrationService.check(userId, courseId);
        return R.ok().data("check", 1);
    }

    @ApiOperation(value = "同意用户的报名", notes = "测试传managerId,上线时不用传，从token中获取")
    @GetMapping("agree")
    public R agree(String userId, String courseId, String managerId) {
        registrationService.agree(userId, courseId, managerId);
        return R.ok();
    }

    @ApiOperation(value = "拒绝用户的报名", notes = "没有删除报名信息")
    @DeleteMapping("deny")
    public R deny(String userId, String courseId, String managerId) {
        registrationService.deny(userId, courseId, managerId);
        return R.ok();
    }


    @ApiOperation(value = "分页参数", notes = "条件未确定未完成，需要返回vo,不是返回id")
    @PostMapping("page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit,
                  @ApiParam(required = false) @RequestBody(required = false) HashMap<String, String> map
    ) {
        Page<RegistrationVo> registrationPage = new Page<>(pageNo, limit);
        List<RegistrationVo> l = registrationService.pageRegistVo(registrationPage);
        registrationPage.setRecords(l);
        return R.ok().data("pageRegistVo", registrationPage);
    }

}

