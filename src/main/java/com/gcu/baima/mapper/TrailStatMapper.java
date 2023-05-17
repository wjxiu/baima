package com.gcu.baima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.TrialLessonCustomer;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试课统计情况
 *
 * @author xiu
 * @create 2023-05-17 21:29
 */
@Service
public interface TrailStatMapper {
    //    具体课程试听人数
    @MapKey("id")
    public List<Map<String, Object>> getCourseRegitCount();

    //    申请试听不同类型课程人数占比
    @MapKey("id")
    public List<Map<String, Object>> getCourseRate();

    @MapKey("id")
    List<Map<String, Object>> getApplyRate();

    @MapKey("id")
    List<Map<String, Object>> getAvgRate();

    @MapKey("id")
    List<Map<String, Object>> getCountByTimeSpan();
}
