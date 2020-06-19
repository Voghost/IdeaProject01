package com.Modle;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by Edgar Liu
 * 用于初始化数据库的javaBean
 */
public class DataBean {
    DataSource dataSource;

    public DataBean() {
        dataSource = new BasicDataSource();
        ((BasicDataSource) dataSource).setDriverClassName("com.mysql.cj.jdbc.Driver");
        ((BasicDataSource) dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/Account?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        ((BasicDataSource) dataSource).setUsername("localTest");
        ((BasicDataSource) dataSource).setPassword("Lf1141776830");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
