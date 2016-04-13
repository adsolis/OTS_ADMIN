package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo;
import com.datacode.avon_ots_admin.reports.model.LiquidacionesRep;
import com.datacode.avon_ots_admin.reports.model.ModelRepSalidaCamioneta;


public class generaDatosReporteSalidaCamioneta {

	public static List<ModelRepSalidaCamioneta> generaDatos()
	{
		List<ModelRepSalidaCamioneta> datos= new ArrayList<ModelRepSalidaCamioneta>();
		ModelRepSalidaCamioneta salida= new ModelRepSalidaCamioneta();
		salida.setSistema("Order Tracking System");
		salida.setCampaniaH("02-2012");
		salida.setZona("909");
		salida.setReporte("CONTROL DE SALIDA DE CAMIONETA");
		salida.setCajas("cajas");
		salida.setCampania("2012 - 24");
		salida.setHrPrimerVisita(String.valueOf(new Date()));
		salida.setHrRegresoBodega(String.valueOf(new Date()));
		salida.setHrSalidaReparto(String.valueOf(new Date()));
		salida.setHrUltimaVisita(String.valueOf(new Date()));
		salida.setKmPrimerVisita(52520);
		salida.setKmRegresoBodega(54789);
		salida.setKmSalidaReparto(784589);
		salida.setKmUltimaVisita(45789);
		salida.setNombreAyudante("javier gallegos montoya");
		salida.setNombreChofer("chofer jajaja ferkjuh lkjh l");
		salida.setOrdenes("ordenes");
		salida.setPremios("premios");
		List<LiquidacionesRep> liquidaciones = new ArrayList<LiquidacionesRep>();
		for(int i=0;i<8;i++)
		{
			LiquidacionesRep liq = new LiquidacionesRep();
			liq.setCajas(23);
			liq.setCods(34.8);
			liq.setConcepto("concepto"+i);
			liq.setPremios(i*i);
			liq.setUnitarios(i*2);
			liq.setRecibio("javier gallegos montoya");
			liquidaciones.add(liq);
			}
		salida.setLiquidaciones(liquidaciones);
		List<DesgloceEfectivo> desMoneda = new ArrayList<DesgloceEfectivo>();
		for(int i=0;i<4;i++)
		{
			DesgloceEfectivo mon = new DesgloceEfectivo();
			mon.setCantidad(i);
			mon.setDenominacion("$"+i+".00");
			mon.setSubTotal(i*9.2);
			desMoneda.add(mon);
			}
		salida.setDesgloceMoneda(desMoneda);
		List<DesgloceEfectivo> desBil = new ArrayList<DesgloceEfectivo>();
		for(int i=0;i<5;i++)
		{
			DesgloceEfectivo mon = new DesgloceEfectivo();
			mon.setCantidad(i);
			mon.setDenominacion("$"+i+".00");
			mon.setSubTotal(i*3.5);
			desBil.add(mon);
			}
		salida.setDesgloceBilletes(desBil);
		datos.add(salida);
		return datos;
	}
}
