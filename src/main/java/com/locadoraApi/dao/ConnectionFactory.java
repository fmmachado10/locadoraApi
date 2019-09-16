package com.locadoraApi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5433/LOCADORA_VEICULOS";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
		    return DriverManager.getConnection(URL,USER, PASSWORD);
		}catch (Exception e) {
			System.out.print("Erro ao conectar no DB! " + e.getMessage() );
			return null;
		}
	}

}
