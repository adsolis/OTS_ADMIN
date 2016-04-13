/**
 *
 *  @since 14/02/2012
 *
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelTablaAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelTablaAnalisisEfectivoLiquidacion;


/**
 * @author jessica.leon
 *
 */
public class GenerarReporteAnalisisEfectivo {
	
	public static List<ModelAnalisisEfectivo> llenarReporte(){
		
		List<ModelAnalisisEfectivo> listaAnalisis = new ArrayList<ModelAnalisisEfectivo>();
		ModelAnalisisEfectivo encabezado = new ModelAnalisisEfectivo();
		List<ModelTablaAnalisisEfectivo> detalles1 = new ArrayList<ModelTablaAnalisisEfectivo>();
		List<ModelTablaAnalisisEfectivoLiquidacion> detalles2 = new ArrayList<ModelTablaAnalisisEfectivoLiquidacion>();
		
		ModelTablaAnalisisEfectivo detalle1 = null;
		ModelTablaAnalisisEfectivoLiquidacion detalle2 = null;
		encabezado.setPlaza("4789896");
		encabezado.setPorteador("Jessica León Ruiz");
		encabezado.setZona("728");
		encabezado.setCampania("12 - 2012");
		
		for(int i=0;i<20;i++){
			
			detalle1 = new ModelTablaAnalisisEfectivo();
			detalle1.setRuta("01");
			detalle1.setBancoDeposito("Banamex");
			detalle1.setDepositoGlobal(i*458.12);
			detalle1.setEfectivoRecolectado(i*968.2);
			detalle1.setFechaRecepcionEfectivo(new Date().toString());
			
			detalle2 = new ModelTablaAnalisisEfectivoLiquidacion();
			detalle2.setBanco("Bancomer");
			detalle2.setBodegaSub("14");
			detalle2.setFecha(new Date().toString());
			detalle2.setImporte(i*678.07);
			
			detalles1.add(detalle1);
			detalles2.add(detalle2);
		}
		
		encabezado.setListaAnalisisEfectivo(detalles1);
		encabezado.setListaAnalisisEfectivoLiquidacion(detalles2);
		
		listaAnalisis.add(encabezado);
		return listaAnalisis;
	}
}
