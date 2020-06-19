package com.Controller;

import com.Modle.AccountBean;
import com.Modle.DetermineUsersBean;
import com.Modle.SessionProcessBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Edgar Liu
 * 登陆请求控制器，处理分发请求
 */
@WebServlet(name = "LoginHandlerServlet",urlPatterns = "/loginHandler")
public class LoginHandlerServlet extends HttpServlet {
    protected  void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        int judgeAccount;

        AccountBean accountBean=new AccountBean(request.getParameter("userName"),request.getParameter("password"));//用accountBean封装用户名密码
        DetermineUsersBean determineUsersBean=new DetermineUsersBean(accountBean);//将账户信息传入DetermineUserName中
        judgeAccount=determineUsersBean.determine(); //判断传入类的正确与否

        if(judgeAccount==1){
            //密码错误登陆失败 分发给loginFailPassword
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("loginFailPassword.jsp");
            requestDispatcher.forward(request,response);

        }else if(judgeAccount==2){
            //登陆成功 分发给loginSuccess
            //request.getSession().setAttribute("userName",accountBean.getUserName());//设置Session
            SessionProcessBean sessionProcessBean=new SessionProcessBean(accountBean.getUserName(),request);
            sessionProcessBean.newSession();

            RequestDispatcher requestDispatcher=request.getRequestDispatcher("loginSuccess.jsp");
            requestDispatcher.forward(request,response);

        }else if(judgeAccount==0){
            //不存在账户登陆失败 分发给loginFailUserName
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("loginFailUserName.jsp");
            requestDispatcher.forward(request,response);
        }else if(judgeAccount==-1){
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
