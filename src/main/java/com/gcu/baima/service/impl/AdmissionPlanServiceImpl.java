package com.gcu.baima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.AdmissionVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.AdmissionPlanMapper;
import com.gcu.baima.service.AdmissionPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.service.ArticleService;
import com.gcu.baima.service.CourseService;
import com.gcu.baima.service.ManagerService;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    ArticleService articleService;
    @Autowired
    ManagerService managerService;

    /**
     * 分页查询招生计划
     * todo：查询条件未知,转为vo
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
    public AdmissionVo getAdminssionByCourseType(Integer courseType) {
        AdmissionVo admissionVo = baseMapper.getByCourseType(courseType);
        if (admissionVo==null){
            throw new BaimaException(201,"没有这个招生计划");
        }
        return admissionVo;
    }

//    保存招生计划，同时保存到文章中，二者id相同
    @Override
    public void saveAdmission(AdmissionVo vo) {
        AdmissionPlan admissionPlan = new AdmissionPlan();
        BeanUtils.copyProperties(admissionPlan,vo);
        save(admissionPlan);
//        保存文章
        Article article = new Article();
        article.setId(vo.getId());
        article.setAuthorId(vo.getAuthorId());
        article.setTitle(admissionPlan.getName());
        article.setContent(vo.getContext());
        article.setAcId("1");
        articleService.save(article);
    }

    @Override
    public AdmissionVo getAdminssionById(String id) {
        AdmissionVo admissionVo = baseMapper.getByid(id);
        if (admissionVo==null){
            throw new BaimaException(201,"没有这个招生计划");

        }
        return admissionVo;
    }

    @Override
    public void updateByAdmissionId(AdmissionVo admissionPlanVo) {
        AdmissionPlan vo = getById(admissionPlanVo.getId());
        AdmissionPlan admissionPlan1 = new AdmissionPlan();
        BeanUtils.copyProperties(vo,admissionPlan1);
        Article article = new Article();
        BeanUtils.copyProperties(vo,admissionPlan1);
        BeanUtils.copyProperties(vo,article);
        articleService.save(article);
        save(admissionPlan1);
    }
}
