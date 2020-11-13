package com.yxhpy.web.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable {
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;
    private String username;
    private String password;
}
