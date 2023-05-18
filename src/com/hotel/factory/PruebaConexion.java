package com.hotel.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
    	Connection conn = new ConnectionFactory().recuperaConexion();
    	Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT usuario, clave FROM usuarios" );
		String usuario = "";
		String clave ="";
		if (resultSet.next()) {
			 usuario = resultSet.getString("usuario");
			 clave = resultSet.getString("clave");
		}
		 
        System.out.println("Cerrando la conexión Holtel Alura Usuario: " + usuario + clave);

        conn.close();
    }

}
