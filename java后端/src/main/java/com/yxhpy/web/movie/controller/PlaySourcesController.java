package com.yxhpy.web.movie.controller;



import com.yxhpy.web.dao.Message;
import com.yxhpy.web.movie.entity.PlaySources;
import com.yxhpy.web.movie.service.impl.PlaySourcesServiceImpl;
import com.yxhpy.web.movie.vo.PlaySourceGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/movie/play-sources")
public class PlaySourcesController {
    @Autowired
    private PlaySourcesServiceImpl playSourcesService;
    @PostMapping("/add")
    public Message add(@RequestBody PlaySources playSources){
        try {
            if (playSourcesService.save(playSources)){
                return Message.success("添加成功!");
            } else {
                return Message.error("添加失败!");
            }
        } catch (Exception e){
            return Message.error("添加失败, 该网址已经存在!");
        }

    }

    @GetMapping("/get")
    public Message get(Integer mid){
        Map<String, List<PlaySourceGroupVo>> map = playSourcesService.getPlaySourcesByMid(mid);
        return Message.success("获取成功").add("data", map);
    }
    @DeleteMapping("/delete")
    public Message delete(Integer pid){
        if (playSourcesService.removeById(pid)) {
            return Message.success("删除成功");
        }else {
            return Message.error("删除失败");
        }
    }

}
