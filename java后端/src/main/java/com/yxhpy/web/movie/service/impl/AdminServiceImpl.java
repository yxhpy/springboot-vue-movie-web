package com.yxhpy.web.movie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxhpy.web.movie.entity.Admin;
import com.yxhpy.web.movie.mapper.AdminMapper;
import com.yxhpy.web.movie.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
