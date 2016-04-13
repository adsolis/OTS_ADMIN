package com.datacode.avon_ots_admin.utils;

public class ExcepcionNegocio extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionNegocio(String mensaje, Throwable excepcion) {
		this.mensaje = mensaje;
		this.excepcion = excepcion;
	}

	private String mensaje = "";

	private Throwable excepcion = null;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Throwable getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(Throwable excepcion) {
		this.excepcion = excepcion;
	}

}
