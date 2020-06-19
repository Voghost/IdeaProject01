package com.Controller;

import com.Modle.AccountBean;
import com.Modle.InsertAccount;
import com.Modle.NewAccountBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Edgar Liu
 * 用于处理分发注册请求
 */
@WebServlet(name = "RegisterHandlerServlet",urlPatterns = "/registerHandlerServlet")
public class RegisterHandlerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
       NewAccountBean newAccountBean =new NewAccountBean(request.getParameter("userName"),request.getParameter("password1"),request.getParameter("password2"));
       if(newAccountBean.isCorrectPassword()){
           //两次密码一致
           int correctUserName=newAccountBean.hasCorrectUserName();
           if(correctUserName==0){
               //没有重名的用户名，可以注册
               AccountBean accountBean=new AccountBean(request.getParameter("userName"),request.getParameter("password1"));
               InsertAccount insertAccount=new InsertAccount(accountBean);
               insertAccount.insertAccount();//新建一个用户

               PrintWriter out =response.getWriter();
               out.println("注册成功，请登陆");
               RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.jsp");
               requestDispatcher.include(request,response);

           }else if(correctUserName>=1){
               //有重复的用户名，不可以注册
               RequestDispatcher requestDispatcher=request.getRequestDispatcher("registerFail.jsp");
               requestDispatcher.forward(request,response);
           }else{
               //出现错误

           }
       }else {
           //两次密码不一致
           PrintWriter out =response.getWriter();
           out.println("两次密码不一致，请检查");
           RequestDispatcher requestDispatcher=request.getRequestDispatcher("register.jsp");
           requestDispatcher.include(request,response);
       }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
