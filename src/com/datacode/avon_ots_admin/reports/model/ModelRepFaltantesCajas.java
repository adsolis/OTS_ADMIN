package com.datacode.avon_ots_admin.reports.model;

public class ModelRepFaltantesCajas {

	private String ruta;
	private String registro;
	private String representante;
	private String orden;
	private int facturado;
	private int recibido;
	private int cantFaltante;
	private String faltante;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

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

	public int getCantFaltante() {
		return cantFaltante;
	}

	public void setCantFaltante(int cantFaltante) {
		this.cantFaltante = cantFaltante;
	}

	public String getFaltante() {
		return faltante;
	}

	public void setFaltante(String faltante) {
		this.faltante = faltante;
	}

}
