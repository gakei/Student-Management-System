package com.WHJ.servlet;

import com.WHJ.util.JDBCConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class InputStuInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String successText = "登陆成功";
    private final String failText = "密码或用户名错误，登录失败";
    private final String emptyText = "密码或用户名为空，登录失败";

    public static final String URL = "jdbc:mysql://localhost:3306/StuManageSys?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] name = parameterMap.get("name");
        String[] sex = parameterMap.get("sex");
        String[] age = parameterMap.get("age");
        String[] grade = parameterMap.get("grade");
        String[] classNo = parameterMap.get("classNo");
        String[] buildingNo = parameterMap.get("buildingNo");
        String[] dormitoryNo = parameterMap.get("dormitoryNo");

        if (sex[0].equals("男")) {
            sex[0] = "0";
        } else {
            sex[0] = "1";
        }
        //拼接楼栋号和宿舍号
        dormitoryNo[0] = buildingNo[0] + dormitoryNo[0];

        String sql = "insert into student (name, sex, age, grade, class_no, dormitory_no) values (?, ?, ?, ?, ?, ?);";
        try {
            JDBCConnector jdbcConnector = new JDBCConnector();
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(sql);
            prepareStatement.setString(1, name[0]);
            prepareStatement.setInt(2, Integer.parseInt(sex[0]));
            prepareStatement.setInt(3, Integer.parseInt(age[0]));
            prepareStatement.setInt(4, Integer.parseInt(grade[0]));
            prepareStatement.setString(5, classNo[0]);
            prepareStatement.setString(6, dormitoryNo[0]);
            int rs = prepareStatement.executeUpdate();
            if (rs != 0) {
                req.setAttribute( "isSuccess ", true);
                req.getRequestDispatcher( "/success.jsp").forward(req, resp);
            }
            req.getRequestDispatcher( "/failure.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            req.getRequestDispatcher( "/failure.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
