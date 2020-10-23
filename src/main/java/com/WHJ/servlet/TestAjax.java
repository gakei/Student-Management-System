package com.WHJ.servlet;

import com.WHJ.entity.Student;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setId(1);
        student.setSex("男");
        student.setName("WHJ");
        student.setGrade("大四");
        student.setAge(22);

        String jsonString = JSON.toJSONString(student);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        /*返回数据*/
        resp.getWriter().write(jsonString);
    }
}
