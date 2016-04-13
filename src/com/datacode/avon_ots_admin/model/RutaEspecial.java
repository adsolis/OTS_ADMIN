package com.datacode.avon_ots_admin.model;

import java.util.Date;

public class RutaEspecial {
	private String claveRutaEspecial;
	private int idRutaEspecial;
	private String registro;
	private String nombre;
	private String claveOrden;
	private int idOrden;
	private int numOrdenes;
	private String campaniaRelacionada;
	private Date fechaCreacion;
	private String fechaCreacionS;
	private String blocked;

	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public int getNumOrdenes() {
		return numOrdenes;
	}

	public void setNumOrdenes(int numOrdenes) {
		this.numOrdenes = numOrdenes;
	}

	public String getCampaniaRelacionada() {
		return campaniaRelacionada;
	}

	public void setCampaniaRelacionada(String campaniaRelacionada) {
		this.campaniaRelacionada = campaniaRelacionada;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaCreacionS() {
		return fechaCreacionS;
	}

	public void setFechaCreacionS(String fechaCreacionS) {
		this.fechaCreacionS = fechaCreacionS;
	}

	public String getClaveRutaEspecial() {
		return claveRutaEspecial;
	}

	public void setClaveRutaEspecial(String claveRutaEspecial) {
		this.claveRutaEspecial = claveRutaEspecial;
	}

	public int getIdRutaEspecial() {
		return idRutaEspecial;
	}

	public void setIdRutaEspecial(int idRutaEspecial) {
		this.idRutaEspecial = idRutaEspecial;
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

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}
}
