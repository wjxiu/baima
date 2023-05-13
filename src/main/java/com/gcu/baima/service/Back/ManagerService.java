package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Manager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface ManagerService extends IService<Manager> {
    //    分页查询工作人员
    Page<Manager> pageManager(Long pageNo, Long limit, HashMap<String, Object> map);

    public Manager getByUserName(String username);
}
