package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Definition;
import com.yxhpy.web.movie.entity.Director;
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
public interface DirectorMapper extends BaseMapper<Director> {
    @Select("select did, dname from director_to_movie left join director d on director_to_movie.director = d.did where director_to_movie.movie=#{mid}")
    public List<Director> getListByMovieId(Integer mid);
}
