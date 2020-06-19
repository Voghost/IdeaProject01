package com.Modle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Edgar Liu
 */
public class SessionProcessBean {
    String userName;
    String sessionUserName;
    HttpServletRequest request;

    public SessionProcessBean(String userName, HttpServletRequest request) {
        this.userName = userName;
        this.request = request;
    }

    public void newSession(){
        HttpSession session=request.getSession();
        session.setAttribute("userName",userName);
    }

    public String getSession(){
        HttpSession session=request.getSession();
        String userNameSession=(String)session.getAttribute("userName");
        return userNameSession;
    }

    public boolean isCorrectSessionUser(){
        HttpSession session=request.getSession();
        String userNameSession=(String)session.getAttribute("userName");
       if(userNameSession==null){
           userNameSession="";
       }
       if(userName==null){
           userName=null;
       }
        if(userNameSession.equals(userName)){
            return true;
        }
        return  false;
    }

}
