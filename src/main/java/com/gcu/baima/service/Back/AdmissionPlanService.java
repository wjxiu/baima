package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.AdmissionPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.VO.AdmissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
public interface AdmissionPlanService extends IService<AdmissionPlan> {
    /**
     * 分页查询招生计划
     *
     * @param page    页码
     * @param limit   页大小
     * @param queryVo 查询条件vo 现在没确定
     * @return
     */
    Page<AdmissionVo> pageAdminssion(Long page, Long limit, AdmissionPlan queryVo);

    AdmissionVo getAdminssionByCourseType(Integer courseType);

    void saveAdmission(AdmissionPlanSaveDTO vo);

    AdmissionVo getAdminssionById(String id);

    void updateByAdmissionId(AdmissionVo admissionPlanVo);

    AdmissionVo getByCourseId(String courseId);

    String getAdmissionPlanACId();
}
