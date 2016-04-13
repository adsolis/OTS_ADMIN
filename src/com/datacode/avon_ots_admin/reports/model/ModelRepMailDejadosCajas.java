package com.datacode.avon_ots_admin.reports.model;

public class ModelRepMailDejadosCajas {

	private String zona;
	private String campania;
	private String account;
	private String orden;
	private String codigo;
	private String descripcion;
	private String tipoItem;
	private int cantidad;
	private double montoCobrar;
	private int cont;
	private int quantityToCollect;
	private int collectedQuantity;

	public int getQuantityToCollect() {
		return quantityToCollect;
	}

	public void setQuantityToCollect(int quantityToCollect) {
		this.quantityToCollect = quantityToCollect;
	}

	public int getCollectedQuantity() {
		return collectedQuantity;
	}

	public void setCollectedQuantity(int collectedQuantity) {
		this.collectedQuantity = collectedQuantity;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getMontoCobrar() {
		return montoCobrar;
	}

	public void setMontoCobrar(double montoCobrar) {
		this.montoCobrar = montoCobrar;
	}

}
