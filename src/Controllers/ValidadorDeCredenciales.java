package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.factory.ConnectionFactory;

public class ValidadorDeCredenciales {
	public  List<Map<String,String>>  ValidaCredencial() {
		String usuario = "";
		String clave ="";
		Connection conn = new ConnectionFactory().recuperaConexion();
		final Statement statement;
		try {
			 statement = conn.createStatement();

		ResultSet resultSet = statement.executeQuery("SELECT usuario, clave FROM usuarios" );
		 List<Map<String,String>> resultado = new ArrayList<>();
		
		if (resultSet.next()) {
			Map<String,String> fila = new HashMap<>();
			fila.put("usuario", resultSet.getString("usuario"));
			fila.put("clave", resultSet.getString("clave"));
			// usuario = resultSet.getString("usuario");
			// clave = resultSet.getString("clave");
		  resultado.add(fila);
		}
		return resultado;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
	
		}	
	}
}
