package com.yxhpy.web.movie.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Handler {
    @RequestMapping("/")
    public String index(){
        return "front.html";
    }
    @RequestMapping("/ssm")
    public String ssm(){
        return "ssm.html";
    }
}
