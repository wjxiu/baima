package com.gcu.baima.service.Back.impl;

import com.gcu.baima.entity.Article;
import com.gcu.baima.entity.VO.ArticleVo;
import com.gcu.baima.mapper.ArticleMapper;
import com.gcu.baima.service.Back.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        return baseMapper.getArticleById(id);
    }
}
