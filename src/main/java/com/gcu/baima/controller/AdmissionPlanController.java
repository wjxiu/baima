package com.gcu.baima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Course;
import com.gcu.baima.entity.DTO.AdmissionPlanSaveDTO;
import com.gcu.baima.entity.VO.AdmissionVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.AdmissionPlanService;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 招生计划控制器
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Api(tags = "招生计划")
@RestController
@RequestMapping("/baima/admission-plan")
public class AdmissionPlanController {
    @Autowired
    AdmissionPlanService admissionPlanService;
    @Autowired
    ArticleService articleService;

    @ApiOperation("根据id查询招生计划")
    @GetMapping("{id}")
    public R getById(@ApiParam("招生计划id") @PathVariable String id) {
        AdmissionVo byId = admissionPlanService.getAdminssionById(id);
        return R.ok().data("admission", byId);
    }

    @Transactional
    @ApiOperation("根据id删除招生计划")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam("招生计划id") @PathVariable String id) {
//        id不存在
        if (!CheckDBUtil.checkIdEqual(AdmissionPlan.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        admissionPlanService.removeById(id);
        articleService.removeById(id);
        return R.ok();
    }

    @ApiOperation("创建招生计划")
    @PostMapping("")
    public R addAdmission(@ApiParam("招生计划实体类") @RequestBody AdmissionPlanSaveDTO dto) {
        admissionPlanService.saveAdmission(dto);
        return R.ok();
    }

    @ApiOperation(value = "根据id修改招生计划", notes = "招生计划实体类必须有id")
    @PutMapping("")
    public R updateAdmission(@ApiParam(value = "招生计划实体类") @RequestBody AdmissionVo admissionPlanVo) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(AdmissionPlan.class, admissionPlanVo.getId()))
            throw new BaimaException(201, "id对应的数据不存在");
        admissionPlanService.updateByAdmissionId(admissionPlanVo);
        return R.ok();
    }

    @ApiOperation(value = "根据课程id查找招生计划", notes = "课程id不能为空，返回招生计划及其对应文章")
    @GetMapping("/getByCourseId")
    public R getByCourseId(String courseId) {
        AdmissionVo admissionPlan = admissionPlanService.getByCourseId(courseId);
        return R.ok().data("admissionPlan", admissionPlan);
    }

    /**
     * 分页查询招生计划
     *
     * @param pageNo 页码
     * @param limit  页大小
     * @return 分页后的page对象
     */
    @ApiOperation(value = "分页查询招生计划", notes = "查询参数未定")
    @PostMapping("pageAdminssion/{pageNo}/{limit}")
    public R pageAdminssion(@ApiParam(value = "页码", required = true) @PathVariable Long pageNo,
                            @ApiParam(value = "页大小", required = true) @PathVariable Long limit,
                            @ApiParam(value = "查询参数", required = false) @RequestBody(required = false) AdmissionPlan queryVo) {
        Page<AdmissionVo> admissionVoPage = admissionPlanService.pageAdminssion(pageNo, limit, queryVo);
        return R.ok().data("pages", admissionVoPage);
    }
}

