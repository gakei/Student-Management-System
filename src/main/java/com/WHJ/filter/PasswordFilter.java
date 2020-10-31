package com.WHJ.filter;

import com.WHJ.util.JDBCConnector;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PasswordFilter implements Filter {

    public PasswordFilter() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest)request;
        String sql = "select * from admin_account where username = ? and password = ?";
        JDBCConnector jdbcConnector = new JDBCConnector();
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String dbUsername = null;
        String dbPassword = null;

        if (req.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }

        if (password == null || username == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                dbUsername = resultSet.getString("username");
                dbPassword = resultSet.getString("password");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (dbUsername == null || dbPassword == null) {
            request.setAttribute("msg", "密码或用户名不正确！！！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!dbUsername.equals(username)) {
            request.setAttribute("msg", "密码或用户名不正确！！！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (req.getSession().getAttribute("user") == null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
