package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen;

public class GeneraDatosItemsNoEntregados {

	public static List<ModelReporteItemsNoEntregadosEnAlmacen> generaDatos() {
		List<ModelReporteItemsNoEntregadosEnAlmacen> regs = new ArrayList<ModelReporteItemsNoEntregadosEnAlmacen>();
		for (int i = 0; i <= 10; i++) {
			ModelReporteItemsNoEntregadosEnAlmacen r = new ModelReporteItemsNoEntregadosEnAlmacen();
			r.setCodigoItem("15487845");
			r.setDescItem("BULTO DE EMPAQUE");
			r.setFechaHoraDevolucion(new Date());
			r.setFechaHoraDevolucionS("15/02/2014");
			r.setNombreChofer("Armando");
			r.setNombreRepresentante("FRANCISCO JAVIER GALLEGOS MONTOYA");
			r.setNumeroOrden("1548");
			r.setRegistro("15457856");
			r.setRuta("86");
			r.setZona("808");
			r.setCampania("042014");
			regs.add(r);
		}
		return regs;
	}
}
