package com.gcu.baima.service.Back;

import com.gcu.baima.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.DTO.CourseSaveDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface CourseService extends IService<Course> {

    Boolean isFull(String courseId);

    Boolean deleteById(String id);

    void addCourse(CourseSaveDTO course);

    void updateCourse(Course course);
}
