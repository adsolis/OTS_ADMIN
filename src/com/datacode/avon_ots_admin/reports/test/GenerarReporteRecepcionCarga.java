package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepRecepcionCarga;
import com.datacode.avon_ots_admin.reports.model.TablaCajasMaltratadas;
import com.datacode.avon_ots_admin.reports.model.TablaCargaRecibida;
import com.datacode.avon_ots_admin.reports.model.TablaInfoCodFaltantes;
import com.datacode.avon_ots_admin.reports.model.TablaOrdenesXZona;
import com.datacode.avon_ots_admin.reports.model.TablaPapeleo;

public class GenerarReporteRecepcionCarga {
	
	public static List<ModelRepRecepcionCarga> generarReporteRecepcionCarga() {
		List<ModelRepRecepcionCarga> reportes = new ArrayList<ModelRepRecepcionCarga>();
		ModelRepRecepcionCarga reporte = new ModelRepRecepcionCarga();
		reporte.setCampania("2012 - 24");
		reporte.setCampaniaH("2012 - 25");
		reporte.setcTransportista("javier gallegos montoya");
		reporte.setFecha("10/07/2012");
		reporte.setFechaLlegadaProg("11/07/2012");
		reporte.setFechaLlegadaReal("12/07/2012");
		reporte.setHoraLlegadaProg("11:45");
		reporte.setHoraLlegadaReal("12:45");
		reporte.setOperador("octavio paz");
		reporte.setPoblacion("san juan del llanito jalisco");
		reporte.setPorteador("porteador centro norte");
		reporte.setZonaH("1967");
		List<TablaInfoCodFaltantes> codFaltantes = new ArrayList<TablaInfoCodFaltantes>();
		List<TablaOrdenesXZona> ordenes = new ArrayList<TablaOrdenesXZona>();
		List<TablaPapeleo> papeleos = new ArrayList<TablaPapeleo>();
		List<TablaCajasMaltratadas> cajas = new ArrayList<TablaCajasMaltratadas>();
		List<TablaCargaRecibida> cargasRec = new ArrayList<TablaCargaRecibida>();
		for (int i = 0; i <= 8; i++) {
			TablaInfoCodFaltantes codFaltante = new TablaInfoCodFaltantes();
			codFaltante.setCajasFaltantes(i * i);
			codFaltante.setObservaciones("obs" + i);
			codFaltante.setRegistro("afasd" + i);
			codFaltante.setTotalCajas(i * i);
			codFaltante.setTotalRecibido(i * i + i);
			codFaltantes.add(codFaltante);
			TablaOrdenesXZona orden = new TablaOrdenesXZona();
			orden.setCajasOrd(i * i);
			orden.setCajasPre(i * i);
			orden.setCancel(i*i);
			orden.setCods(i + i);
			orden.setOrdEnvGerZon(i+ i);
			orden.setCajasOrd(i * i + i);
			orden.setRelacion(i + i);
			orden.setZona("1967");
			orden.setTotCajasOrd(i*i);
			orden.setTotCajasPre(i+i);
			orden.setTotCancel(i+i);
			orden.setTotCods(i*i);
			orden.setTotOrdEnvGerZona(i+i*i);
			orden.setTotPremios(5148);
			orden.setTotRelacion(1548);
			ordenes.add(orden);
			TablaPapeleo papeleo = new TablaPapeleo();
			papeleo.setOtros(i * i);
			papeleo.setPanoram(i+1);
			papeleo.setPapGerenZonal(i+2);
			papeleo.setPapPortead(i+3);
			papeleo.setPortaf(i*2);
			papeleo.setTotCajas(i * i);
			papeleos.add(papeleo);
			TablaCajasMaltratadas caja = new TablaCajasMaltratadas();
			caja.setAveriada("X");
			caja.setDespegada("X");
			caja.setMojada("X");
			caja.setNoCaja(i + i * i + i);
			caja.setRegistro("sabe que se");
			cajas.add(caja);
			TablaCargaRecibida carga = new TablaCargaRecibida();
			carga.setCajasOrd(i * i);
			carga.setCajasPre(i * i);
			carga.setCods(i * i);
			carga.setOtros(i*i);
			carga.setPanoram(i*i);
			carga.setPapGerZonal(i*i+i);
			carga.setPapPorteador(i+i*i);
			carga.setPortaf(i+i+i*i);
			carga.setTotCajas(i * i * i);
			carga.setPremios(i + i * i);
			carga.setZona("90910");
			carga.setTotCajas(i*i);
			carga.setTotCajasOrd(i+i);
			carga.setTotCajasPre(i*i+i);
			cargasRec.add(carga);
		}
		reporte.setListaCajaRecibida(cargasRec);
		reporte.setListaCajasMaltratadas(cajas);
		reporte.setListaCodFaltantes(codFaltantes);
		reporte.setListaOrdenesFaltantes(ordenes);
		reporte.setListaPapeleo(papeleos);
		reportes.add(reporte);
		return reportes;
	}
}
