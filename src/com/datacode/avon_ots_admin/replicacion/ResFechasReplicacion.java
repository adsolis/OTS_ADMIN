package com.datacode.avon_ots_admin.replicacion;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResFechasReplicacion extends ResReplicacion {
	private List<ReplicacionTabla> replicacionTablas;

	public ResFechasReplicacion(String idLDC,
			List<ReplicacionTabla> replicacionTablas) {
		super(idLDC);
		this.replicacionTablas = replicacionTablas;
	}

	public ResFechasReplicacion() {
		super();

	}

	public ResFechasReplicacion(String idLDC) {
		super(idLDC);
	}

	public List<ReplicacionTabla> getReplicacionTablas() {
		return replicacionTablas;
	}

	public void setReplicacionTablas(List<ReplicacionTabla> replicacionTablas) {
		this.replicacionTablas = replicacionTablas;
	}

	@JsonIgnore
	public String getFechasTablasReplicacion() {
		StringBuilder res = new StringBuilder("");
		for (ReplicacionTabla rep : replicacionTablas) {
			res.append(rep.getIdReplicacionTabla());
			res.append("|");
			res.append(rep.getFechaActualizacionTabla());
			res.append(",");
		}
		if (res.length() > 0) {
			return res.toString().substring(0, res.toString().length() - 1);
		} else {
			return "";
		}
	}

}
