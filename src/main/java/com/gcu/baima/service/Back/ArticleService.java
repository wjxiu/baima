package com.gcu.baima.service.Back;

import com.gcu.baima.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.baima.entity.VO.ArticleVo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
public interface ArticleService extends IService<Article> {

    ArticleVo getArticleById(String id);

    ArticleVo getGuide();

    void updateGuide(Article article);

    void addGuide(Article article);

    void downloadGuide(HttpServletResponse response);
}
