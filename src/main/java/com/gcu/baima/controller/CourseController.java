package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.DTO.CourseSaveDTO;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.entity.VO.CourseVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.CheckDBUtil;
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

    @Autowired
    TrialLessonService trialLessonService;

    @ApiOperation("添加课程")
    @PostMapping("")
    public R addCourse(@ApiParam("课程实体类") @RequestBody CourseSaveDTO course) {
        courseService.addCourse(course);
        return R.ok();
    }

    @ApiOperation("根据课程id修改课程")
    @PutMapping("")
    public R updateCourse(@ApiParam("课程实体类") @RequestBody Course course) {


        courseService.updateCourse(course);
        return R.ok();
    }
    @ApiOperation(value = "根据课程id删除课程", notes = "同时删除试听课程及其相关的评论和报名")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam("课程id") @PathVariable String id) {
        if (!CheckDBUtil.checkIdEqual(Course.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        Boolean b = courseService.deleteById(id);
        if (b) return R.ok();
        return R.error();
    }

    @ApiOperation("根据课程id获取课程")
    @GetMapping("{id}")
    public R getById(@ApiParam("课程id") @PathVariable String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Course.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        Course course = courseService.getById(id);
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


}

