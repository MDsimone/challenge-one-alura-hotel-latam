package Controllers;

public class Usuarios {
	
	private String nombre;
	private String clave;
	
	public Usuarios(String nombre, String clave) {
		this.nombre = nombre;
		this.clave = clave;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	

}
