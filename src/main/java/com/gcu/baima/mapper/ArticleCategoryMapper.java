package com.gcu.baima.mapper;

import com.gcu.baima.entity.ArticleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {
//具体课程试听人数

    public HashMap<String, String> getTrialCount();
}
