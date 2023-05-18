package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.hotel.factory.ConnectionFactory;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.hotel.modelo.Buscar;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.Reserva;

public class ReservaDao {
 final private Connection conn;
	
	public ReservaDao(Connection conn) {
		this.conn = conn;		
	}
	
	
	public void guardar(Reserva reserva) {
	try {
		conn.setAutoCommit(false);
		final PreparedStatement statement = conn.prepareStatement("INSERT INTO reservas (fechaEntrada, fechaSalida, valor, FormaPago)"
				+ "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		 try(statement){
					ejecutaRegistro(reserva, statement);
					conn.commit();		
				}
	
	}catch(SQLException e) {
		  throw new RuntimeException();
	}

	}
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
	
		statement.setDate(1, reserva.getFechaE());
		statement.setDate(2, reserva.getFechaS());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getFormPago());
		
		statement.execute();
		
		final ResultSet resulSet = statement.getGeneratedKeys(); 
		try(resulSet){
		while (resulSet.next()) {
			reserva.setId(resulSet.getInt(1));
			 //para ver el valor del ID generado
			System.out.println(
					String.format("Su número de Reserva es %s",reserva));
		}
		}
	}
	
	public void guardarHuesped(Huesped huesped) {
		try {
			conn.setAutoCommit(false);
			final PreparedStatement statement = conn.prepareStatement("INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			 try(statement){
						ejecutaRegistroHuesped(huesped, statement);
						conn.commit();		
					}
		
		}catch(SQLException e) {
			  throw new RuntimeException();
		}

	}
	private void ejecutaRegistroHuesped(Huesped huesped, PreparedStatement statement) throws SQLException {
	
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());
		
		statement.execute();
		
		final ResultSet resulSet = statement.getGeneratedKeys(); 
		try(resulSet){
		while (resulSet.next()) {
			huesped.setId(resulSet.getInt(1));
			 //para ver el valor del ID generado
			System.out.println(
					String.format("Fue generado el usuario con ID %s",huesped));
		}
		}
		
	}
	public static List<Reserva> listar(Integer id) {
		List<Reserva> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.recuperaConexion();
		
	try(conn){
		Integer idR = id;
		String sqlConsulta = "";
		switch (idR) {
		case  0:
			String sqlConsulta1 ="SELECT * FROM reservas";
			sqlConsulta = sqlConsulta1;
			break;
		 default :
			 if (idR!=0) {
			
			String sqlConsulta2 ="SELECT * FROM reservas Where ID="+ idR;
			 sqlConsulta = sqlConsulta2;
			 }
			break;
		}
		String sql = sqlConsulta;
		
			final PreparedStatement statement = conn.prepareStatement(sql);
		try(statement){

				boolean result  = statement.execute(sql); //Esto es para saber si tengo una lista

				ResultSet resultset = statement.getResultSet();
								
				//recorro resulset con next
				
				while (resultset.next()) {//envío los datos para crear el constructor producto
					Reserva fila = new Reserva(resultset.getInt("ID"),
							resultset.getDate("fechaEntrada"),
							resultset.getDate("fechaSalida"),
							resultset.getString("valor"),
							resultset.getString("FormaPago")
							);
	
					
					resultado.add(fila);
				}
		
				return resultado;
			}
	 }catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static List<Huesped> listarH(String texto) {
		List<Huesped> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection conn = factory.recuperaConexion();
		
	try(conn){
		String Apellido = texto;
		String consulta = "";
		
		if (!"".equals(Apellido)) {
			String consulta1 = "SELECT * FROM huespedes Where apellido Like '%"+ Apellido +"%'";
			consulta = consulta1;
		}else {	
			String consulta1 ="SELECT * FROM huespedes";
			consulta = consulta1;
		}
			String sql = consulta;
			final PreparedStatement statement = conn.prepareStatement(sql);
		try(statement){

				boolean result  = statement.execute(sql); //Esto es para saber si tengo una lista

				ResultSet resultset = statement.getResultSet();
								
				//recorro resulset con next
				
				while (resultset.next()) {//envío los datos para crear el constructor
					Huesped fila = new Huesped(resultset.getInt("ID"),
							resultset.getString("nombre"),
							resultset.getString("apellido"),
							resultset.getDate("fecha_nacimiento"),
							resultset.getString("nacionalidad"),
							resultset.getString("telefono"),
							resultset.getInt("id_reserva")
							);
	
					
					resultado.add(fila);
				}
		
				return resultado;
			}
	 }catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public  int modificar(Reserva reserva) {
		try {
			conn.setAutoCommit(false);
			final PreparedStatement statement = conn.prepareStatement("UPDATE reservas SET"
					+ "	 fechaEntrada = ?"
		            + ", fechaSalida = ?"
		            + ", valor = ?"
		            + ", FormaPago = ?"
		            + " WHERE ID = ?");
			try(statement){		
					statement.setDate(1,reserva.getFechaE());
					statement.setDate(2,reserva.getFechaS());
					statement.setString(3, reserva.getValor());
					statement.setString(4,reserva.getFormPago());
					statement.setInt(5, reserva.getId());
					statement.execute();	
					
					int updateCount = statement.getUpdateCount();
					return updateCount;
					}
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
}


	public int modificarH(Huesped huespedNuevo) {
		try{
			final PreparedStatement statement = conn.prepareStatement("UPDATE huespedes SET"
					+ "nombre = ?"
		            + ", apellido = ?"
		            + ", fecha_nacimiento = ?"
		            + ", nacionalidad = ?"
		            + ", telefono = ?"
		            + ", id_reserva = ?"
		            + " WHERE ID = ?");
			try(statement){		
					statement.setString(1, huespedNuevo.getNombre());
					statement.setString(2, huespedNuevo.getApellido());
					statement.setDate(3, huespedNuevo.getFechaNacimiento());
					statement.setString(4,huespedNuevo.getNacionalidad());
					statement.setString(5,huespedNuevo.getTelefono());
					statement.setInt(5, huespedNuevo.getIdReserva());
					statement.setInt(6, huespedNuevo.getId());
					statement.execute();	
					
					int updateCount = statement.getUpdateCount();
					return updateCount;
					}
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
		
	}

	

	public int eliminar(Integer id, String tabla) {
		try{
				String Sql= "DELETE FROM "+ tabla + " WHERE ID = ?";
				
				final PreparedStatement statement = conn.prepareStatement(Sql);
				try(statement){
					statement.setInt(1, id);
					statement.execute();
					int updateCount = statement.getUpdateCount();
					return updateCount;
					//mostrar en la aplicacion cuantos´registros fueron eliminados por lo que devuelvo un int
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public static List<Reserva> listarBusquedaReserva(Buscar nuevaBusqueda){
			List<Reserva> resultado = new ArrayList<>();
			ConnectionFactory factory = new ConnectionFactory();
			final Connection conn = factory.recuperaConexion();
			
		try(conn){
				String sql ="SELECT * FROM reservas HERE ID = ?";
				final PreparedStatement statement = conn.prepareStatement(sql);
			try(statement){
				statement.setInt(1, nuevaBusqueda.getIdR());
					boolean result  = statement.execute(sql); //Esto es para saber si tengo una lista

					ResultSet resultset = statement.getResultSet();
									
					//recorro resulset con next
					
					while (resultset.next()) {//envío los datos para crear el constructor producto
						Reserva fila = new Reserva(resultset.getInt("ID"),
								resultset.getDate("fechaEntrada"),
								resultset.getDate("fechaSalida"),
								resultset.getString("valor"),
								resultset.getString("FormaPago")
								);
		
						
						resultado.add(fila);
					}
			
					return resultado;
				}
		 }catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	

	
	
}
	
	






