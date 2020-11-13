package com.yxhpy.web.movie.controller;


import com.yxhpy.web.dao.Message;
import com.yxhpy.web.movie.entity.MType;
import com.yxhpy.web.movie.service.impl.MTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/movie/m-type")
public class MTypeController {
    @Autowired
    private MTypeServiceImpl mTypeService;
    @GetMapping("/get/all")
    public Message getAll(){
        List<MType> list = mTypeService.list();
        if (list != null)
            return Message.success("获取成功!").add("data", list);
        else
            return Message.error("获取失败!");
    }
}
