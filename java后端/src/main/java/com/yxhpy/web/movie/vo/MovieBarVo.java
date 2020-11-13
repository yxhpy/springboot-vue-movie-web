package com.yxhpy.web.movie.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MovieBarVo {
    public List<String> name;
    public List<Integer> value;
}
