package com.Modle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Edgar Liu
 */
@WebServlet(name = "DownloadServlet", urlPatterns = "/downloadServlet")

public class DownloadServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=Example.pdf");

        byte[] buffer = new byte[1024 * 1024];
        DataBean dataBean = new DataBean();
        DataSource dataSource = dataBean.getDataSource();
        InputStream inputStream = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            dataSource = dataBean.getDataSource();
            connection = dataSource.getConnection();

            String sql = "SELECT * FROM fileList WHERE fileID =1";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                inputStream= resultSet.getBinaryStream(2);
            }


            int readBytes = -1;

            while ((readBytes = inputStream.read(buffer, 0, 1024 * 1024)) != -1) {
                outputStream.write(buffer, 0, 1024 * 1024);
            }


        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
