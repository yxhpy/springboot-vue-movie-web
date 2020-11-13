package com.yxhpy.web.movie.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxhpy.web.movie.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxhpy.web.movie.vo.MovieBarVo;
import com.yxhpy.web.movie.vo.MovieLocalVo;
import com.yxhpy.web.movie.vo.MovieVo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
public interface MovieMapper extends BaseMapper<Movie> {
    @Select("select * from movie ${ew.customSqlSegment}")
    public IPage<MovieVo> selectPageVo(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    @Select("select * from movie left join m_type_to_movie mttm on movie.mid = mttm.movie where mttm.m_type=#{tid}")
    public IPage<MovieVo> selectPageVoWrapperTid(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, Integer tid);
    @Select("select lname name, count(*) value from local left join local_to_movie ltm on local.lid = ltm.local group by lid")
    public List<MovieLocalVo> getLocalCount();
    @Select("select lname name, count(*) value from local left join local_to_movie ltm on local.lid = ltm.local group by lid order by value desc limit 0, 5")
    public List<MovieLocalVo> getLocalCountTop5();
    @Select("select tname name, count(*) value from m_type left join m_type_to_movie mttm on m_type.tid = mttm.m_type group by tid")
    public List<MovieLocalVo> getMovieType();
    @Select("select * from movie where mid=#{mid}")
    public MovieVo getMovieById(Integer mid);
}
