/**
 * @author jose.ponce
 * @since 03/02/2012
 */
package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

/**
 * @author jose.ponce
 *
 */
public class ModelRepPrinEntVent {
	//ENCABEZADO
	private String account;
	private String nomRepre;
	private String dirRepre;
	private String zonRepre;
	private String ordenRepre;
	//DETALLE
	private List<ModelRepOrdEntVentanilla> listaDetalle;
	
	/***********************ENCAPSULAMIENTO***************************************/
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNomRepre() {
		return nomRepre;
	}
	public void setNomRepre(String nomRepre) {
		this.nomRepre = nomRepre;
	}
	public String getDirRepre() {
		return dirRepre;
	}
	public void setDirRepre(String dirRepre) {
		this.dirRepre = dirRepre;
	}
	public String getZonRepre() {
		return zonRepre;
	}
	public void setZonRepre(String zonRepre) {
		this.zonRepre = zonRepre;
	}
	private String getOrdenRepre() {
		return ordenRepre;
	}
	private void setOrdenRepre(String ordenRepre) {
		this.ordenRepre = ordenRepre;
	}
	public List<ModelRepOrdEntVentanilla> getListaDetalle() {
		return listaDetalle;
	}
	public void setListaDetalle(List<ModelRepOrdEntVentanilla> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
}
