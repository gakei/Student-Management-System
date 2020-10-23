package com.WHJ.servlet;

import com.WHJ.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowList extends HelloServlet {
    public static final String URL = "jdbc:mysql://localhost:3306/StuManageSys?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //3.操作数据库，实现增删改查
            PreparedStatement stmt = conn.prepareStatement("select * from StuManageSys.student");

            ResultSet resultSet = stmt.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));

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
                }
                list.add(student);
            }
            req.setAttribute("list", list);
            req.getRequestDispatcher( "webPage/back.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
