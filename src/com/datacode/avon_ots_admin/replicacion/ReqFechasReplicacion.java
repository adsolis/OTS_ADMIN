package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReqFechasReplicacion extends ReqReplicacion {
	
	private List<ReplicacionTabla> replicacionTablas;

	public ReqFechasReplicacion(String idLDC,
			List<ReplicacionTabla> replicacionTablas) {
		super(idLDC);
		this.replicacionTablas = replicacionTablas;
	}

	public ReqFechasReplicacion() {
		super();
		
	}

	public ReqFechasReplicacion(String idLDC) {
		super(idLDC);
		
	}

	public List<ReplicacionTabla> getReplicacionTablas() {
		return replicacionTablas;
	}

	public void setReplicacionTablas(List<ReplicacionTabla> replicacionTablas) {
		this.replicacionTablas = replicacionTablas;
	}
	
	@JsonIgnore
	public String getTablasReplicacion() {
		/*StringBuilder res = new StringBuilder();
		for (ReplicacionTabla rep : replicacionTablas) {
			res.append(rep.getIdReplicacionTabla());
			res.append(",");
		}				
		return res.toString().substring(0, res.toString().length() - 2);*/
		
		StringBuilder res = new StringBuilder();
		int i = 1;
		for (ReplicacionTabla rep : replicacionTablas) {			
			res.append(rep.getIdReplicacionTabla());
			if (i < replicacionTablas.size()) {
				res.append(",");
			}
			i++;
		}				
		return res.toString();
	}
	
}
