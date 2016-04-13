package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelRelPedidosDejados {

	private String titulo;
	private String zona;
	private String campania;
	private String ruta;
	private String chofer;
	private String ayudante;
	private String poblacionP;
	private String repartidor;
	private String encargado;
	private String clave;
	private List<ModelTablaRelPedidosDejados> pedidosDejados;
	
	public List<ModelTablaRelPedidosDejados> getPedidosDejados() {
		return pedidosDejados;
	}
	public void setPedidosDejados(List<ModelTablaRelPedidosDejados> pedidosDejados) {
		this.pedidosDejados = pedidosDejados;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getChofer() {
		return chofer;
	}
	public void setChofer(String chofer) {
		this.chofer = chofer;
	}
	public String getAyudante() {
		return ayudante;
	}
	public void setAyudante(String ayudante) {
		this.ayudante = ayudante;
	}
	public String getPoblacionP() {
		return poblacionP;
	}
	public void setPoblacionP(String poblacionP) {
		this.poblacionP = poblacionP;
	}
	public String getRepartidor() {
		return repartidor;
	}
	public void setRepartidor(String repartidor) {
		this.repartidor = repartidor;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
