package com.WHJ.servlet;

import com.WHJ.DTO.StudentDTO;
import com.WHJ.entity.Student;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchStuInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> map = new HashMap<>();
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] name = parameterMap.get("name");

        String sql = "select * from student where name = ?";
        JDBCConnector jdbcConnector = new JDBCConnector();
        PreparedStatement prepareStatement = null;
        List<Student> list = new ArrayList<>();
        //此处特殊使用code
        StudentDTO studentDTO = new StudentDTO();
        try {
            prepareStatement = jdbcConnector.getPrepareStatement(sql);
            prepareStatement.setString(1, name[0]);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                //Code为0就是有对应的数据
                studentDTO.setCode(0);
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setClassNo(resultSet.getString("class_no"));
                student.setDormitoryNo(resultSet.getString("dormitory_no"));

                //设置性别
                if (resultSet.getInt("sex") == 0) {
                    student.setSex("男");
                } else {
                    student.setSex("女");
                }

                //设置年级
                switch (resultSet.getInt("grade")) {
                    case 1:
                        student.setGrade("大一");
                        break;
                    case 2:
                        student.setGrade("大二");
                        break;
                    case 3:
                        student.setGrade("大三");
                        break;
                    case 4:
                        student.setGrade("大四");
                        break;
                    default:
                        student.setGrade("unKnown");
                }
                list.add(student);
            }
            if (list.isEmpty()) {
                //没找到
                studentDTO.setCode(1);
            }
            studentDTO.setMsg("");
            studentDTO.setCount(list.size());
            studentDTO.setData(list);

            String jsonString = JSON.toJSONString(studentDTO);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            /*返回数据*/
            resp.getWriter().write(jsonString);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
