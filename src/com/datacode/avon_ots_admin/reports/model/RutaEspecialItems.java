package com.datacode.avon_ots_admin.reports.model;

public class RutaEspecialItems {

	private String claveOrden;
	private int idItem;
	private String codigoBarras;
	private String descripcion;
	private int cantidadSolicitada;
	private String tipo;
	private boolean marcado;
	private String codigoFSC;
	private String codigoEAN13;
	private int cantidadIngresada;
	private String clase;
	private String registro;

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public int getCantidadIngresada() {
		return cantidadIngresada;
	}

	public void setCantidadIngresada(int cantidadIngresada) {
		this.cantidadIngresada = cantidadIngresada;
	}

	public String getCodigoFSC() {
		return codigoFSC;
	}

	public void setCodigoFSC(String codigoFSC) {
		this.codigoFSC = codigoFSC;
	}

	public String getCodigoEAN13() {
		return codigoEAN13;
	}

	public void setCodigoEAN13(String codigoEAN13) {
		this.codigoEAN13 = codigoEAN13;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
