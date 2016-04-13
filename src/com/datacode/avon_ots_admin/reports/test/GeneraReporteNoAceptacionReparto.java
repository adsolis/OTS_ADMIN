/**
 * @author brenda.estrada
 * @since 02/02/2012
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.NoAceptacionReparto;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoDetalle;

/**
 * @author brenda.estrada
 * @since 02/02/2012
 */
public class GeneraReporteNoAceptacionReparto {

	public static List<NoAceptacionReparto> generaDatos() {
		List<NoAceptacionReparto> nar = new ArrayList<NoAceptacionReparto>();
		List<NoAceptacionRepartoDetalle> narData = new ArrayList<NoAceptacionRepartoDetalle>();
		NoAceptacionRepartoDetalle datas = null;
		// Datos del Detalle
		for (int i = 0; i < 100; i++) {
			 datas = new NoAceptacionRepartoDetalle(
					"1" + i, "Registro" + i, "Jose Alberto Hernandez Rodriguez"
							+ i, "Mi calle el no 4 Int 9, Colonia Lomas" + i,
					"Entre la calle dela escuela" + i, "Queretaro",
					 String.valueOf(1*i*i), String.valueOf(i*i), "RED" + i, "1" + i,
					"2" + i, "Referencia" + i, "10:0" + 1, "Robo " + 1);
			narData.add(datas);
		}
		// LLena los datos
		NoAceptacionReparto narEnc = new NoAceptacionReparto("Zona",
				"1", "50%", "5000", "2000", narData);
		nar.add(narEnc);

		return nar;
	}

}
