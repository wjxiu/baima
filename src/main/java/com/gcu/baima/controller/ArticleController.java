package com.gcu.baima.controller;


import com.gcu.baima.entity.Article;
import com.gcu.baima.service.ArticleService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  文章接口
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
    @GetMapping("{id}")
    public R  getById(@PathVariable String id){
        Article byId = articleService.getById(id);
        return R.ok().data("article",byId);
    }
    @PostMapping("")
    public R add(@RequestBody Article article){
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

