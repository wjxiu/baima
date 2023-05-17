package com.gcu.baima.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.entity.VO.FrontCourseVo;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.service.Front.FrontCourseService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiu
 * @create 2023-05-15 18:38
 */
@RequestMapping("/baima/front/coures")
@RestController
public class FrontCourseController {
    @Autowired
    FrontCourseService frontCourseService;
    @Autowired
    CourseService courseService;
    @Autowired
    ArticleService articleService;

    //    获取所有课程，根据课程分类展示
    @GetMapping("")
    public R list() {
        HashMap<Integer, List<FrontCourseVo>> map = frontCourseService.listFrontCourse();
        return R.ok().data("list", map);
    }

    //    判断该课程是否已满人,满人返回1，未满返回0
    @ApiOperation(value = "判断该课程是否已满人", notes = "判断结果为isFull，1满人，0为满人")
    @PostMapping("isFull/{courseId}")
    public R isFull(@PathVariable String courseId) {
        Boolean isFull = courseService.isFull(courseId);
        if (isFull) return R.ok().data("isFull", 1);
        return R.ok().data("isFull", 0);
    }

    //   给课程添加一篇宣传文章， 课程id和文章id一致
    @PostMapping("/addArticleForCourse/{courseId}")
    public R addArticleForCourse(@PathVariable String courseId, @RequestBody Article article) {
        article.setId(courseId);
        articleService.save(article);
        return R.ok();
    }

    // 根据课程查询课程的宣传文章
    @GetMapping("getArticleForCourse/{courseId}")
    public R getArticleForCourse(@PathVariable String courseId) {
        ArticleVo vo = articleService.getArticleById(courseId);
        return R.ok().data("article", vo);
    }
}
