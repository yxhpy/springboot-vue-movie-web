package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Director;
import com.yxhpy.web.movie.entity.Language;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
public interface LanguageMapper extends BaseMapper<Language> {
    @Select("select lid, lname from language_to_movie left join language l on language_to_movie.language = l.lid where language_to_movie.movie=#{mid}")
    public List<Language> getListByMovieId(Integer mid);
}
