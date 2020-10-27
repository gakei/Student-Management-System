package com.WHJ.servlet;

import com.WHJ.util.JDBCConnector;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeleteStuInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Integer> map = new HashMap<>();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] id = parameterMap.get("id");
        String sql = "DELETE FROM student WHERE id = ?";
        JDBCConnector jdbcConnector = new JDBCConnector();
        try {
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(sql);
            prepareStatement.setInt(1, Integer.parseInt(id[0]));
            prepareStatement.execute();
            //1->删除成功
            map.put("responseCode", 1);
            packAsJson(resp, map);
        } catch (ClassNotFoundException | SQLException e) {
            //2->删除失败
            map.put("responseCode", 2);
            packAsJson(resp, map);
            e.printStackTrace();
        }
    }

    private void packAsJson(HttpServletResponse resp, Map<String, Integer> map) throws IOException {
        String jsonString = JSON.toJSONString(map);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        /*返回数据*/
        resp.getWriter().write(jsonString);
    }
}
