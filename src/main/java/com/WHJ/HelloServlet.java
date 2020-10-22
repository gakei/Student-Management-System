package com.WHJ;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String successText = "登陆成功";
    private final String failText = "密码或用户名错误，登录失败";
    private final String emptyText = "密码或用户名为空，登录失败";

    public static final String URL = "jdbc:mysql://localhost:3306/StuManageSys?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] name = parameterMap.get("name");
        String[] sex = parameterMap.get("sex");
        String[] age = parameterMap.get("age");
        String[] grade = parameterMap.get("grade");



        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //3.操作数据库，实现增删改查
            PreparedStatement stmt = conn.prepareStatement("insert into student (name, sex, age, grade) values (?, ?, ?, ?);");

            //将性别转换为整数
            if (Integer.parseInt(sex[0]) == 0) {
                sex[0] = "0";
            } else {
                sex[0] = "1";
            }

            stmt.setString(1, name[0]);
            stmt.setInt(2, Integer.parseInt(sex[0]));
            stmt.setInt(3, Integer.parseInt(age[0]));
            stmt.setInt(4, Integer.parseInt(grade[0]));
            int rs = stmt.executeUpdate();
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
