package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

public class ReqReplicacionUploadZip extends ReqReplicacion {
	private long checkSum;
	private String nombreArchivo;
	private Replicacion replicacion;
	private List<ReplicacionDetalle> replicacionDetalles;
	private byte[] secretKey;
	public ReqReplicacionUploadZip(String LDC, long checkSum,
			String nombreArchivo, List<ReplicacionDetalle> replicacionDetalles) {
		super(LDC);
		this.checkSum = checkSum;
		this.nombreArchivo = nombreArchivo;
		this.replicacionDetalles = replicacionDetalles;
	}
	
	
	public ReqReplicacionUploadZip(String LDC, long checkSum,
			String nombreArchivo, Replicacion replicacion,
			List<ReplicacionDetalle> replicacionDetalles) {
		super(LDC);
		this.checkSum = checkSum;
		this.nombreArchivo = nombreArchivo;
		this.replicacion = replicacion;
		this.replicacionDetalles = replicacionDetalles;
	}


	public ReqReplicacionUploadZip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReqReplicacionUploadZip(String LDC) {
		super(LDC);
		// TODO Auto-generated constructor stub
	}
	public long getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(long checkSum) {
		this.checkSum = checkSum;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public List<ReplicacionDetalle> getReplicacionDetalles() {
		return replicacionDetalles;
	}
	public void setReplicacionDetalles(List<ReplicacionDetalle> replicacionDetalles) {
		this.replicacionDetalles = replicacionDetalles;
	}
	public Replicacion getReplicacion() {
		return replicacion;
	}
	public void setReplicacion(Replicacion replicacion) {
		this.replicacion = replicacion;
	}


	public byte[] getSecretKey() {
		return secretKey;
	}


	public void setSecretKey(byte[] secretKey) {
		this.secretKey = secretKey;
	}



	
	
	
}
