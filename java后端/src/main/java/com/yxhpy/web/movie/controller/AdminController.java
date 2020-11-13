package com.yxhpy.web.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxhpy.web.dao.Message;
import com.yxhpy.web.movie.entity.Admin;
import com.yxhpy.web.movie.service.IAdminService;
import com.yxhpy.web.movie.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
@RequestMapping("/movie/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/login")
    public Message login(@RequestBody Admin admin, HttpServletRequest httpServletRequest, HttpSession httpSession){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.setEntity(admin);
        Admin admin1 = adminService.getOne(wrapper);
        if (admin1 == null) {
            Map<String, String> map = new HashMap<>();
            map.put("username", "账号或密码错误");
            map.put("password", "账号或密码错误");
            return Message.error("登陆失败!").add("error", map);
        }
        else {
            httpSession.setAttribute("username", admin1.getUsername());
            return Message.success("登录成功!");
        }
    }
}
