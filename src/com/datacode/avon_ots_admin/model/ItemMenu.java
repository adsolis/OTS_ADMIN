package com.datacode.avon_ots_admin.model;

public class ItemMenu {

	private String nombreMenu;
	private String linkMenu;
	private String metodoMenu;
	private Boolean mostrar;
	
	public Boolean getMostrar() {
		return mostrar;
	}
	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}
	public String getNombreMenu() {
		return nombreMenu;
	}
	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}
	public String getLinkMenu() {
		return linkMenu;
	}
	public void setLinkMenu(String linkMenu) {
		this.linkMenu = linkMenu;
	}
	public String getMetodoMenu() {
		return metodoMenu;
	}
	public void setMetodoMenu(String metodoMenu) {
		this.metodoMenu = metodoMenu;
	}
}
