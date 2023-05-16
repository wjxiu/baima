package com.gcu.baima.service.Back.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.CustomerVo;
import com.gcu.baima.mapper.CustomerMapper;
import com.gcu.baima.service.Back.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public Page<CustomerVo> pageCustomer(Long pageNo, Long limit, HashMap<String, Object> map) {
        Page<Customer> customerPage = new Page<>(pageNo, limit);
        IPage<Customer> page = page(customerPage, null);
        List<Customer> records = page.getRecords();
        List<CustomerVo> customerVos = BeanUtil.copyToList(records, CustomerVo.class);
        Page<CustomerVo> customerPageVo = new Page<>(pageNo, limit);
        customerPageVo.setRecords(customerVos);
        customerPageVo.setTotal(page.getTotal());
        customerPageVo.setCurrent(page.getCurrent());
        return customerPageVo;
    }
}
