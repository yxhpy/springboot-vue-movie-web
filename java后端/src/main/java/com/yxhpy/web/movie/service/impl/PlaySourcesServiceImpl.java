package com.yxhpy.web.movie.service.impl;

import com.yxhpy.web.movie.entity.PlaySources;
import com.yxhpy.web.movie.mapper.PlaySourcesMapper;
import com.yxhpy.web.movie.service.IPlaySourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxhpy.web.movie.vo.PlaySourceGroupVo;
import com.yxhpy.web.movie.vo.PlaySourceVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
@Service
public class PlaySourcesServiceImpl extends ServiceImpl<PlaySourcesMapper, PlaySources> implements IPlaySourcesService {
    @Autowired
    private PlaySourcesMapper playSourcesMapper;

    public Map<String, List<PlaySourceGroupVo>> getPlaySourcesByMid(Integer mid) {
        HashMap<String, List<PlaySourceGroupVo>> map = new HashMap<>();
        for (PlaySourceVo playSourceVo : playSourcesMapper.getPlaySourcesByMid(mid)) {
            if (map.containsKey(playSourceVo.getSname())) {
                map.get(playSourceVo.getSname()).
                        add(new PlaySourceGroupVo(playSourceVo.getPid(), playSourceVo.getTitle(), playSourceVo.getPlayUrl()));
            } else {
                ArrayList<PlaySourceGroupVo> playSourceGroupVos = new ArrayList<>();
                playSourceGroupVos.add(new PlaySourceGroupVo(playSourceVo.getPid(), playSourceVo.getTitle(), playSourceVo.getPlayUrl()));
                map.put(playSourceVo.getSname(),playSourceGroupVos);
            }
        }
        return map;
    }
}
