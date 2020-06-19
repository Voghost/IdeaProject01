package com.Modle;

/**
 * Created by Edgar Liu
 * AccountBean 类（javaBean） 用于 封装 账户的名字和密码
 */


public class AccountBean {
    String userName;
    String password;



    public AccountBean(String userName, String password) {
        if(userName==null){
            userName="";
        }
        if(password==null){
            password="";
        }

        this.userName = userName;
        this.password = password;
    }

    public AccountBean(int userId, String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
