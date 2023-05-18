package com.hotel.modelo;

public class Buscar {
	private Integer idR;
	private String apellido;
	
	public Integer getIdR() {
		return idR;
	}
	public void setIdR(Integer idR) {
		this.idR = idR;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Buscar(Integer idR, String apellido) {

		this.idR = idR;
		this.apellido = apellido;
	}
	
	
}
