package com.WHJ.servlet;

import com.WHJ.util.JDBCConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.WHJ.util.JsonUtil.packAsJson;

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
        Map<String, Integer> responseMap = new HashMap<>();
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

        String inputStuInfo = "insert into student (name, sex, age, grade, class_no, dormitory_no) values (?, ?, ?, ?, ?, ?);";
        try {
            JDBCConnector jdbcConnector = new JDBCConnector();
            PreparedStatement prepareStatement = jdbcConnector.getPrepareStatement(inputStuInfo);
            prepareStatement.setString(1, name[0]);
            prepareStatement.setInt(2, Integer.parseInt(sex[0]));
            prepareStatement.setInt(3, Integer.parseInt(age[0]));
            prepareStatement.setInt(4, Integer.parseInt(grade[0]));
            prepareStatement.setString(5, classNo[0]);
            prepareStatement.setString(6, dormitoryNo[0]);
            int rs = prepareStatement.executeUpdate();

            String dormitoryMemNumSQL = "select dormitory_no, dormitory_mem_num from dormitory where dormitory_no = ?";
            PreparedStatement prepareStatement1 = jdbcConnector.getPrepareStatement(dormitoryMemNumSQL);
            prepareStatement1.setString(1, dormitoryNo[0]);
            ResultSet resultSet = prepareStatement1.executeQuery();

            assert resultSet != null;
            while (!resultSet.next()) {
                //responseCode为2，没有查到这个宿舍号
                responseMap.put("responseCode", 2);
                packAsJson(resp, responseMap);
                req.getRequestDispatcher( "/notExitDormitoryNo.jsp").forward(req, resp);
                return;
            }

            int dormitoryMemNum = 0;
            int incRs = 0;
            while (resultSet.next()) {
                dormitoryMemNum = resultSet.getInt("dormitory_mem_num");
            }
            if (dormitoryMemNum >=8) {
                //超出宿舍人数限制
                req.getRequestDispatcher( "/dormitoryMemFailure.jsp").forward(req, resp);
                return;
            } else {
                dormitoryMemNum += 1;
                String dormitoryMemNumIncSQL = "update dormitory set dormitory_mem_num = ? where dormitory_no = ?";
                PreparedStatement prepareStatement2 = jdbcConnector.getPrepareStatement(dormitoryMemNumIncSQL);
                prepareStatement2.setInt(1, dormitoryMemNum);
                prepareStatement2.setString(2, dormitoryNo[0]);
                incRs = prepareStatement2.executeUpdate();
            }

            if (rs != 0 &&  incRs!= 0) {
                req.setAttribute( "isSuccess ", true);
                req.getRequestDispatcher( "/success.jsp").forward(req, resp);
                return;
            }
            req.getRequestDispatcher( "/failure.jsp").forward(req, resp);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            req.getRequestDispatcher( "/dubName.jsp").forward(req, resp);
        }
        catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            req.getRequestDispatcher( "/failure.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
