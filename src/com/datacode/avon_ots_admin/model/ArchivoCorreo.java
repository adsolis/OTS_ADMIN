package com.datacode.avon_ots_admin.model;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ArchivoCorreo {

	private String nombreArchivo;
	private ByteArrayOutputStream bytes;
	private String type;
	private List<Integer> listaItems;
	private int idCampania;
	private String nombreBorrar;

	public String getNombreBorrar() {
		return nombreBorrar;
	}

	public void setNombreBorrar(String nombreBorrar) {
		this.nombreBorrar = nombreBorrar;
	}

	public int getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}

	public List<Integer> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<Integer> listaItems) {
		this.listaItems = listaItems;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public ByteArrayOutputStream getBytes() {
		return bytes;
	}

	public void setBytes(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
