package com.gcu.baima.service.Back.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonCustomer;
import com.gcu.baima.entity.VO.TrialLessonVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.TrialLessonMapper;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TrialLessonServiceImpl extends ServiceImpl<TrialLessonMapper, TrialLesson> implements TrialLessonService {
    @Autowired
    TrialLessonCustomerService trialLessonCustomerService;

    @Override
    public void apply(String customerId, String trialLessionId) {
        //        判断用户是否试听过
        QueryWrapper<TrialLessonCustomer> wrapper = new QueryWrapper<TrialLessonCustomer>().eq("customer_id", customerId)
                .eq("trail_lession_id", trialLessionId);
        TrialLessonCustomer one = trialLessonCustomerService.getOne(wrapper);
        if (one != null) {
            throw new BaimaException(201, "已经试听过该课程,不能重复试听");
        }
        one = new TrialLessonCustomer();
        one.setCustomerId(customerId);
        one.setTrailLessionId(trialLessionId);
//        更新试听课程人数，但是最大人数不做限制
        TrialLesson lesson = getById(one.getTrailLessionId());
        lesson.setCurrCustomerNum(lesson.getCurrCustomerNum() + 1);
        updateById(lesson);
        trialLessonCustomerService.save(one);
    }

    @Override
    public void withdraw(String customerId, String trialLessionId) {
        //        判断用户是否试听过
        QueryWrapper<TrialLessonCustomer> wrapper = new QueryWrapper<TrialLessonCustomer>().eq("customer_id", customerId)
                .eq("trail_lession_id", trialLessionId);
        TrialLessonCustomer one = trialLessonCustomerService.getOne(wrapper);
        if (one == null) {
            throw new BaimaException(201, "没有申请该课程，撤回失败");
        }
        //        更新试听课程人数，但是最大人数不做限制
        TrialLesson lesson = getById(one.getTrailLessionId());
        lesson.setCurrCustomerNum(lesson.getCurrCustomerNum() - 1);
        updateById(lesson);
        trialLessonCustomerService.removeById(one.getId());
    }

    @Override
    public TrialLessonVo getTrialById(String id) {
        return baseMapper.getTrialById(id);
    }

    @Override
    public void deleteTrialLessonById(String id) {
        baseMapper.deleteTrialLessonById(id);
    }

    @Override
    public void pageTrialLesson(Page<TrialLessonVo> trialLessonPage, HashMap<String, String> map) {
        baseMapper.pageTrialLesson(trialLessonPage, map);
    }
}
