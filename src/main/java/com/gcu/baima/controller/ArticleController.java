package com.gcu.baima.controller;


import cn.hutool.core.lang.UUID;
import com.gcu.baima.entity.AdmissionPlan;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.utils.CheckDBUtil;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 文章接口
 * todo:分页参数查询
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Api(tags = "文章控制器")
@RestController
@RequestMapping("/baima/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;


    //    根据id查询文章,返回文章vo类
    @ApiOperation("根据id查询文章,返回文章vo类")
    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        ArticleVo articleVo = articleService.getArticleById(id);
        return R.ok().data("article", articleVo);
    }

    @ApiOperation("添加文章")
    @PostMapping("")
    public R add(@RequestBody Article article) {
        String id = UUID.randomUUID().toString(true).substring(0, 19);
        article.setId(id);
        article.setPublicTime(new Date());
        articleService.save(article);
        return R.ok();
    }

    @ApiOperation("修改文章")
    @PutMapping("")
    public R update(@RequestBody Article article) {
        if (!CheckDBUtil.checkIdEqual(Article.class, article.getId())) throw new BaimaException(201, "id对应数据不存在");
        if (!CheckDBUtil.checkIdEqual(Customer.class, article.getAuthorId()))
            throw new BaimaException(201, "id对应数据不存在");
        article.setPublicTime(new Date());
        articleService.updateById(article);
        return R.ok();
    }

    @ApiOperation("根据id删除文章")
    @DeleteMapping("{id}")
    public R removeByid(@PathVariable String id) {
        //        id不存在
        if (!CheckDBUtil.checkIdEqual(Article.class, id)) throw new BaimaException(201, "id对应的数据不存在");
        articleService.removeById(id);
        return R.ok();
    }
//    todo:分页查询待做
}

