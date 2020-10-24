package com.WHJ.servlet;

import com.WHJ.DTO.StudentDTO;
import com.WHJ.entity.Student;
import com.WHJ.util.JDBCConnector;
import com.alibaba.fastjson.JSON;
import jdk.net.SocketFlow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentList extends HelloServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PreparedStatement stmt = new JDBCConnector().getPrepareStatement("select * from StuManageSys.student");

            ResultSet resultSet = stmt.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
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
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setCode(0);
            studentDTO.setMsg("");
            studentDTO.setCount(list.size());
            studentDTO.setData(list);

            String jsonString = JSON.toJSONString(studentDTO);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            /*返回数据*/
            resp.getWriter().write(jsonString);
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
