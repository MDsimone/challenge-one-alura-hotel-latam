package Controllers;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;

import com.hotel.DAO.ReservaDao;
import com.hotel.factory.ConnectionFactory;
import com.hotel.modelo.Buscar;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.Reserva;


public class ReservasControllers {
	private  ReservaDao reservaDAO;
	
	public ReservasControllers() {
		this.reservaDAO = new ReservaDao(new ConnectionFactory().recuperaConexion());
	}
	
	public  void guardar(Reserva nuevaReserva) {
		reservaDAO.guardar(nuevaReserva);
		
	}
	public List<Reserva> listar(Integer id) {
	return ReservaDao.listar(id);
		
	}
	
	public List<Reserva> listBuscarReserva(Buscar nuevaBusqueda){
		return ReservaDao.listarBusquedaReserva(nuevaBusqueda);
	}
	
	public int modificar(Reserva nuevaReserva) {
		return reservaDAO.modificar(nuevaReserva);
	}
	
	public int eliminar(Integer id, String tabla) {
	
		return reservaDAO.eliminar(id, tabla);
	}


}
