/**
 * @author jose.ponce
 * @since 30/12/2011
 */
package com.datacode.avon_ots_admin.cu001;

/**
 * @author jose.ponce
 *
 */
/**
 * Clases para prueba de exportacion
 */
public class Tabla {
	 
	private perInfo[] perInfoAll = new perInfo[]{
	new perInfo(101, "felpas", "9891444444"),
	new perInfo(102, "alfred", "9911666666"),
	new perInfo(103, "roger", "9313888888"),
	new perInfo(104, "pena mario", "9911222222"),
	new perInfo(105, "iiiop", "9313999999"),
	};

	public perInfo[] getperInfoAll() {
	return perInfoAll;
	}

	public class perInfo {
	int id;
	String name;
	String phone;

	public perInfo(int id, String name, String phone) {
	this.id = id;
	this.name = name;
	this.phone = phone;
	
	}

	public int getid() {
	return id;
	}

	public String getname() {
	return name;
	}

	public String getphone() {
	return phone;
	}
	}
}