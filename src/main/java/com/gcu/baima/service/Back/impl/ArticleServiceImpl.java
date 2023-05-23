package com.gcu.baima.service.Back.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.poi.word.Word07Writer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.ArticleCategory;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.ArticleMapper;
import com.gcu.baima.service.Back.ArticleCategoryService;
import com.gcu.baima.service.Back.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleCategoryService articleCategoryService;
    @Override
    public ArticleVo getArticleById(String id) {
        if (!CheckDBUtil.checkIdEqual(Article.class, id)) throw new BaimaException(201, "id对应数据不存在");
        Article byId = getById(id);
//        更新浏览量
        byId.setView(byId.getView() + 1);
        updateById(byId);
        ArticleVo articleById = baseMapper.getArticleById(id);
        return articleById;
    }

    @Override
    public ArticleVo getGuide() {
        ArticleVo guide = baseMapper.getGuide();
        String id = guide.getId();
        Article byId = getById(id);
        byId.setView(byId.getView() + 1);
        updateById(byId);
        return guide;
    }

    @Override
    public void updateGuide(Article article) {
        ArticleVo guide = getGuide();
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("title", article.getTitle());
        int count = count(articleQueryWrapper);
        if (count > 1) throw new BaimaException(201, "名字重复");
        if (article.getPublicTime() == null) article.setPublicTime(new Date());
        article.setId(guide.getId());
        article.setAcId(getGuideAcId());
        updateById(article);
    }

    @Override
    public void addGuide(Article article) {
        if (CheckDBUtil.checkStringEqual(Article.class, "title", article.getTitle()))
            throw new BaimaException(201, "名字重复");
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        article.setAcId(getGuideAcId());
        Article one = getOne(articleQueryWrapper);
        if (one != null) {
            throw new BaimaException(201, "已存在一篇招生简章");
        }
        String id = UUID.randomUUID().toString(true).substring(0, 19);
        article.setId(id);
        article.setAcId(getGuideAcId());
        if (article.getPublicTime() == null) article.setPublicTime(new Date());
        save(article);
    }

    @Override
    public void downloadGuide(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticleVo guide = getGuide();
        Word07Writer writer = new Word07Writer();
        try {
            writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), guide.getTitle());
            writer.addText(new Font("宋体", Font.PLAIN, 10), guide.getContent());
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + "admissions" + ".docx");
            response.setContentType("application/x-msdownload");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            // 写出到文件
            writer.flush(outputStream);
        } finally {
            writer.close();
        }
    }

    @Override
    public void add(Article article) {
        if (CheckDBUtil.checkStringEqual(Article.class, "title", article.getTitle()))
            throw new BaimaException(201, "名字重复");
        String id = UUID.randomUUID().toString(true).substring(0, 19);
        article.setId(id);
        article.setPublicTime(new Date());
        save(article);
    }

    @Override
    public String getGuideAcId() {
        QueryWrapper<ArticleCategory> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.select("id").eq("name", "招生简章");
        ArticleCategory one = articleCategoryService.getOne(articleQueryWrapper);
        return one.getId();
    }
}
