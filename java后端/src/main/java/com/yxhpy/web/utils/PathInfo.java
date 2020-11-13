package com.yxhpy.web.utils;

import lombok.Data;

@Data
public class PathInfo {
    private String realPath;
    private static final String PATH = System.getProperty("user.dir");
    private static final String SPLIT = System.getProperty("file.separator");


    public static String join(boolean isAddPath, String...path){
        StringBuilder stringBuilder = new StringBuilder();
        if (isAddPath)
            stringBuilder.append(PATH);
        stringBuilder.append(SPLIT);
        for (String s : path) {
            stringBuilder.append(s);
            stringBuilder.append(SPLIT);
        }
        return stringBuilder.toString().substring(0, stringBuilder.length()-1);
    }
    public static String join(String...path){
        return join(true, path);
    }
}
