package com.gcu.baima.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface TrialLessonCommentMapper extends BaseMapper<TrialLessonComment> {

    Page<TrialLessonCommentVo> queryPage(Page<TrialLessonCommentVo> voPage, TrialLesson map);

    List<TrialLessonCommentVo> getAllByCourseId(String courseId);
}
