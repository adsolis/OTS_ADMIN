package com.datacode.avon_ots_admin.reports.model;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ModelReporteConsultaEstatusOdenesRep {

	private int idOrden;
	private String latitud;
	private String longitud;
	private double montoCobrar;
	private String montoCobrarS;
	private String pagos;
	private String causaDevolucion;
	private String quienRecibe;
	private String quienInforma;
	private String tieneFoto;
	private List<ModelReporteConsultaEstatusOrdenesRepDetalle> items;
	private String zona;
	private String nombre;
	private java.io.InputStream foto;
	private byte[] fotoB;
	private ByteArrayInputStream fotoStream;
	private String estatusActual;
	private String fechaReparto;
	private String ultimoEstatus;
	private String claveOrden;

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public String getUltimoEstatus() {
		return ultimoEstatus;
	}

	public void setUltimoEstatus(String ultimoEstatus) {
		this.ultimoEstatus = ultimoEstatus;
	}

	public String getMontoCobrarS() {
		return montoCobrarS;
	}

	public void setMontoCobrarS(String montoCobrarS) {
		this.montoCobrarS = montoCobrarS;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public double getMontoCobrar() {
		return montoCobrar;
	}

	public void setMontoCobrar(double montoCobrar) {
		this.montoCobrar = montoCobrar;
	}

	public String getPagos() {
		return pagos;
	}

	public void setPagos(String pagos) {
		this.pagos = pagos;
	}

	public String getCausaDevolucion() {
		return causaDevolucion;
	}

	public void setCausaDevolucion(String causaDevolucion) {
		this.causaDevolucion = causaDevolucion;
	}

	public String getQuienRecibe() {
		return quienRecibe;
	}

	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	public String getQuienInforma() {
		return quienInforma;
	}

	public void setQuienInforma(String quienInforma) {
		this.quienInforma = quienInforma;
	}

	public String getTieneFoto() {
		return tieneFoto;
	}

	public void setTieneFoto(String tieneFoto) {
		this.tieneFoto = tieneFoto;
	}

	public List<ModelReporteConsultaEstatusOrdenesRepDetalle> getItems() {
		return items;
	}

	public void setItems(
			List<ModelReporteConsultaEstatusOrdenesRepDetalle> items) {
		this.items = items;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public java.io.InputStream getFoto() {
		return foto;
	}

	public void setFoto(java.io.InputStream foto) {
		this.foto = foto;
	}

	public byte[] getFotoB() {
		return fotoB;
	}

	public void setFotoB(byte[] fotoB) {
		this.fotoB = fotoB;
	}

	public ByteArrayInputStream getFotoStream() {
		return fotoStream;
	}

	public void setFotoStream(ByteArrayInputStream fotoStream) {
		this.fotoStream = fotoStream;
	}

	public String getEstatusActual() {
		return estatusActual;
	}

	public void setEstatusActual(String estatusActual) {
		this.estatusActual = estatusActual;
	}

	public String getFechaReparto() {
		return fechaReparto;
	}

	public void setFechaReparto(String fechaReparto) {
		this.fechaReparto = fechaReparto;
	}

}
