package com.gcu.baima.service.Back.impl;

import com.gcu.baima.entity.Course;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.CourseMapper;
import com.gcu.baima.service.Back.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    TrialLessonService trialLessonService;

    @Override
    public Boolean isFull(String courseId) {
        if (!CheckDBUtil.checkIdEqual(Course.class, courseId)) throw new BaimaException(201, "id对应的数据不存在");
        Course course = getById(courseId);
        return course.getCurrentNum() > (course.getMaxClassCount() * course.getMaxClassNum());
    }

    @Override
    public Boolean deleteById(String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Course.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        boolean b = removeById(id);
        trialLessonService.deleteTrialLessonById(id);
        return b;
    }
}
