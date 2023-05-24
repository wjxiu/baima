package com.gcu.baima.service.Back.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.poi.word.Word07Writer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.ArticleCategory;
import com.gcu.baima.entity.Customer;
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
import org.springframework.util.StringUtils;


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
        if (!CheckDBUtil.checkIdEqual(Article.class, id)) throw new BaimaException(201, "id对应文章不存在");
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
        if (guide == null) throw new BaimaException(201, "没有招生简章");
        String id = guide.getId();
        Article byId = getById(id);
        byId.setView(byId.getView() + 1);
        updateById(byId);
        return guide;
    }

    @Override
    public void updateGuide(Article article) {
        ArticleVo guide = getGuide();
        if (checkTitle(article.getTitle())) throw new BaimaException(201, "名字重复");
        article.setPublicTime(new Date());
        article.setId(guide.getId());
        article.setAcId(getGuideAcId());
        updateById(article);
    }

    /**
     * 检查标题是否重复
     *
     * @param title 标题
     * @return 重复 返回 true,不重复返回false
     */
    @Override
    public boolean checkTitle(String title) {
        if (StringUtils.isEmpty(title)) return false;
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("title", title);
        int count = count(articleQueryWrapper);
        return count > 1;
    }

    @Override
    public void deleteGuide() {
        ArticleVo guide = baseMapper.getGuide();
        if (guide == null) throw new BaimaException(201, "没有招生简章");
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("ac_Id", getGuideAcId());
//        删除招生简章文章
        remove(articleQueryWrapper);
    }

    @Override
    public void addGuide(Article article) {
        article.setAcId(getGuideAcId());
        ArticleVo guide = baseMapper.getGuide();
        if (guide != null) throw new BaimaException(201, "已存在一篇招生简章");
        if (checkTitle(article.getTitle())) throw new BaimaException(201, "名字重复");
        String id = UUID.randomUUID().toString(true).substring(0, 19);
        article.setId(id);
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
        if (one == null || StringUtils.isEmpty(one.getId())) throw new BaimaException(201, "招生简章目录未创建");
        return one.getId();
    }

    @Override
    public void updateArticle(Article article) {
        if (!CheckDBUtil.checkIdEqual(Article.class, article.getId())) throw new BaimaException(201, "id对应数据不存在");
        if (!CheckDBUtil.checkIdEqual(Customer.class, article.getAuthorId()))
            throw new BaimaException(201, "id对应数据不存在");
        article.setPublicTime(new Date());
        updateById(article);
    }
}
