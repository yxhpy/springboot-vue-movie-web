package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Definition;
import com.yxhpy.web.movie.entity.MType;
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
public interface MTypeMapper extends BaseMapper<MType> {
    @Select("select tid, tname from m_type_to_movie left join m_type mt on m_type_to_movie.m_type = mt.tid where m_type_to_movie.movie=#{mid}")
    public List<MType> getListByMovieId(Integer mid);
}
