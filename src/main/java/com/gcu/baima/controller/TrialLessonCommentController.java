package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLessonComment;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;
import com.gcu.baima.service.Back.TrialLessonCommentService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
        commentService.removeById(id);
        return R.ok();
    }

    @PostMapping("")
    public R add(@RequestBody TrialLessonComment Comment) {
        commentService.save(Comment);
        return R.ok();
    }

    //    todo 分页参数查询，参数未知
    @PostMapping("/page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit,
                  @ApiParam() @RequestBody(required = false) HashMap<String, String> map) {
        IPage<TrialLessonCommentVo> page = commentService.pageComment(pageNo, limit, map);
        return R.ok().data("page", page);
    }
}

