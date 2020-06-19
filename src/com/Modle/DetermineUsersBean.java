package com.Modle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Edgar Liu
 * DetermineUsersBean
 * 通过这个类判断用户输入的用户名、密码的合法性
 * determine()   返回 -1 出现错误
 *                    0 没有这个用户
 *                    1 有这个用户，但密码不正确
 *                    2 有这个用护，且密码正确
 */
public class DetermineUsersBean {
    AccountBean accountBean ;


    public DetermineUsersBean(AccountBean accountBean) {
        if(accountBean.userName==null){
            accountBean.userName="";
        }
        if(accountBean.userName==null){
            accountBean.password="";
        }

         this.accountBean=new AccountBean(accountBean.userName,accountBean.password);

    }

    public DetermineUsersBean(String userName, String password) {

        if(userName==null){
            userName="";
        }
        if(password==null){
            password="";
        }
       accountBean=new AccountBean(userName,password);
    }

    public AccountBean getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }

    //用于判断传入的账户 是否匹配
    public int determine(){
        int correctAccount = 0;   // 0 没有这个用户 , 1 存在这个用户 ,2存在这个用户且密码正确, -1 出现异常

        //用自定义的DataBean对象
        DataBean dataBean = new DataBean();
        DataSource dataSource = dataBean.getDataSource();

        Connection connection = null;
        PreparedStatement preparedStatement = null; //有参数的执行语句
        ResultSet resultSet = null; //结果集对象

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * FROM account WHERE userName= ?");
            preparedStatement.setString(1, accountBean.userName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                correctAccount = 1; //存在这个用户
            } else {
                correctAccount = 0; //不存在这个用户
            }

            preparedStatement=connection.prepareStatement("select * FROM account WHERE userName=? AND password=?");
            preparedStatement.setString(1,accountBean.userName);
            preparedStatement.setString(2,accountBean.password);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                correctAccount=2; //存在这个用户，且密码正确
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
            correctAccount = -1;

        } catch (Exception e) {
            System.out.println(e.toString());
            correctAccount = -1;
        } finally {

            //关闭数据库连接池
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return correctAccount;
    }
}
