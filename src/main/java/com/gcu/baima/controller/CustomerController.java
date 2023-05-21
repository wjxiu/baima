package com.gcu.baima.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.CustomerVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.CustomerService;

import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "用户控制器")
@RestController
@RequestMapping("/baima/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        //        id不存在
        if (CheckDBUtil.checkIdEqual(Customer.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        Customer customer = customerService.getById(id);
        CustomerVo customervo = BeanUtil.copyProperties(customer, CustomerVo.class);
        return R.ok().data("customer", customervo);
    }

    @ApiOperation("更新信息")
    @PutMapping("")
    public R updateCustom(@ApiParam("修改后的用户信息，用户id不能为空") @RequestBody Customer customer) {
        //        id不存在
        if (CheckDBUtil.checkIdEqual(Customer.class, customer.getId())) throw new BaimaException(201, "id对应的数据不存在");
        customerService.updateById(customer);
        return R.ok();
    }

    @ApiOperation("根据用户id删除用户")
    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable String id) {
        customerService.removeById(id);
        return R.ok();
    }

    //    todo 分页参数查询条件未知,map换为查询参数类
    @ApiOperation("分页参数查询")
    @PostMapping("/page/{pageNo}/{limit}")
    public R pageCustomer(@PathVariable Long pageNo, @PathVariable Long limit, @RequestBody HashMap<String, Object> map) {
        Page<CustomerVo> customerPageVo = customerService.pageCustomer(pageNo, limit, map);
        return R.ok().data("pageRegistVo", customerPageVo);
    }
}

