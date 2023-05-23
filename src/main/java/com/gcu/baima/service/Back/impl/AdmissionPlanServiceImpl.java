package com.gcu.baima.service.Back.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.ArticleCategory;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.VO.AdmissionVo;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.AdmissionPlanMapper;
import com.gcu.baima.service.Back.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
    @Autowired
    ArticleCategoryService articleCategoryService;

    /**
     * todo:换为自定义查询语句分页
     * 分页查询招生计划
     * todo：查询条件未知,转为vo
     *
     * @param page    页码
     * @param limit   页大小
     * @param queryVo 查询条件vo 现在没确定
     * @return
     */
    @Transactional
    @Override
    public Page<AdmissionVo> pageAdminssion(Long page, Long limit, AdmissionPlan queryVo) {
        Page<AdmissionPlan> planPage = new Page<>(page, limit);
        QueryWrapper<AdmissionPlan> wrapper = new QueryWrapper<>();
        String courseId = queryVo.getCourseId();
        wrapper.eq(!StringUtils.isEmpty(courseId), "course_id", courseId)
                .eq(!StringUtils.isEmpty(queryVo.getCourseType()), "course_type", queryVo.getCourseType())
                .like(!StringUtils.isEmpty(queryVo.getName()), "name", queryVo.getName());
        IPage<AdmissionPlan> page1 = page(planPage, wrapper);
        List<AdmissionVo> admissionVos = BeanUtil.copyToList(page1.getRecords(), AdmissionVo.class);
        for (AdmissionVo admissionVo : admissionVos) {
            if (CheckDBUtil.checkIdEqual(Article.class, admissionVo.getId())) {
                ArticleVo articleById = articleService.getArticleById(admissionVo.getId());
                admissionVo.setArticle(articleById);
//                更新浏览量
                String id = articleById.getId();
                Article byId = articleService.getById(id);
                byId.setView(byId.getView() + 1);
                articleService.updateById(byId);
                if (!StringUtils.isEmpty(courseId)) {
                    Course course = courseService.getOne(new QueryWrapper<Course>().select("name").eq("id", courseId));
                    admissionVo.setCourseName(course.getName());
                } else {
                    Course course = courseService.getOne(new QueryWrapper<Course>().select("name").eq("id", admissionVo));
                    admissionVo.setCourseName(course.getName());
                }
            }
        }
        Page<AdmissionVo> admissionVoPage = new Page<>();
        BeanUtil.copyProperties(page1, admissionVoPage);
        admissionVoPage.setRecords(admissionVos);
        admissionVoPage.setPages(page1.getPages());
        return admissionVoPage;
    }
    @Override
    public AdmissionVo getAdminssionByCourseType(Integer courseType) {
        //        id不存在
        if (CourseType.valueOf(courseType) == null) throw new BaimaException(201, "课程类型不存在");
        AdmissionVo admissionVo = baseMapper.getByCourseType(courseType);
        return admissionVo;
    }

    //    保存招生计划，同时保存到文章中，二者id相同
    @Transactional
    @Override
    public void saveAdmission(AdmissionPlanSaveDTO dto) {
        AdmissionPlan admissionPlan = new AdmissionPlan();
        BeanUtils.copyProperties(dto, admissionPlan);
        save(admissionPlan);
        Article article = BeanUtil.copyProperties(dto, Article.class);
        article.setId(admissionPlan.getId());
//        暂时写死
        QueryWrapper<ArticleCategory> articleCategoryQueryWrapper = new QueryWrapper<>();
        articleCategoryQueryWrapper.eq("name", "招生计划");
        ArticleCategory one = articleCategoryService.getOne(articleCategoryQueryWrapper);
        article.setAcId(one.getId());
        article.setPublicTime(new Date());
        article.setAuthorId(dto.getAuthorId());
        articleService.save(article);
    }

    @Override
    public AdmissionVo getAdminssionById(String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(AdmissionPlan.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        AdmissionVo byid = baseMapper.getByid(id);
        ArticleVo articleById = articleService.getArticleById(id);
//        存在才查询，左连接查询
        if (CheckDBUtil.checkIdEqual(Article.class, id)) {
            byid.setArticle(articleById);
        }
        return byid;
    }

    @Transactional
    @Override
    public void updateByAdmissionId(AdmissionVo admissionPlanVo) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(AdmissionPlan.class, admissionPlanVo.getId()))
            throw new BaimaException(201, "id对应的数据不存在");
        if (!CheckDBUtil.checkIdEqual(Article.class, admissionPlanVo.getId()))
            throw new BaimaException(201, "id对应的数据不存在");
        AdmissionPlan vo = getById(admissionPlanVo.getId());
        AdmissionPlan admissionPlan1 = new AdmissionPlan();
        BeanUtils.copyProperties(admissionPlanVo, admissionPlan1);
        Article article = BeanUtil.copyProperties(admissionPlanVo.getArticle(), Article.class);
        article.setPublicTime(new Date());
        articleService.updateById(article);
        updateById(admissionPlan1);
    }

    @Override
    public AdmissionVo getByCourseId(String courseId) {
        //id不存在
        if (!CheckDBUtil.checkIdEqual(Course.class, courseId)) throw new BaimaException(201, "id对应的数据不存在");
        QueryWrapper<AdmissionPlan> admissionPlanQueryWrapper = new QueryWrapper<>();
        admissionPlanQueryWrapper.eq("course_id", courseId);
        Course course = courseService.getOne(new QueryWrapper<Course>().select("name").eq("id", courseId));
        AdmissionPlan one = getOne(admissionPlanQueryWrapper);
        if (one == null) throw new BaimaException(201, "课程对应的招生计划未创建");
//        招生表关联的文章
        ArticleVo article = articleService.getArticleById(one.getId());
        AdmissionVo admissionVo = BeanUtil.copyProperties(one, AdmissionVo.class);
        admissionVo.setCourseName(course.getName());
        ArticleVo articleVo = BeanUtil.copyProperties(article, ArticleVo.class);
        admissionVo.setArticle(articleVo);
        return admissionVo;
    }
}
