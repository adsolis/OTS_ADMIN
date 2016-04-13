package com.datacode.avon_ots_admin.model;

import java.util.List;

public class MenuH {

	private String nombreMenu;
	private List<ItemMenu> submenus;
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
	public List<ItemMenu> getSubmenus() {
		return submenus;
	}
	public void setSubmenus(List<ItemMenu> submenus) {
		this.submenus = submenus;
	}
}
