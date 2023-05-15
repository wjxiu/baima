package com.gcu.baima.service.Back.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLessonComment;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;
import com.gcu.baima.mapper.TrialLessonCommentMapper;
import com.gcu.baima.service.Back.TrialLessonCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class TrialLessonCommentServiceImpl extends ServiceImpl<TrialLessonCommentMapper, TrialLessonComment> implements TrialLessonCommentService {

    @Override
    public IPage<TrialLessonCommentVo> pageComment(Long pageNo, Long limit, HashMap<String, String> map) {
        Page<TrialLessonCommentVo> voPage = new Page<>(pageNo, limit);
        baseMapper.queryPage(voPage, map);
        return voPage;
    }

    @Override
    public Boolean isRate(String trialLessonId, String customerId) {
        QueryWrapper<TrialLessonComment> wrapper = new QueryWrapper<>();
        wrapper.eq("trial_id", trialLessonId)
                .eq("customer_id", customerId);
        int count = count(wrapper);
        return count > 0;
    }
}
