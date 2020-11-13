package com.yxhpy.web.movie.service;

import com.yxhpy.web.movie.entity.PlaySources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxhpy.web.movie.vo.PlaySourceGroupVo;
import com.yxhpy.web.movie.vo.PlaySourceVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
public interface IPlaySourcesService extends IService<PlaySources> {
    public Map<String, List<PlaySourceGroupVo>> getPlaySourcesByMid(Integer mid);
}
