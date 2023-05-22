package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonComment;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.TrialLessonCommentService;
import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 试课评论控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "试课评论控制器")
@RestController
@RequestMapping("/baima/trial-lesson-comment")
public class TrialLessonCommentController {
    @Autowired
    TrialLessonCommentService commentService;

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id) {
        if (!CheckDBUtil.checkIdEqual(TrialLessonComment.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        commentService.removeById(id);
        return R.ok();
    }

    @PostMapping("")
    public R add(@RequestBody TrialLessonComment Comment) {
        commentService.addCommont(Comment);
        return R.ok();
    }

    //    todo 分页参数查询，根据课程id查
    @PostMapping("/page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit,
                  @ApiParam() @RequestBody(required = false) TrialLesson trialLesson) {
        IPage<TrialLessonCommentVo> page = commentService.pageComment(pageNo, limit, trialLesson);
        return R.ok().data("page", page);
    }

    //    判断是否已评价该门课程, 试听课程ID、用户ID
    @ApiOperation("判断是否已评价该门课程")
    @GetMapping("isRate")
    public R isRate(String courseId, String customerId) {
        Boolean rate = commentService.isRate(courseId, customerId);
        if (rate) return R.ok().message("已评价").data("isRate", 1);
        return R.ok().message("未评价").data("isRate", 0);
    }

    @ApiOperation("根据课程id获取课程的所有评价")
    @GetMapping("getAllByCourseId")
    public R getAllByCourseId(String courseId) {
        List<TrialLessonCommentVo> lessonCommentVos = commentService.getAllByCourseId(courseId);
        return R.ok().data("list", lessonCommentVos);
    }
}

