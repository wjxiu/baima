package com.gcu.baima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.baima.entity.AdmissionPlan;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param page 页码
     * @param limit 页大小
     * @param queryVo 查询条件vo 现在没确定
     * @return
     */
    IPage<AdmissionPlan> pageAdminssion(Long page, Long limit, AdmissionPlan queryVo);

    void saveAdmissionPlan(AdmissionPlan admissionPlan);

    AdmissionPlan getAdminssionById(String id);
}
