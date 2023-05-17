package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.entity.VO.CourseVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程控制器，课程关联的
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "课程控制器")
@RestController
@RequestMapping("/baima/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ArticleService articleService;

    @ApiOperation("添加课程")
    @PostMapping("")
    public R addCourse(@ApiParam("课程实体类") @RequestBody(required = false) Course course) {
        courseService.save(course);
        return R.ok();
    }

    @ApiOperation("根据课程id修改课程")
    @PutMapping("")
    public R updateCourse(@ApiParam("课程实体类") @RequestBody Course course) {
        courseService.updateById(course);
        return R.ok();
    }

    @ApiOperation("根据课程id删除课程")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam("课程id") @PathVariable String id) {
        courseService.removeById(id);
        return R.ok();
    }

    @ApiOperation("根据课程id获取课程")
    @GetMapping("{id}")
    public R getById(@ApiParam("课程id") @PathVariable String id) {
        Course course = courseService.getById(id);
        if (course == null) {
            throw new BaimaException(201, "没有这条数据");
        }
        return R.ok().data("course", course);
    }
//todo 分页参数查询未知
@ApiOperation("分页带参数获取课程列表")
@PostMapping("page/{pageNo}/{limit}")
public R pageCourse(@ApiParam("页码") @PathVariable Long pageNo, @ApiParam("页大小") @PathVariable Long limit, @ApiParam("查询参数类") @RequestBody HashMap<String, Object> map) {
    Page<Course> coursePage = new Page(pageNo, limit);
    QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
    courseService.page(coursePage, courseQueryWrapper);
    return R.ok().data("pageRegistVo", coursePage);
}

    //    查询所有课程，为了报名选择课程
    @ApiOperation("查询所有课程，为了用户报名选择课程")
    @PostMapping("all")
    public R getAll() {
        List<Course> list = courseService.list(null);
        List<CourseVo> courseVos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Course course = list.get(i);
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(course, courseVo);
            Integer type = course.getType();
            CourseType courseType = CourseType.valueOf(type);
            courseVo.setCourseType(courseType);
            Integer i1 = course.getMaxClassNum() * course.getMaxClassCount();
            courseVo.setMaxNum(i1);
            courseVo.setCurrentNum(course.getCurrentNum());
            courseVos.add(courseVo);
        }
        return R.ok().data("list", courseVos);
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

