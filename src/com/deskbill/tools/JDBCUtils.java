package com.deskbill.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static  String URL = "jdbc:mysql://localhost:3306/bill?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&autoReconnect=true";
	public static  String USERNAME = "root";
	public static  String PASSWORD = "root";
	
	private static final int MAX_IDLE = 3;
	private static final long MAX_WAIT = 5000;
	private static final int MAX_ACTIVE = 5;
	private static final int INITIAL_SIZE = 3;
	
	private static BasicDataSource dataSource = new BasicDataSource();
	static {
	/*	String path = System.getProperty("user.dir");
		Properties properties = new Properties();
		try {
        InputStream inputStream = new FileInputStream(path+"resource\\config.properties");
        properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
		if (!StringUtils.isNull(properties.getProperty("jdbc.url"))) {
			URL = properties.getProperty("jdbc.url");
		}
		if (!StringUtils.isNull(properties.getProperty("jdbc.username"))) {
			USERNAME = properties.getProperty("jdbc.username");
		}
		if (!StringUtils.isNull(properties.getProperty("jdbc.url"))) {
			PASSWORD = properties.getProperty("jdbc.password");
		}*/
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setMaxActive(MAX_IDLE);
		dataSource.setMaxWait(MAX_WAIT);
		dataSource.setMaxActive(MAX_ACTIVE);
		dataSource.setInitialSize(INITIAL_SIZE);
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
}

