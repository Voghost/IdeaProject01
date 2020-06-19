package com.Modle;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Edgar Liu
 * 用于处理注册页面的javaBean
 * 功能：
 *  1. 判断两次密码是否一直
 *  2. 判断是已存在这个用户
 */
public class NewAccountBean {
    String userName;
    String password1;
    String password2;

    public NewAccountBean() {
    }

    public NewAccountBean(String userName, String password1, String password2) {
        this.userName = userName;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "NewAccountBean{" +
                "userName='" + userName + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }

    //判断两次密码是否一致
    public boolean isCorrectPassword(){
        if(password1.equals(password2)){
            return true;
        }else {
            return false;
        }
    }

    //判断是否存在需要注册的用户
    public int hasCorrectUserName() {
        int judgeAccount;
        DetermineUsersBean determineUsersBean=new DetermineUsersBean(userName,password1);
        judgeAccount=determineUsersBean.determine();
        return judgeAccount;
    }

}


