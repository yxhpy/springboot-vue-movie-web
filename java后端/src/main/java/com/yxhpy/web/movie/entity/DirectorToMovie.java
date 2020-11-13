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
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DirectorToMovie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dmid", type = IdType.AUTO)
    private Integer dmid;

    private Integer director;

    private Integer movie;


}
