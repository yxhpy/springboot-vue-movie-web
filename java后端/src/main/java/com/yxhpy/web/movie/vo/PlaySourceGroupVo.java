package com.yxhpy.web.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaySourceGroupVo {
    private Integer pid;
    private String title;
    private String play_url;
}
