package com.gcu.baima.service.Back.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.mapper.ManagerMapper;
import com.gcu.baima.service.Back.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    //    分页查询工作人员
//todo 分页查询参数未知
    @Override
    public Page<Manager> pageManager(Long pageNo, Long limit, HashMap<String, Object> map) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        Page<Manager> objectPage = new Page<>(pageNo, limit);
        page(objectPage, managerQueryWrapper);
        return objectPage;
    }

    @Override
    public Manager getByUserName(String username) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        managerQueryWrapper.eq("username", username);
        Manager one = getOne(managerQueryWrapper);
        return one;
    }
}
