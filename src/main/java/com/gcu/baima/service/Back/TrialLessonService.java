package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLesson;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.VO.TrialLessonVo;

import java.util.HashMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface TrialLessonService extends IService<TrialLesson> {

    void apply(String customerId, String trialLessionId);

    void withdraw(String customerId, String trialLessionId);

    TrialLessonVo getTrialById(String id);

    void deleteTrialLessonById(String id);

    void pageTrialLesson(Page<TrialLessonVo> trialLessonPage, HashMap<String, String> map);
}
