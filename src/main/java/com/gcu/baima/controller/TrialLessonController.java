package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonCustomer;
import com.gcu.baima.entity.VO.TrialLessonVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "试课控制器")
@RestController
@RequestMapping("/baima/trialLesson")
public class TrialLessonController {
    @Autowired
    TrialLessonService trialLessonService;
    @Autowired
    TrialLessonCustomerService trialLessonCustomerService;


    @ApiOperation("添加一个试课")
    @PostMapping("")
    public R add(@RequestBody TrialLesson trialLesson) {
        trialLessonService.save(trialLesson);
        return R.ok();
    }

    @ApiOperation("根据id获取试课信息")
    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        TrialLessonVo t = trialLessonService.getTrialById(id);
        return R.ok().data("trialLesson", t);
    }


    // 根据id删除试课课程，  同时删除用户评论和用户申请
    @ApiOperation(value = "根据id删除试课课程 ", notes = "会强制删除与之有关的用户评论和用户申请")
    @DeleteMapping("{id}")
    public R deleteTrialLessonById(@PathVariable String id) {
        trialLessonService.deleteTrialLessonById(id);
        return R.ok();
    }


    //    申请试听，不做人数限制，但是记录人数
    @ApiOperation("用户申请试课")
    @PostMapping("apply")
    public R apply(String customerId, String trialLessionId) {
//        判断参数
        if (StringUtils.isEmpty(customerId) || StringUtils.isEmpty(trialLessionId)) {
            throw new BaimaException(201, "缺少必要参数");
        }
//  申请试听
        trialLessonService.apply(customerId, trialLessionId);
        return R.ok();
    }

    //    取消申请
    @ApiOperation("用户取消申请试课")
    @PostMapping("withdraw")
    public R withdraw(String customerId, String trialLessionId) {
        if (StringUtils.isEmpty(customerId) || StringUtils.isEmpty(trialLessionId)) {
            throw new BaimaException(201, "缺少必要参数");
        }
        trialLessonService.withdraw(customerId, trialLessionId);
        return R.ok();
    }

    // 分页，参数未确定
    @ApiOperation("分页查询课程")
    @PostMapping("page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit, @RequestBody HashMap<String, String> map) {
        Page<TrialLessonVo> trialLessonPage = new Page<>(pageNo, limit);
        trialLessonService.pageTrialLesson(trialLessonPage, map);
        return R.ok().data("page", trialLessonPage);
    }

    @ApiOperation("修改试课课程")
    @PutMapping()
    public R update(@RequestBody TrialLesson trialLesson) {
        trialLessonService.updateById(trialLesson);
        return R.ok();
    }


}

