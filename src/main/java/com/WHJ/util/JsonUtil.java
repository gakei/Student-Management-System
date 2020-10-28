package com.WHJ.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JsonUtil {
    public static void packAsJson(HttpServletResponse resp, Map<String, Integer> map) throws IOException {
        String jsonString = JSON.toJSONString(map);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        /*返回数据*/
        resp.getWriter().write(jsonString);
    }
}
