package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelDirectorioLideres {

	private String titulo;
	private String de;
	private String para;
	private String plaza;
	private String mensaje;
	private  String domicilio;
	private String consejero;
	private String telefono;
	private String codigo;
	private List<ModelTablaDirectorioLideres> listaLideres;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getPlaza() {
		return plaza;
	}
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getConsejero() {
		return consejero;
	}
	public void setConsejero(String consejero) {
		this.consejero = consejero;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<ModelTablaDirectorioLideres> getListaLideres() {
		return listaLideres;
	}
	public void setListaLideres(List<ModelTablaDirectorioLideres> listaLideres) {
		this.listaLideres = listaLideres;
	}
}
