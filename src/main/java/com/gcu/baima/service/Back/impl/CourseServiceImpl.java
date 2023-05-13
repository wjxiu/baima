package com.gcu.baima.service.Back.impl;

import com.gcu.baima.entity.Course;
import com.gcu.baima.mapper.CourseMapper;
import com.gcu.baima.service.Back.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.utils.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public Boolean isFull(String courseId) {
        Course course = getById(courseId);
        return course.getCurrentNum() > (course.getMaxClassCount() * course.getMaxClassNum());
    }
}
