package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Actor;
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
public interface ActorMapper extends BaseMapper<Actor> {
    @Select("select aid, aname from actor_to_movie am left join actor a on am.actor = a.aid where am.movie=#{mid}")
    public List<Actor> getListByMovieId(Integer mid);
}
