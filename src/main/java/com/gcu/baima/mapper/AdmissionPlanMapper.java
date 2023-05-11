package com.gcu.baima.mapper;

import com.gcu.baima.entity.AdmissionPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.AdmissionVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
public interface AdmissionPlanMapper extends BaseMapper<AdmissionPlan> {
    public AdmissionVo getByid(String id);

    public AdmissionVo getByCourseType(Integer courseType);
}
