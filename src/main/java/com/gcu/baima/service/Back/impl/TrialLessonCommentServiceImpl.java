package com.gcu.baima.service.Back.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.*;
import com.gcu.baima.entity.VO.TrialLessonCommentVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.TrialLessonCommentMapper;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Back.TrialLessonCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CustomerService customerService;
    @Autowired
    CourseService courseService;
    @Autowired
    TrialLessonCustomerService trialLessonCustomerService;

    @Override
    public IPage<TrialLessonCommentVo> pageComment(Long pageNo, Long limit, HashMap<String, String> map) {
        Page<TrialLessonCommentVo> voPage = new Page<>(pageNo, limit);
        baseMapper.queryPage(voPage, map);
        return voPage;
    }

    @Override
    public Boolean isRate(String trialLessonId, String customerId) {
        if (!CheckDBUtil.checkIdEqual(TrialLesson.class, trialLessonId) || !CheckDBUtil.checkIdEqual(Customer.class, customerId))
            throw new BaimaException(201, "id对应数据不存在");
//        判断用户是否已经申请课程
        QueryWrapper<TrialLessonCustomer> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("trail_lession_id", trialLessonId)
                .eq("customer_id", customerId);
        if (trialLessonCustomerService.count(wrapper1) <= 0) {
            throw new BaimaException(201, "用户没有申请该课程");
        }
        QueryWrapper<TrialLessonComment> wrapper = new QueryWrapper<>();
        wrapper.eq("trial_id", trialLessonId)
                .eq("customer_id", customerId);
        int count = count(wrapper);
        return count > 0;
    }

    @Override
    public void addCommont(TrialLessonComment comment) {
        String trialId = comment.getTrialId();
        String customerId = comment.getCustomerId();
        if (isRate(trialId, customerId)) {
            throw new BaimaException(201, "已经评价，请勿重复评价");
        }
        save(comment);
    }
}
