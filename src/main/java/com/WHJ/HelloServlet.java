package com.WHJ;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String successText = "登陆成功";
    private final String failText = "密码或用户名错误，登录失败";
    private final String emptyText = "密码或用户名为空，登录失败";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] username = parameterMap.get("username");
        String[] password = parameterMap.get("password");
        if (username[0] == null || password[0] == null) {
            req.setAttribute( "ValueA ", emptyText);
            req.getRequestDispatcher( "/index.jsp").forward(req, resp);
        }

        if (username[0].equals("") || password[0].equals("")) {
            req.setAttribute( "ValueA ", emptyText);
            req.getRequestDispatcher( "/index.jsp").forward(req, resp);
        }

        if (username[0].equals("WHJ") && password[0].equals("123456")) {
            req.setAttribute( "ValueA ", successText);
        } else {
            req.setAttribute( "ValueA ", failText);
        }
        req.getRequestDispatcher( "/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
