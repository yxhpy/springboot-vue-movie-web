package com.yxhpy.web.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxhpy.web.dao.Message;
import com.yxhpy.web.movie.entity.Movie;
import com.yxhpy.web.movie.service.IMovieService;
import com.yxhpy.web.movie.service.impl.MovieServiceImpl;
import com.yxhpy.web.movie.vo.MovieBarVo;
import com.yxhpy.web.movie.vo.MovieCountVo;
import com.yxhpy.web.movie.vo.MovieLocalVo;
import com.yxhpy.web.movie.vo.MovieVo;
import com.yxhpy.web.utils.PathInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/movie/movie")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @GetMapping("/get")
    public Message get(Integer page, Integer size, Integer tid, String search){
        page = page == null?1:page;
        size = size == null?10:size;
        search = search == null ? "" : search;
        LambdaQueryWrapper<MovieVo> like = Wrappers.lambdaQuery(MovieVo.class).like(Movie::getTitle, search);
        IPage<MovieVo> objectPage = new Page<>(page, size);
        return Message.success("获取成功！").add("pages", movieServiceImpl.selectPageVoWrapperTid(objectPage, like, tid));
    }
    @PostMapping("/img/post")
    public Message get(@RequestParam(value = "file")MultipartFile multipartFile,
                       @RequestParam(value = "id") Integer id){
        String saveFilename = System.currentTimeMillis() + multipartFile.getOriginalFilename();
        String savePath = PathInfo.join("target", "classes", "static", "movie", "img");
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File dest = new File(PathInfo.join(false, savePath, saveFilename));
        String visitUrl = "/movie/img/" + saveFilename;
        Movie movie = new Movie();
        movie.setMid(id);
        movie.setImg(visitUrl);
        try {
            multipartFile.transferTo(dest);
            if (movieServiceImpl.updateById(movie)) {
                return Message.success("上传成功!").add("imgUrl", visitUrl);
            }
        } catch (IOException e) {
            return Message.success(e.getMessage());
        }
        return Message.success("无效上传!");
    }

    @PostMapping("/add")
    public Message add(@RequestBody  Movie movie){
        if (movieServiceImpl.save(movie)) {
            return Message.success("增加成功!");
        } else {
            return Message.error("增加失败!");
        }
    }
    @PostMapping("/update")
    public Message update(@RequestBody  Movie movie){
        if (movieServiceImpl.updateById(movie)) {
            return Message.success("更新成功!");
        } else {
            return Message.error("更新失败!");
        }
    }
    @DeleteMapping("/delete")
    public Message delete(@RequestParam("mid")Integer mid){
        if (movieServiceImpl.removeById(mid)) {
            return Message.success("删除成功!");
        } else {
            return Message.error("删除失败!");
        }
    }

    @GetMapping("/get/local/count/{top5}")
    public Message getLocalCount(@PathVariable(value = "top5") String top5){
        List<MovieLocalVo> localCount = null;
        if ("top5".equals(top5))
            localCount = movieServiceImpl.getLocalCountTop5();
        else
            localCount = movieServiceImpl.getLocalCount();
        ArrayList<String> strings = new ArrayList<>();
        for (MovieLocalVo movieLocalVo : localCount) {
            strings.add(movieLocalVo.getName());
        }
        if (localCount != null) {
            return Message.success("获取成功!").add("data", localCount).add( "label", strings);
        } else {
            return Message.error("获取失败!");
        }
    }
    @GetMapping("/get/type")
    public Message getType(){
        MovieBarVo movieType = movieServiceImpl.getMovieType();
        if (movieType != null){
            return Message.success("获取成功!").add("data", movieType);
        }
        else {
            return Message.success("获取失败!");
        }
    }
    @GetMapping("/get/count/all")
    public Message getCountAll(){
        MovieCountVo countAll = movieServiceImpl.getCountAll();
        if (countAll != null){
            return Message.success("获取成功!").add("data", countAll);
        }
        else {
            return Message.success("获取失败!");
        }
    }

    @GetMapping("/get/{mid}")
    public Message getById(@PathVariable("mid") Integer mid){
        MovieVo movieVo = movieServiceImpl.getMovieById(mid);
        if (movieVo == null)
            return Message.success("获取失败！");
        else {
            return Message.success("获取成功！").add("movie", movieVo);
        }
    }

}
