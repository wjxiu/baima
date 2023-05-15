package com.gcu.baima.service.Front.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.VO.FrontCourseVo;
import com.gcu.baima.mapper.CourseMapper;
import com.gcu.baima.service.Front.FrontCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiu
 * @create 2023-05-15 18:47
 */
@Service
public class FrontCourseServiceImpl implements FrontCourseService {
    @Resource
    CourseMapper courseMapper;


    @Override
    public HashMap<Integer, List<FrontCourseVo>> listFrontCourse() {
        List<Course> list = courseMapper.listFront();
        List<FrontCourseVo> frontCourseVos = BeanUtil.copyToList(list, FrontCourseVo.class);
        HashMap<Integer, List<FrontCourseVo>> map = new HashMap<>();
        for (FrontCourseVo frontCourseVo : frontCourseVos) {
            Integer type = frontCourseVo.getType();
            List<FrontCourseVo> vos = map.getOrDefault(type, new ArrayList<FrontCourseVo>());
            vos.add(frontCourseVo);
            map.put(type, vos);
        }
        return map;
    }
}
