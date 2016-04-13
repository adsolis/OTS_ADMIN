package com.datacode.avon_ots_admin.replicacion;

public class ResReplicacion {
	private String  idLDC;
	private String clave;
	private String mensaje;
	
	public ResReplicacion(String idLDC) {
		super();
		this.idLDC = idLDC;
	}

	public ResReplicacion() {
		super();
	}

	public String getIdLDC() {
		return idLDC;
	}

	public void setIdLDC(String idLDC) {
		this.idLDC = idLDC;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	

	
	
	
}
