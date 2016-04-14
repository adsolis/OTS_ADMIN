package com.datacode.avon_ots_admin.model;

public class ModelRemito {
	
	private long idRemito;
	
	private String registro;
	
	private String nombre;
	
	private int cantidadRecolectar;
	
	private boolean status;

	public long getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(long idRemito) {
		this.idRemito = idRemito;
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

	public int getCantidadRecolectar() {
		return cantidadRecolectar;
	}

	public void setCantidadRecolectar(int cantidadRecolectar) {
		this.cantidadRecolectar = cantidadRecolectar;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
