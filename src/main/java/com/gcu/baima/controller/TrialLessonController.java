package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonCustomer;
import com.gcu.baima.entity.VO.TrialLessonVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.CheckDBUtil;
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
//@Api(tags = "试课控制器")
@RestController
//@RequestMapping("/baima/trialLesson")
public class TrialLessonController {
    @Autowired
    TrialLessonService trialLessonService;
    @Autowired
    TrialLessonCustomerService trialLessonCustomerService;


    //    @ApiOperation("添加一个试课，试课的id和课程id相同")
//    @PostMapping("")
    public R add(@RequestBody TrialLesson trialLesson, String courseId) {
        if (!CheckDBUtil.checkIdEqual(Course.class, courseId)) throw new BaimaException(201, "没有这门课程");
        trialLesson.setId(courseId);
        trialLessonService.save(trialLesson);
        return R.ok();
    }

    @ApiOperation("根据id获取试课信息")
//    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        TrialLessonVo t = trialLessonService.getTrialById(id);
        return R.ok().data("trialLesson", t);
    }


    // 根据id删除试课课程，  同时删除用户评论和用户申请
    @ApiOperation(value = "根据id删除试课课程 ", notes = "会强制删除与之有关的用户评论和用户申请")
//    @DeleteMapping("{id}")
    public R deleteTrialLessonById(@PathVariable String id) {
        trialLessonService.deleteTrialLessonById(id);
        return R.ok();
    }

    // 分页，参数未确定
    @ApiOperation("分页查询试课")
//    @PostMapping("page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit, @RequestBody HashMap<String, String> map) {
        Page<TrialLessonVo> trialLessonPage = new Page<>(pageNo, limit);
        trialLessonService.pageTrialLesson(trialLessonPage, map);
        return R.ok().data("page", trialLessonPage);
    }

    @ApiOperation("修改试课")
//    @PutMapping()
    public R update(@RequestBody TrialLesson trialLesson) {
        trialLessonService.updateById(trialLesson);
        return R.ok();
    }


}

