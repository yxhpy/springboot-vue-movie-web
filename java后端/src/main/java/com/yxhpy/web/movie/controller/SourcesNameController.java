package com.yxhpy.web.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxhpy.web.dao.Message;
import com.yxhpy.web.movie.entity.SourcesName;
import com.yxhpy.web.movie.service.impl.SourcesNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/movie/sources-name")
public class SourcesNameController {
    @Autowired
    private SourcesNameServiceImpl sourcesNameService;

    @GetMapping("/add")
    public Message add(String name){
        QueryWrapper<SourcesName> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (sourcesNameService.getOne(queryWrapper)==null){
            SourcesName sourcesName = new SourcesName();
            sourcesName.setName(name);
            sourcesNameService.save(sourcesName);
            return Message.success("增加成功!");
        }else {
            return Message.success("增加失败,播放源商已经被添加,无法重复添加");
        }

    }
    @GetMapping("/get")
    public Message get(){
        return Message.success("获取成功").add("data",sourcesNameService.list());
    }
    @DeleteMapping("/delete")
    public Message delete(Integer snid){
        try {
            if (sourcesNameService.removeById(snid)) {
                return Message.success("删除成功");
            }else {
                return Message.error("删除失败");
            }
        }catch (Exception e){
            return Message.error("删除失败,该源下存在资源无法删除");
        }
    }
}
