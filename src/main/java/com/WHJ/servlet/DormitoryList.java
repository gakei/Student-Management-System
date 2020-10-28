package com.WHJ.servlet;

import com.WHJ.DTO.DormitoryDTO;
import com.WHJ.entity.Dormitory;
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

public class DormitoryList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JDBCConnector jdbcConnector = new JDBCConnector();
        String sql = "select * from dormitory;";
        List<Dormitory> list = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(sql);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setId(resultSet.getInt("id"));
                dormitory.setDormitoryNo(resultSet.getString("dormitory_no"));
                dormitory.setDormitoryMemNum(resultSet.getInt("dormitory_mem_num"));
                list.add(dormitory);
            }
            DormitoryDTO dormitoryDTO = new DormitoryDTO();
            dormitoryDTO.setCode(0);
            dormitoryDTO.setCount(list.size());
            dormitoryDTO.setData(list);

            String jsonString = JSON.toJSONString(dormitoryDTO);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            /*返回数据*/
            resp.getWriter().write(jsonString);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
