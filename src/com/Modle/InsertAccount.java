package com.Modle;

import javax.sql.DataSource;
import java.awt.image.PackedColorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Edgar Liu
 * InsertAccount 在数据库中添加一条用户的记录 用AccountBean包装
 */
public class InsertAccount {
    AccountBean accountBean;


    public InsertAccount(AccountBean accountBean) {
        this.accountBean = new AccountBean(accountBean.userName, accountBean.password);
    }

    public int insertAccount() {
        int flag = -1;

        if (accountBean.password.equals("") || accountBean.userName.equals("")) {
            flag = -1;
            System.out.println("插入错误");
            return flag;
        }
        DataBean dataBean = new DataBean();
        DataSource dataSource = dataBean.getDataSource();

        Connection connection = null;
        PreparedStatement preparedStatement = null; //有参数的执行语句
        ResultSet resultSet = null; //结果集对象
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into account(userName,password) values (?,?)");
            preparedStatement.setString(1, accountBean.userName);
            preparedStatement.setString(2, accountBean.password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
            flag = -1;
        } catch (Exception e) {
            System.out.println(e.toString());
            flag = -1;
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }

        return flag;
    }

}
