package com.yxhpy.web.movie.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxhpy.web.movie.entity.Actor;
import com.yxhpy.web.movie.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxhpy.web.movie.vo.MovieBarVo;
import com.yxhpy.web.movie.vo.MovieCountVo;
import com.yxhpy.web.movie.vo.MovieLocalVo;
import com.yxhpy.web.movie.vo.MovieVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
public interface IMovieService extends IService<Movie> {
    public IPage<MovieVo> selectPage(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    public List<MovieLocalVo> getLocalCount();
    public List<MovieLocalVo> getLocalCountTop5();
    public MovieBarVo getMovieType();
    public MovieCountVo getCountAll();
    public MovieVo getMovieById(Integer mid);
    public IPage<MovieVo> selectPageVoWrapperTid(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, Integer tid);
}
