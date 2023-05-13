package com.gcu.baima.mapper;

import com.gcu.baima.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcu.baima.entity.VO.ArticleVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
public interface ArticleMapper extends BaseMapper<Article> {

    ArticleVo getArticleById(String id);
}
