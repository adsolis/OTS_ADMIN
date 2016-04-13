package com.datacode.avon_ots_admin.reports.model;

import java.io.ByteArrayInputStream;
import java.util.Date;

public class ModelRepConsultaRepresentantes {
	
	private String campania;
	private String order;
	private String gps;
	private String entregadoEn;
	private Date fechaEntrega;
	private String tipoPago;
	private String bancoFolio;
	private Date fechaPago;
	private double montoPagado;
	private Date fechaDevolucion;
	private String causaDevolucion;
	private int idRepresentante;
	private String nombreRepresentante;
	private String direccionRepresentante;
	private ByteArrayInputStream fotoDomicilio;
	private String registroRepresentante;
	
	
	//falta la imagen
	public String getCampania() {
		return campania;
	}
	public int getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	public String getDireccionRepresentante() {
		return direccionRepresentante;
	}
	public void setDireccionRepresentante(String direccionRepresentante) {
		this.direccionRepresentante = direccionRepresentante;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getEntregadoEn() {
		return entregadoEn;
	}
	public void setEntregadoEn(String entregadoEn) {
		this.entregadoEn = entregadoEn;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getBancoFolio() {
		return bancoFolio;
	}
	public void setBancoFolio(String bancoFolio) {
		this.bancoFolio = bancoFolio;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public double getMontoPagado() {
		return montoPagado;
	}
	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getCausaDevolucion() {
		return causaDevolucion;
	}
	public void setCausaDevolucion(String causaDevolucion) {
		this.causaDevolucion = causaDevolucion;
	}
	/**
	 * @return the fotoDomicilio
	 */
	public ByteArrayInputStream getFotoDomicilio() {
		return fotoDomicilio;
	}
	/**
	 * @param fotoDomicilio the fotoDomicilio to set
	 */
	public void setFotoDomicilio(ByteArrayInputStream fotoDomicilio) {
		this.fotoDomicilio = fotoDomicilio;
	}
	/**
	 * @return the registroRepresentante
	 */
	public String getRegistroRepresentante() {
		return registroRepresentante;
	}
	/**
	 * @param registroRepresentante the registroRepresentante to set
	 */
	public void setRegistroRepresentante(String registroRepresentante) {
		this.registroRepresentante = registroRepresentante;
	}
}
