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
        Map<String, Integer> map = new HashMap<>();
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] id = parameterMap.get("id");
        String[] name = parameterMap.get("name");
        String[] sex = parameterMap.get("sex");
        String[] age = parameterMap.get("age");
        String[] grade = parameterMap.get("grade");
        String[] classNo = parameterMap.get("classNo");
        String[] dormitoryNo = parameterMap.get("dormitoryNo");

        if (sex[0].equals("男")) {
            sex[0] = "0";
        } else if (sex[0].equals("女")) {
            sex[0] = "1";
        } else {
            //3是用户输入有误
            map.put("responseCode", 3);
            packAsJson(resp, map);
            return;
        }

        switch (grade[0]) {
            case "大一":
                grade[0] = "1";
                break;
            case "大二":
                grade[0] = "2";
                break;
            case "大三":
                grade[0] = "3";
                break;
            case "大四":
                grade[0] = "4";
                break;
            default:
                //3是用户输入有误
                map.put("responseCode", 3);
                packAsJson(resp, map);
                return;
        }

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
            //1为请求成功
            map.put("responseCode", 1);
            packAsJson(resp, map);
        } catch (ClassNotFoundException | SQLException e) {
            //2为服务器程序出错
            map.put("responseCode", 2);
            packAsJson(resp, map);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //3是用户输入有误
            map.put("responseCode", 3);
            packAsJson(resp, map);
        }
    }

    private void packAsJson(HttpServletResponse resp, Map<String, Integer> map) throws IOException {
        String jsonString = JSON.toJSONString(map);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        /*返回数据*/
        resp.getWriter().write(jsonString);
    }
}
