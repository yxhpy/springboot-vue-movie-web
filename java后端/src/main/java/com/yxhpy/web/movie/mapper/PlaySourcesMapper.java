package com.yxhpy.web.movie.mapper;

import com.yxhpy.web.movie.entity.PlaySources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxhpy.web.movie.vo.PlaySourceVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
public interface PlaySourcesMapper extends BaseMapper<PlaySources> {
    @Select("select pid, sn.name sname, title, play_url from play_sources left join sources_name sn on play_sources.sname = sn.snid where play_sources.movie=#{mid}")
    public List<PlaySourceVo> getPlaySourcesByMid(Integer mid);
}
