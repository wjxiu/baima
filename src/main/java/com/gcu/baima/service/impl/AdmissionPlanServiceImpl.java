package com.gcu.baima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Course;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.AdmissionPlanMapper;
import com.gcu.baima.service.AdmissionPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Service
public class AdmissionPlanServiceImpl extends ServiceImpl<AdmissionPlanMapper, AdmissionPlan> implements AdmissionPlanService {
    @Autowired
    CourseService courseService;
    /**
     * 分页查询招生计划
     * todo：查询条件未知
     * @param page 页码
     * @param limit 页大小
     * @param queryVo 查询条件vo 现在没确定
     * @return
     */
    @Override
    public IPage<AdmissionPlan> pageAdminssion(Long page, Long limit, AdmissionPlan queryVo) {
        Page<AdmissionPlan> planPage = new Page<>(page,limit);
        QueryWrapper<AdmissionPlan> wrapper = new QueryWrapper<>();
        IPage<AdmissionPlan> planIPage = page(planPage, wrapper);
//        wrapper.eq()
        return planIPage;
    }

    @Override
    public void saveAdmissionPlan(AdmissionPlan admissionPlan) {
        QueryWrapper<AdmissionPlan> wrapper = new QueryWrapper<>();
        wrapper.eq("name",admissionPlan.getName());
        int count = count(wrapper);
        if (count>0){
            throw new BaimaException(201,"招生计划名字重复");
        }
        save(admissionPlan);
    }

    @Override
    public AdmissionPlan getAdminssionById(String id) {
        AdmissionPlan admiss = getById(id);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("id",admiss.getCourseId());
        if (admiss.getCourseId()!=null){
            Course course = courseService.getById(admiss.getCourseId());
            admiss.setCourse(course);
        }
        return admiss;
    }
}
