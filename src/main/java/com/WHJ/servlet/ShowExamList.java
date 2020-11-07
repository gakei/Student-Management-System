package com.WHJ.servlet;

import com.WHJ.entity.Examination;
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
import java.util.ArrayList;
import java.util.List;

public class ShowExamList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Examination> list = new ArrayList<>();
        try {
            PreparedStatement stmt = new JDBCConnector().getPrepareStatement("select * from examination");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Examination examination = new Examination();
                examination.setId(resultSet.getInt("id"));
                examination.setExamTime(resultSet.getDate("exam_time"));
                examination.setExamName(resultSet.getString("exam_name"));
                examination.setChargeTeacher(resultSet.getString("charge_teacher"));
                examination.setExamPlace(resultSet.getString("exam_place"));
                list.add(examination);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String jsonString = JSON.toJSONString(list);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        /*返回数据*/
        resp.getWriter().write(jsonString);
    }
}
