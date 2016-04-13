/**
 * @author jose.ponce
 * @since 03/02/2012
 */
package com.datacode.avon_ots_admin.reports.model;

/**
 * @author jose.ponce
 *
 */
public class ModelRepOrdEntVentanilla {
	private String campania;
	private String orden;
	private int total_cajas;
	private int total_unitarios;
	private int total_premios;
	private String estatus_reparto;
	private String causa_devolucion;
	private String registro;
	private String nombre;
	private String domicilio;
	private String zona;
	private String codigo_barras;
	private String tipo_item;
	private String estatus_item;
	//Detalle de pago
	private String banco;
	private String tipo_pago;
	private double monto_pagado;
	private String folios;
	private String fecha_pago;
	
	/*********************************************************************************/
	/*********************************ENCAPSULAMIENTO*********************************/
	/*********************************************************************************/
	
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public int getTotal_cajas() {
		return total_cajas;
	}
	public void setTotal_cajas(int total_cajas) {
		this.total_cajas = total_cajas;
	}
	public int getTotal_unitarios() {
		return total_unitarios;
	}
	public void setTotal_unitarios(int total_unitarios) {
		this.total_unitarios = total_unitarios;
	}
	public int getTotal_premios() {
		return total_premios;
	}
	public void setTotal_premios(int total_premios) {
		this.total_premios = total_premios;
	}
	public String getEstatus_reparto() {
		return estatus_reparto;
	}
	public void setEstatus_reparto(String estatus_reparto) {
		this.estatus_reparto = estatus_reparto;
	}
	public String getCausa_devolucion() {
		return causa_devolucion;
	}
	public void setCausa_devolucion(String causa_devolucion) {
		this.causa_devolucion = causa_devolucion;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getCodigo_barras() {
		return codigo_barras;
	}
	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}
	public String getTipo_item() {
		return tipo_item;
	}
	public void setTipo_item(String tipo_item) {
		this.tipo_item = tipo_item;
	}
	public String getEstatus_item() {
		return estatus_item;
	}
	public void setEstatus_item(String estatus_item) {
		this.estatus_item = estatus_item;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipo_pago() {
		return tipo_pago;
	}
	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}
	public String getFolios() {
		return folios;
	}
	public void setFolios(String folios) {
		this.folios = folios;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
}
