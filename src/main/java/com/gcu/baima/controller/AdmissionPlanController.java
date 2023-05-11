package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.service.AdmissionPlanService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  招生计划控制器
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Api(tags="招生计划")
@RestController
@RequestMapping("/baima/admission-plan")
public class AdmissionPlanController {
    @Autowired
    AdmissionPlanService admissionPlanService;
    @ApiOperation("根据id查询招生计划")
    @GetMapping("{id}")
    public R getById(@ApiParam("招生计划id") @PathVariable String id){
        AdmissionPlan byId = admissionPlanService.getAdminssionById(id);
        return R.ok().data("admission",byId);
    }
    @ApiOperation("根据id删除招生计划")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam("招生计划id") @PathVariable String id){
        admissionPlanService.removeById(id);
        return R.ok();
    }
    @ApiOperation("创建招生计划")
    @PostMapping("")
    public R addAdmission(@ApiParam("招生计划实体类") @RequestBody AdmissionPlan admissionPlan){
        admissionPlanService.saveAdmissionPlan(admissionPlan);
        return R.ok().data("admission",admissionPlan);
    }
    @ApiOperation(value = "根据id修改招生计划",notes = "招生计划实体类必须有id")
    @PutMapping("")
    public R updateAdmission(@ApiParam(value = "招生计划实体类")@RequestBody AdmissionPlan admissionPlan){
        admissionPlanService.update(admissionPlan,null);
        return R.ok();
    }

    /**
     * 分页查询招生计划
     * @param page 页码
     * @param limit 页大小
     * @param queryVo 查询条件vo 现在没确定
     * @return 分页后的page对象
     */
    @ApiOperation(value = "分页查询招生计划",notes = "查询参数未定")
    @PostMapping("pageAdminssion/{page}/{limit}")
    public R pageAdminssion(@ApiParam(value = "页码",required = true) @PathVariable Long page,
                            @ApiParam(value = "页大小",required = true) @PathVariable Long limit,
                            @ApiParam(value = "查询参数",required = false)  @RequestBody(required = false) AdmissionPlan queryVo){
        IPage<AdmissionPlan> admissionPlanIPage = admissionPlanService.pageAdminssion(page, limit, queryVo);
        return R.ok().data("pages",admissionPlanIPage);
    }
}

