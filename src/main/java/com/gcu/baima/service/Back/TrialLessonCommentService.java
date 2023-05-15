package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.baima.entity.TrialLessonComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface TrialLessonCommentService extends IService<TrialLessonComment> {

    IPage<TrialLessonCommentVo> pageComment(Long pageNo, Long limit, HashMap<String, String> map);
}
