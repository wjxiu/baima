package com.gcu.baima.controller;


import cn.hutool.core.lang.UUID;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.service.Back.ArticleService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
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
@Api("文章")
@RestController
@RequestMapping("/baima/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    //    根据id查询文章,返回文章vo类
    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        ArticleVo articleVo = articleService.getArticleById(id);
        return R.ok().data("article", articleVo);
    }

    @PostMapping("")
    public R add(@RequestBody Article article) {
        String id = UUID.randomUUID().toString(true).substring(0, 19);
        article.setId(id);
        article.setPublicTime(new Date());
        articleService.save(article);
        return R.ok();
    }

    @PutMapping("")
    public R update(@RequestBody Article article){
        articleService.updateById(article);
        return R.ok();
    }
    @DeleteMapping("{id}")
    public R removeByid(@PathVariable String id){
        articleService.removeById(id);
        return R.ok();
    }
}

