package com.gcu.baima.controller.front;

import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.DTO.TrialLessonApplyDto;
import com.gcu.baima.entity.VO.TrialLessonVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.TrialLessonCustomerService;
import com.gcu.baima.service.Back.TrialLessonService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2023-05-18 14:46
 */
@Api(tags = "前台试课controller")
@RequestMapping("/baima/front/trial")
@RestController
public class FrontTrialController {
    @Autowired
    TrialLessonService trialLessonService;
    @Autowired
    TrialLessonCustomerService trialLessonCustomerService;

    //    申请试听，不做人数限制，但是记录人数
    @ApiOperation("用户申请试课")
    @PostMapping("apply")
    public R apply(@RequestBody TrialLessonApplyDto trialLessonApplyDto) {
//          申请试听
        trialLessonService.apply(trialLessonApplyDto);
        return R.ok();
    }

    //    取消申请
    @ApiOperation("用户取消申请试课")
    @PostMapping("withdraw")
    public R withdraw(String customerId, String courseId) {
        trialLessonService.withdraw(customerId, courseId);
        return R.ok();
    }

    @ApiOperation("根据用户id获取试听信息")
    @GetMapping()
    public R getTrialByUserId(String userId) {
        List<TrialLessonVo> trialVos = trialLessonService.getTrialByUserId(userId);
        return R.ok().data("list", trialVos);
    }

    @ApiOperation("判断用户是否申请试听过课程")
    @GetMapping("isApply")
    public R isApply(String userId, String courseId) {
        Boolean b = trialLessonService.isApply(userId, courseId);
        if (b) return R.ok().data("isApply", 1).message("已申请");
        return R.error().data("isApply", 0).message("未申请");
    }

}
