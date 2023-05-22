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
        return baseMapper.getArticleById(id);
    }

    @Override
    public ArticleVo getGuide() {
        return baseMapper.getGuide();
    }

    @Override
    public void updateGuide(Article article) {
        if (!CheckDBUtil.checkIdEqual(Article.class, article.getId())) throw new BaimaException(201, "id对应数据不存在");
        if (CheckDBUtil.checkStringEqual(Article.class, "title", article.getTitle()))
            throw new BaimaException(201, "名字重复");
        if (article.getPublicTime() == null) article.setPublicTime(new Date());
        article.setAcId(getGuideAcId());
        updateById(article);
    }

    @Override
    public void addGuide(Article article) {

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

    public String getGuideAcId() {
        QueryWrapper<ArticleCategory> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.select("id").eq("name", "招生计划");
        ArticleCategory one = articleCategoryService.getOne(articleQueryWrapper);
        return one.getId();
    }
}
