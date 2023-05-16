package com.gcu.baima.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.VO.FrontCourseVo;
import com.gcu.baima.service.Front.FrontCourseService;
import com.gcu.baima.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //    获取所有课程，根据课程分类展示
    @GetMapping("")
    public R list() {
        HashMap<Integer, List<FrontCourseVo>> map = frontCourseService.listFrontCourse();
        return R.ok().data("list", map);
    }
}
