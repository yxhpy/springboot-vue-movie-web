package com.yxhpy.web.movie.vo;

import com.yxhpy.web.movie.entity.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
public class MovieVo extends Movie {
    private List<Actor> actors;
    private List<Local> locals;
    private List<Language> languages;
    private List<Scriptwriter> scriptwriters;
    private List<Director> directors;
    private List<MType> mTypes;
}
