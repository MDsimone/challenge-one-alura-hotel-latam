package com.hotel.modelo;

import java.sql.Date;

public class Reserva {
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String valor;
	private String formPago;

	public Reserva( Date fechaE, Date fechaS, String valor, String formPago) {
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.formPago = formPago;
	}

	public Reserva(Integer id, Date fechaE, Date fechaS, String valor, String formPago) {
		super();
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.formPago = formPago;
	}

	public Date getFechaE() {
		return fechaE;
	}

	public void setFechaE(Date fechaE) {
		this.fechaE = fechaE;
	}

	public Date getFechaS() {
		return fechaS;
	}

	public void setFechaS(Date fechaS) {
		this.fechaS = fechaS;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormPago() {
		return formPago;
	}

	public void setFormPago(String formPago) {
		this.formPago = formPago;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public Integer getId() {
		
		return id;
	}


	
	
}	
