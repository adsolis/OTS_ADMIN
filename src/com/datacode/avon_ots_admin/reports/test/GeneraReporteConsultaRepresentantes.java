package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepConsultaRepresentantes;

public class GeneraReporteConsultaRepresentantes {

	public static List<ModelRepConsultaRepresentantes> generaRepConsultaRep() {
		List<ModelRepConsultaRepresentantes> listaRep = new ArrayList<ModelRepConsultaRepresentantes>();
		for (int i = 1; i <= 20; i++) {
			ModelRepConsultaRepresentantes rep = new ModelRepConsultaRepresentantes();
			rep.setBancoFolio("123,34" + i);
			rep.setCampania("12- 2012");
			rep.setCausaDevolucion("no llego a tiempo");
			rep.setDireccionRepresentante("juana villaseñor 125 col. las isurgentes");
			rep.setEntregadoEn("ventanilla");
			rep.setFechaDevolucion(new Date());
			rep.setFechaEntrega(new Date());
			rep.setFechaPago(new Date());
			rep.setGps("12,221,212,779,797");
			rep.setIdRepresentante(23);
			rep.setMontoPagado(5879.25);
			rep.setTipoPago("Ficha");
			rep.setNombreRepresentante("Francisco Javier de Jesus Gallegos Montoya");
			rep.setOrder("333");
			listaRep.add(rep);
		}
		return listaRep;
	}
}
