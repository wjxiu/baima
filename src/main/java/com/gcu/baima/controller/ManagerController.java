package com.gcu.baima.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.ManagerVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.ManagerService;
import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 工作人员控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "工作人员（管理员或招生专员）控制器")
@RestController
@RequestMapping("/baima/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @ApiOperation("根据工作人员id获取工作人员信息")
    @GetMapping("{id}")
    public R getById(@ApiParam("工作人员id") @PathVariable String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Manager.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        Manager manager = managerService.getById(id);
        ManagerVo managerVo = BeanUtil.copyProperties(manager, ManagerVo.class);
        return R.ok().data("manager", managerVo);
    }

    @ApiOperation("更新信息")
    @PutMapping("")
    public R updateCustom(@ApiParam("修改后的工作人员信息，用工作人员id不能为空") @RequestBody Manager manager) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Manager.class, manager.getId())) throw new BaimaException(201, "id对应的数据不存在");
        managerService.updateById(manager);
        return R.ok();
    }

    @ApiOperation("根据用户id删除用户")
    @DeleteMapping("/{id}")
    public R deleteById(@ApiParam("工作人员") @PathVariable String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Manager.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        managerService.removeById(id);
        return R.ok();
    }

    //  todo  分页查询工作人员
    @ApiOperation("分页查询工作人员")
    @PostMapping("/page/{pageNo}/{limit}")
    public R pageManager(@ApiParam(value = "页码", required = true) @PathVariable Long pageNo,
                         @ApiParam(value = "页大小", required = true) @PathVariable Long limit,
                         @ApiParam(value = "查询参数", required = true) @RequestBody(required = false) HashMap<String, Object> map) {

        Page<ManagerVo> managerPage = managerService.pageManager(pageNo, limit, map);
        return R.ok().data("pageRegistVo", managerPage);
    }
}

