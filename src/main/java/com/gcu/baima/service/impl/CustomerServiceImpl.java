package com.gcu.baima.service.impl;

import com.gcu.baima.entity.Customer;
import com.gcu.baima.mapper.CustomerMapper;
import com.gcu.baima.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
