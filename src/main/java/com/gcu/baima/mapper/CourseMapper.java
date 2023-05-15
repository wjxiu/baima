package com.gcu.baima.mapper;

import com.gcu.baima.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.FrontCourseVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> listFront();
}
