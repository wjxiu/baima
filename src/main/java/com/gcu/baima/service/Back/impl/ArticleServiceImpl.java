package com.gcu.baima.service.Back.impl;

import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.mapper.ArticleMapper;
import com.gcu.baima.service.Back.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.baima.utils.CheckDBUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-04-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ArticleVo getArticleById(String id) {
        if (!CheckDBUtil.checkIdEqual(Article.class, id)) throw new BaimaException(201, "id对应数据不存在");
        return baseMapper.getArticleById(id);
    }
}
