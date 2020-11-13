package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.Director;
import com.yxhpy.web.movie.entity.Scriptwriter;
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
public interface ScriptwriterMapper extends BaseMapper<Scriptwriter> {
    @Select("select sid, sname from scriptwriter_to_movie left join scriptwriter s on scriptwriter_to_movie.scriptwriter = s.sid where scriptwriter_to_movie.movie=#{mid}")
    public List<Scriptwriter> getListByMovieId(Integer mid);
}
