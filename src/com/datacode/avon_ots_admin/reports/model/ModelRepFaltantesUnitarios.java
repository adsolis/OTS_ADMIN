package com.datacode.avon_ots_admin.reports.model;

public class ModelRepFaltantesUnitarios {

	private String FSC;
	private String EAN13;
	private String descripcion;
	private String tipo;
	private int facturado;
	private int recibido;
	private int faltante;

	public int getFacturado() {
		return facturado;
	}

	public void setFacturado(int facturado) {
		this.facturado = facturado;
	}

	public int getRecibido() {
		return recibido;
	}

	public void setRecibido(int recibido) {
		this.recibido = recibido;
	}

	public int getFaltante() {
		return faltante;
	}

	public void setFaltante(int faltante) {
		this.faltante = faltante;
	}

	public String getFSC() {
		return FSC;
	}

	public void setFSC(String fSC) {
		FSC = fSC;
	}

	public String getEAN13() {
		return EAN13;
	}

	public void setEAN13(String eAN13) {
		EAN13 = eAN13;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
