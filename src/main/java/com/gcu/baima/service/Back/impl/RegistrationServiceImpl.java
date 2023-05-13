package com.gcu.baima.service.Back.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.Enum.EnrollStatus;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Registration;
import com.gcu.baima.entity.DTO.RegistrationDto;
import com.gcu.baima.entity.VO.RegistrationVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.RegistrationMapper;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Back.RegistrationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {
    @Autowired
    CourseService courseService;
    @Autowired
    CustomerService customerService;

    //检查用户是否能报名课程
    @Override
    public Boolean check(String userId, String courseId) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", userId).eq("perfer_course_id", courseId).eq("enroll_status", 0);
        int count = count(wrapper);
        if (count > 0) {
            throw new BaimaException(201, "你已报名了该课程");
        }
        Boolean full = courseService.isFull(courseId);
        if (full) {
            throw new BaimaException(201, "课程已满，等待下次开课");
        }
        return true;
    }

    @Override
    public void agree(String userId, String courseId, String managerId) {
        Registration registration = new Registration();
        registration.setCustomerId(userId);
        registration.setPerferCourseId(courseId);
        registration.setEnrollStatus(EnrollStatus.SUCCESS.value());
        registration.setManagerId(managerId);
        QueryWrapper<Registration> wrapper = new QueryWrapper<Registration>().
                eq("customer_id", userId).eq("perfer_course_id", courseId)
                .eq("enroll_status", EnrollStatus.CHECKING.value());
        boolean update = update(registration, wrapper);
        //        报名人数+1
        if (!update) {
            throw new BaimaException(201, "用户没有报名课程或已经同意");
        }
        Course course = courseService.getById(courseId);
        course.setCurrentNum(course.getCurrentNum() + 1);
        courseService.updateById(course);
    }

    @Override
    public void addRegistration(RegistrationDto registrationVo) {
        boolean b = customerService.updateById(registrationVo.getCustomer());
        check(registrationVo.getCustomer().getId(), registrationVo.getCourseId());
        Registration registration = new Registration();
        registration.setCustomerId(registrationVo.getCustomer().getId());
        registration.setPerferCourseId(registrationVo.getCourseId());
        //        设置状态为待审核
        registration.setEnrollStatus(EnrollStatus.CHECKING.value());
        boolean save = save(registration);
        if (!b || !save) {
            throw new BaimaException(201, "报名出错");
        }
    }

    @Override
    public void deny(String userId, String courseId, String managerId) {
        Registration registration = new Registration();
        registration.setCustomerId(userId);
        registration.setPerferCourseId(courseId);
        registration.setEnrollStatus(EnrollStatus.FAILED.value());
        registration.setManagerId(managerId);

        QueryWrapper<Registration> wrapper = new QueryWrapper<Registration>().
                eq("customer_id", userId).
                eq("perfer_course_id", courseId);
        boolean update = update(registration, wrapper);
//        boolean remove = remove(wrapper);
        //        报名人数-1
        Course course = courseService.getById(courseId);
        course.setCurrentNum(course.getCurrentNum() - 1);
        courseService.updateById(course);
        if (!update) {
            throw new BaimaException(201, "拒绝失败");
        }
    }

    @Override
    public List<RegistrationVo> pageRegistVo(Page<RegistrationVo> page, String id) {
        return baseMapper.pageRegistVo(id, page);
    }
}
