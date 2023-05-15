package com.gcu.baima.service.Front;

import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.VO.FrontCourseVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author xiu
 * @create 2023-05-15 18:47
 */
public interface FrontCourseService {

    HashMap<Integer, List<FrontCourseVo>> listFrontCourse();
}
