package com.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;


public class ConnectionFactory {
	
	private DataSource datasource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("marilin55");
		pooledDataSource.setMaxPoolSize(10);
		
		this.datasource = pooledDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
