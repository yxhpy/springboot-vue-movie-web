package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Director;
import com.yxhpy.web.movie.entity.Local;
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
public interface LocalMapper extends BaseMapper<Local> {
    @Select("select lid, lname from local_to_movie left join local l on local_to_movie.local = l.lid where local_to_movie.movie=#{mid}")
    public List<Local> getListByMovieId(Integer mid);
}
