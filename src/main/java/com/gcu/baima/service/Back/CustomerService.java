package com.gcu.baima.service.Back;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.VO.CustomerVo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface CustomerService extends IService<Customer> {

    Page<CustomerVo> pageCustomer(Long pageNo, Long limit, HashMap<String, Object> map);
}
