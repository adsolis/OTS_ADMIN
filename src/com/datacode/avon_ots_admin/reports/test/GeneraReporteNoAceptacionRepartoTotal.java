/**
 * 
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotal;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotalDetLibres;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotalDetRED;

/**
 * @author brenda.estrada
 *
 */
public class GeneraReporteNoAceptacionRepartoTotal {

	public static List<NoAceptacionRepartoTotal> generaDatos() {
		List<NoAceptacionRepartoTotal> nart = new ArrayList<NoAceptacionRepartoTotal>();
		List<NoAceptacionRepartoTotalDetRED> nar1Data = new ArrayList<NoAceptacionRepartoTotalDetRED>();
		List<NoAceptacionRepartoTotalDetLibres> nar2Data = new ArrayList<NoAceptacionRepartoTotalDetLibres>();
		
		NoAceptacionRepartoTotalDetRED datas1 = null;
		NoAceptacionRepartoTotalDetLibres datas2 = null;
		// Datos del Detalle1
		for (int i = 0; i < 100; i++) {
			 datas1 = new NoAceptacionRepartoTotalDetRED("1"+i, "20"+i, "1"+i, "$200"+i, "12"+i);
			nar1Data.add(datas1);
		}
		
		// Datos del Detalle1
		for (int i = 0; i < 100; i++) {
			 datas2 = new NoAceptacionRepartoTotalDetLibres("1"+i, "$200"+i, "$200"+i, "20%");
			nar2Data.add(datas2);
		}
		// LLena los datos
		NoAceptacionRepartoTotal nartEnc = new NoAceptacionRepartoTotal("Zona1", "1", "50%", "10", "10.00", "200", "500.00", "10%", "132587","120",nar1Data, nar2Data);
		
		
		nart.add(nartEnc);

		return nart;
	}
	
	
}
