package com.WHJ.servlet;

import com.WHJ.util.JDBCConnector;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.WHJ.util.JsonUtil.packAsJson;

public class InputRulesRecord extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Integer> responseMap = new HashMap<>();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] name = parameterMap.get("name");
        String[] type = parameterMap.get("type");

        String sql = "select * from student where name = ?";
        try {
            boolean flag = true;
            JDBCConnector jdbcConnector = new JDBCConnector();
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(sql);
            prepareStatement.setString(1, name[0]);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                responseMap.put("responseCode", 2);
                String insertSql = "insert into break_rules_record (type, stu_name) values (?, ?)";
                PreparedStatement prepareStatement1 = jdbcConnector.getPrepareStatement(insertSql);
                prepareStatement1.setInt(1, Integer.parseInt(type[0]));
                prepareStatement1.setString(2, name[0]);
                prepareStatement1.executeUpdate();
                flag = false;
            }

            if (flag) {
                responseMap.put("responseCode", 1);
            }

            packAsJson(resp, responseMap);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
