package com.gcu.baima.mapper;

import com.gcu.baima.entity.TrialLesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.TrialLessonVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface TrialLessonMapper extends BaseMapper<TrialLesson> {

    TrialLessonVo getTrialById(String id);

    Integer deleteTrialLessonById(@Param("id") String id);
}
