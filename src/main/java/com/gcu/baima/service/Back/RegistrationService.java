package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Registration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.DTO.RegistrationDto;
import com.gcu.baima.entity.VO.RegistrationVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface RegistrationService extends IService<Registration> {
    //检查用户是否能报名课程
    Boolean check(String userId, String courseId);

    void agree(String userId, String courseId, String managerId);

    void addRegistration(RegistrationDto registrationVo);

    void deny(String userId, String courseId, String managerId);

    List<RegistrationVo> pageRegistVo(Page<RegistrationVo> page, HashMap<String, Object> map);

    List<RegistrationVo> getUserRegistList(String userId);
}
