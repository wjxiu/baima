package com.gcu.baima.service.Back.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.DTO.TrialLessonApplyDto;
import com.gcu.baima.entity.TrialLesson;
import com.gcu.baima.entity.TrialLessonCustomer;
import com.gcu.baima.entity.VO.TrialLessonVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.TrialLessonMapper;
import com.gcu.baima.service.Back.CourseService;
import com.gcu.baima.service.Back.CustomerService;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * todo：
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
    @Autowired
    CustomerService customerService;
    @Autowired
    TrialLessonService trialLessonService;
    @Autowired
    CourseService courseService;

    @Override
    public void apply(TrialLessonApplyDto trialLessonApplyDto) {
        String userId = trialLessonApplyDto.getUserId();
        String trialLessonId = trialLessonApplyDto.getCourseId();
        //        判断用户是否试听过
        if (isApply(userId, trialLessonId)) throw new BaimaException(201, "已经申请过");
        Customer customer = BeanUtil.copyProperties(trialLessonApplyDto, Customer.class);
        customer.setId(trialLessonApplyDto.getUserId());
        customerService.updateById(customer);
//        更新试听课程人数，但是最大人数不做限制
        TrialLesson lesson = getById(trialLessonId);
        lesson.setCurrCustomerNum(lesson.getCurrCustomerNum() + 1);
        updateById(lesson);
        TrialLessonCustomer trialLessonCustomer = new TrialLessonCustomer();
        trialLessonCustomer.setCustomerId(userId);
        trialLessonCustomer.setTrailLessionId(trialLessonId);
        trialLessonCustomer.setPeriod(trialLessonApplyDto.getPriod());
        trialLessonCustomerService.save(trialLessonCustomer);
    }

    @Override
    public void withdraw(String customerId, String courseId) {
        Boolean apply = isApply(customerId, courseId);
        if (!apply) throw new BaimaException(201, "用户没有申请过试听课程");
        //        更新试听课程人数，但是最大人数不做限制
        TrialLesson lesson = getById(courseId);
        lesson.setCurrCustomerNum(lesson.getCurrCustomerNum() - 1);
        updateById(lesson);
        QueryWrapper<TrialLessonCustomer> trialLessonQueryWrapper = new QueryWrapper<>();
        trialLessonQueryWrapper.eq("trail_lession_id", courseId).eq("customer_id", courseId);
        trialLessonCustomerService.remove(trialLessonQueryWrapper);
    }

    @Override
    public TrialLessonVo getTrialById(String id) {
        if (!CheckDBUtil.checkIdEqual(TrialLesson.class, id)) throw new BaimaException(201, "id对应数据不存在");
        return baseMapper.getTrialById(id);
    }

    @Override
    public void deleteTrialLessonById(String id) {
        if (!CheckDBUtil.checkIdEqual(TrialLesson.class, id)) throw new BaimaException(201, "id对应数据不存在");
        baseMapper.deleteTrialLessonById(id);
    }

    @Override
    public void pageTrialLesson(Page<TrialLessonVo> trialLessonPage, HashMap<String, String> map) {
        baseMapper.pageTrialLesson(trialLessonPage, map);
    }

    @Override
    public List<TrialLessonVo> getTrialByUserId(String userId) {
        if (!CheckDBUtil.checkIdEqual(Customer.class, userId)) throw new BaimaException(201, "id对应数据为空");
        List<TrialLessonVo> t = baseMapper.getTrialByUserId(userId);
        return t;
    }

    @Override
    public Boolean isApply(String userId, String courseId) {
        if (!CheckDBUtil.checkIdEqual(Customer.class, userId)) throw new BaimaException(201, "id对应数据为空");
        if (!CheckDBUtil.checkIdEqual(Course.class, courseId)) throw new BaimaException(201, "id对应数据为空");
        //        判断用户是否申请试听课程
        QueryWrapper<TrialLessonCustomer> wrapper = new QueryWrapper<TrialLessonCustomer>().eq("customer_id", userId)
                .eq("trail_lession_id", courseId);
        TrialLessonCustomer one = trialLessonCustomerService.getOne(wrapper);
        return one != null;
    }


}
