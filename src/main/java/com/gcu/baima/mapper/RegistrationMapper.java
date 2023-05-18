package com.gcu.baima.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Registration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.RegistrationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface RegistrationMapper extends BaseMapper<Registration> {

    List<RegistrationVo> pageRegistVo(Page<RegistrationVo> page);

    List<RegistrationVo> getUserRegistList(String userId);
}
