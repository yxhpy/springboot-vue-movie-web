package com.yxhpy.web.dao;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Message {
    private Map<String, Object> data = new HashMap<>();
    private Integer code;
    private String msg;

    public static Message success(String msg){
        Message message = new Message();
        message.setCode(100);
        message.setMsg(msg);
        return message;
    }

    public static Message error(String msg){
        Message message = new Message();
        message.setCode(200);
        message.setMsg(msg);
        return message;
    }

    public Message add(String key, Object val){
        this.data.put(key, val);
        return this;
    }
}
