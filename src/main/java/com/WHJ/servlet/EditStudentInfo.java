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

public class EditStudentInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] id = parameterMap.get("id");
        String[] name = parameterMap.get("name");
        String[] sex = parameterMap.get("sex");
        String[] age = parameterMap.get("age");
        String[] grade = parameterMap.get("grade");
        String[] classNo = parameterMap.get("classNo");
        String[] dormitoryNo = parameterMap.get("dormitoryNo");

        String targetSql = "update student set name = ?, sex = ?, age = ?, " +
                "grade = ?, class_no = ?, dormitory_no = ? where id = ?;";
        try {
            PreparedStatement stmt = new JDBCConnector().getPrepareStatement(targetSql);
            stmt.setString(1, name[0]);
            stmt.setInt(2, Integer.parseInt(sex[0]));
            stmt.setInt(3, Integer.parseInt(age[0]));
            stmt.setInt(4, Integer.parseInt(grade[0]));
            stmt.setString(5, classNo[0]);
            stmt.setString(6, dormitoryNo[0]);
            stmt.setInt(7, Integer.parseInt(id[0]));
            stmt.execute();

            Map<String, Integer> map = new HashMap<>();
            map.put("responseCode", 1);
            String jsonString = JSON.toJSONString(map);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            /*返回数据*/
            resp.getWriter().write(jsonString);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
