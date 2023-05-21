package com.gcu.baima.controller;

import com.gcu.baima.Enum.CourseType;
import com.gcu.baima.mapper.TrailStatMapper;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiu
 * @create 2023-05-17 22:23
 */
@Api(tags = "统计控制器")
@RequestMapping("/baima/stat")
@RestController
public class StatController {
    @Resource
    TrailStatMapper trailStatMapper;

    @ApiOperation(" 具体课程试听人数")
    @GetMapping("/getTrialCount")
    public Object getTrialCount() {
        List<Map<String, Object>> map = trailStatMapper.getCourseRegitCount();
        Object o = new Object();
        return map;
    }

    //    申请试听不同类型课程人数占比
    @ApiOperation("申请试听不同类型课程人数占比")
    @GetMapping("/getCourseRate")
    public List<Map<String, Object>> getCourseRate() {
        List<Map<String, Object>> courseRate = trailStatMapper.getCourseRate();
        for (int i = 0; i < courseRate.size(); i++) {
            Map<String, Object> stringObjectMap = courseRate.get(i);
            Integer type = (Integer) stringObjectMap.get("type");
            CourseType courseType = CourseType.valueOf(type);
            stringObjectMap.put("type", courseType.toString());
        }
        return courseRate;
    }

    //    申请报名不同类型课程人数占比
    @ApiOperation("申请报名不同类型课程人数占比")
    @GetMapping("/getApplyRate")
    public List<Map<String, Object>> getApplyRate() {
        List<Map<String, Object>> applyRate = trailStatMapper.getApplyRate();
        for (int i = 0; i < applyRate.size(); i++) {
            Map<String, Object> stringObjectMap = applyRate.get(i);
            Integer type = (Integer) stringObjectMap.get("type");
            CourseType courseType = CourseType.valueOf(type);
            stringObjectMap.put("type", courseType.toString());
        }
        return applyRate;
    }

    //    具体课程平均评分
    @ApiOperation("具体课程平均评分")
    @GetMapping("/getAvgRate")
    public List<Map<String, Object>> getCoursAvgRate() {
        return trailStatMapper.getAvgRate();
    }

    //    各个时间段选择情况
    @ApiOperation("各个时间段选择情况")
    @GetMapping("/getCountByTimeSpan")
    public List<Map<String, Object>> getCountByTimeSpan() {
        return trailStatMapper.getCountByTimeSpan();
    }
}
