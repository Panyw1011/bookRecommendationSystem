package com.bookrecommend.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class MyConfig {
//    @Autowired
//    protected ApplicationContext applicationContext;
//
//
//    @Bean
//    public DataModel getMySQLDataModel() throws ClassNotFoundException {
////        MysqlDataSource dataSource = new MysqlDataSource();
////        Class.forName("com.mysql.cj.jdbc.Driver");
////        dataSource.setServerName("localhost");
////        dataSource.setUser("root");
////        dataSource.setPassword("123456");
////        dataSource.setDatabaseName("bookrecommend");
//        DataSource dataSource = applicationContext.getBean(DataSource.class);
//        System.out.println(dataSource);
//        JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource,"ratings","ID","ISBN","Rating","time");
//        return dataModel;
//    }
}
