package com.gcu.baima.service.Back.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.DTO.CourseSaveDTO;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.CourseMapper;
import com.gcu.baima.service.Back.AdmissionPlanService;
import com.gcu.baima.service.Back.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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
    @Autowired
    AdmissionPlanService admissionPlanService;

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

    @Transactional
    @Override
    public void addCourse(CourseSaveDTO coursedto) {

        String authorId = coursedto.getAuthorId();
        if (!CheckDBUtil.checkIdEqual(Manager.class, authorId)) throw new BaimaException(201, "id对应的数据不存在");
        if (CheckDBUtil.checkStringEqual(Course.class, "name", coursedto.getName()))
            throw new BaimaException(201, "名字已经存在");
        //        添加一个对应的试课
        TrialLesson trialLesson = new TrialLesson();
        Course course = BeanUtil.copyProperties(coursedto, Course.class);
        save(course);
        trialLesson.setId(course.getId());
        trialLesson.setCourseId(course.getId());
        trialLesson.setCurrCustomerNum(0);
        trialLesson.setLocation("广州");
        trialLessonService.save(trialLesson);
//        todo 添加对应的招生计划
        AdmissionPlanSaveDTO dto = new AdmissionPlanSaveDTO();
        dto.setCourseId(course.getId());
        dto.setAuthorId(authorId);
        dto.setName(course.getName() + "招生计划");
        dto.setCourseType(course.getType());
        dto.setDuration(3);
        dto.setContent("默认内容");
        dto.setInfo("默认简介");
        dto.setTitle(course.getName() + "招生计划");
        dto.setYear(Calendar.getInstance().get(Calendar.YEAR));
        admissionPlanService.saveAdmission(dto);
    }

    @Override
    public void updateCourse(Course course) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Course.class, course.getId())) throw new BaimaException(201, "id对应的数据不存在");
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("name", course.getName());
        if (count(courseQueryWrapper) > 1) throw new BaimaException(201, "名字重复");
        updateById(course);
    }
}
