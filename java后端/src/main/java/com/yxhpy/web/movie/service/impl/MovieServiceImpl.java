package com.yxhpy.web.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxhpy.web.movie.entity.Actor;
import com.yxhpy.web.movie.entity.Movie;
import com.yxhpy.web.movie.mapper.*;
import com.yxhpy.web.movie.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxhpy.web.movie.vo.MovieBarVo;
import com.yxhpy.web.movie.vo.MovieCountVo;
import com.yxhpy.web.movie.vo.MovieLocalVo;
import com.yxhpy.web.movie.vo.MovieVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private DirectorMapper directorMapper;
    @Autowired
    private LocalMapper localMapper;
    @Autowired
    private MTypeMapper mTypeMapper;
    @Autowired
    private LanguageMapper languageMapper;
    @Autowired
    private ScriptwriterMapper scriptwriterMapper;

    private void fillMovieVo(MovieVo movieVo){
        Integer mid = movieVo.getMid();
        movieVo.setActors(actorMapper.getListByMovieId(mid));
        movieVo.setDirectors(directorMapper.getListByMovieId(mid));
        movieVo.setLocals(localMapper.getListByMovieId(mid));
        movieVo.setMTypes(mTypeMapper.getListByMovieId(mid));
        movieVo.setLanguages(languageMapper.getListByMovieId(mid));
        movieVo.setScriptwriters(scriptwriterMapper.getListByMovieId(mid));
    }

    @Override
    public IPage<MovieVo> selectPage(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper) {
        IPage<MovieVo> movieVoIPage = movieMapper.selectPageVo(page, wrapper);
        List<MovieVo> records = movieVoIPage.getRecords();
        for (MovieVo record : records) {
            fillMovieVo(record);
        }
        return movieVoIPage;
    }

    @Override
    public List<MovieLocalVo> getLocalCount() {
        return movieMapper.getLocalCount();
    }
    @Override
    public List<MovieLocalVo> getLocalCountTop5() {
        return movieMapper.getLocalCountTop5();
    }

    @Override
    public MovieBarVo getMovieType() {
        List<MovieLocalVo> movieType = movieMapper.getMovieType();
        MovieBarVo movieBarVo = new MovieBarVo();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        for (MovieLocalVo movieLocalVo : movieType) {
            names.add(movieLocalVo.getName());
            values.add(movieLocalVo.getValue());
        }
        movieBarVo.setName(names);
        movieBarVo.setValue(values);
        return movieBarVo;
    }
    @Override
    public MovieCountVo getCountAll() {
        MovieCountVo movieCountVo = new MovieCountVo();
        movieCountVo.setActor(actorMapper.selectCount(null));
        movieCountVo.setScriptwriter(scriptwriterMapper.selectCount(null));
        movieCountVo.setDirector(directorMapper.selectCount(null));
        movieCountVo.setMovie(movieMapper.selectCount(null));
        return movieCountVo;
    }

    @Override
    public MovieVo getMovieById(Integer mid) {
        MovieVo movieVo = movieMapper.getMovieById(mid);
        if (movieVo == null) return null;
        fillMovieVo(movieVo);
        return movieVo;
    }

    @Override
    public IPage<MovieVo> selectPageVoWrapperTid(IPage<MovieVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, Integer tid) {
        if (tid == null)
            return this.selectPage(page, wrapper);
        IPage<MovieVo> movieVoIPage = movieMapper.selectPageVoWrapperTid(page, wrapper, tid);
        List<MovieVo> records = movieVoIPage.getRecords();
        for (MovieVo record : records) {
            fillMovieVo(record);
        }
        return movieVoIPage;
    }

}
