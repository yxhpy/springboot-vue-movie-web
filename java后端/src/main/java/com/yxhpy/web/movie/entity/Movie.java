package com.yxhpy.web.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    private String img;

    private Float viewNum;

    private String title;

    private Integer definition;

    private String releaseMovie;

    private String duration;

    public String getImg() {
        if (img == null){
            return "/movie/img/1604672893726QQ图片20201010122906.jpg";
        }
        return img;
    }

    public Float getViewNum() {
        if (viewNum == null){
            return new Float(0.0);
        }
        return viewNum;
    }
}
