package Controllers;
import java.sql.Date;
import java.util.List;

import com.hotel.DAO.*;
import com.hotel.factory.ConnectionFactory;
import com.hotel.modelo.Buscar;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.Reserva;

public class HuespedesControllers {
	private static ReservaDao reservaDAO;
	
	public HuespedesControllers () {
		this.reservaDAO = new ReservaDao(new ConnectionFactory().recuperaConexion());
	}
public void guardarHuesped(Huesped nuevoHuesped) {
		
		reservaDAO.guardarHuesped(nuevoHuesped);
	}

public List<Huesped> listarH(String texto) {
return ReservaDao.listarH(texto);
	
}

public int modificarH(Huesped huespedNuevo) {
	return reservaDAO.modificarH(huespedNuevo);
	
}
public int eliminar(Integer id, String tabla) {
	return reservaDAO.eliminar(id, tabla);
}


}
