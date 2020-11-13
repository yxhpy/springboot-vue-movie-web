package com.yxhpy.web.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxhpy
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PlaySources implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    private Integer sname;

    private Integer movie;

    private String title;

    private String playUrl;


}
