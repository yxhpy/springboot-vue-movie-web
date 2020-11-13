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
public class SourcesName implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "snid", type = IdType.AUTO)
    private Integer snid;

    private String name;


}
