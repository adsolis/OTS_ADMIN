/**
 * @author jose.ponce
 * @since 03/02/2012
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.*;

/**
 * @author jose.ponce
 *
 */
public class GeneraReporteEntregaVentanilla {
	public static List<ModelRepPrinEntVent> generaReporteVentanilla(){
		List<ModelRepPrinEntVent> reporte=new ArrayList<ModelRepPrinEntVent>();
		
		ModelRepPrinEntVent cabeceraReporte=new ModelRepPrinEntVent();
		cabeceraReporte.setAccount("123456789");
		cabeceraReporte.setDirRepre("Alamos 76");
		cabeceraReporte.setNomRepre("prueba report");
		cabeceraReporte.setZonRepre("159");
		
		List<ModelRepOrdEntVentanilla> detalleReporte=new ArrayList<ModelRepOrdEntVentanilla>();
		for(int i=0; i < 20; i++){
			ModelRepOrdEntVentanilla tabla=new ModelRepOrdEntVentanilla();
			tabla.setCampania("00-" + i);
			tabla.setOrden("01-"+ i);
			tabla.setTotal_cajas(i);
			tabla.setTotal_unitarios(i);
			tabla.setTotal_premios(i);
			tabla.setEstatus_reparto("test");
			tabla.setCausa_devolucion("prueba");
			tabla.setRegistro("123456789");
			tabla.setNombre("prueba report");
			tabla.setDomicilio("Alamos 76");
			tabla.setZona("159");
			tabla.setCodigo_barras("1"+ i +"2");
			tabla.setTipo_item("CajaPremioUnitario");
			tabla.setEstatus_item("test");
			tabla.setBanco("bancote"+ i);
			tabla.setTipo_pago("banco");
			tabla.setMonto_pagado(0.0);
			tabla.setFolios("00000-" + i);
			tabla.setFecha_pago("01/01/2012");
			
			detalleReporte.add(tabla);
		}
		cabeceraReporte.setListaDetalle(detalleReporte);
		reporte.add(cabeceraReporte);
		
		return reporte;
	}
}
