package com.Modle;

import jdk.internal.util.xml.impl.Input;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig()
public class UploadServlet extends HttpServlet {
    private String fileNameExtractorRegex = "filename=\".+\"";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        DataBean dataBean=new DataBean();
        DataSource dataSource=dataBean.getDataSource();
        String fileName=null;
        String userName=null;
        InputStream inputStream=null;
        PrintWriter out=response.getWriter();

        HttpSession session = request.getSession();
        userName=(String)session.getAttribute("userName");
        if(userName==null){
            userName="";
        }


        //获取文件名
        for(Part p:request.getParts()){
            //if(p.getContentType().contains("image")){
                fileName=getFileName(p);
                inputStream=p.getInputStream();
            //}
        }
        System.out.println("userName: "+userName);
        System.out.println("fileName: "+fileName);

        Connection connection = null;
        PreparedStatement preparedStatement = null; //有参数的执行语句

/*
        ServletInputStream servletInputStream = request.getInputStream();
        InputStream inputStream=servletInputStream;
*/



        String sql="UPDATE account SET userPhoto =? ,userPhotoName=? WHERE userName=?";
        try{
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setBinaryStream(1,inputStream);
            preparedStatement.setString(2,fileName);
            preparedStatement.setString(3,userName);
            preparedStatement.executeUpdate();





        }catch (SQLException e){
            System.out.println("error1"+e.toString());
        }finally {
                if(preparedStatement!=null){
                    try{
                        preparedStatement.close();
                    }catch (SQLException e){
                        System.out.println("error3"+e.toString());
                    }
                }
                if(connection!=null){
                    try{
                        connection.close();
                    }catch (SQLException e){
                        System.out.println("error4"+e.toString());
                    }
                }
        }
        out.println("<center>上传成功!</center> <br/>");
    }

    //获得文件名
    private String getFileName(Part part) {
        String contentDesc = part.getHeader("content-disposition");
        String fileName = null;
        Pattern pattern = Pattern.compile(fileNameExtractorRegex);
        Matcher matcher = pattern.matcher(contentDesc);
        if (matcher.find()) {
            fileName = matcher.group();
            fileName = fileName.substring(10, fileName.length() - 1);
        }
        return fileName;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
