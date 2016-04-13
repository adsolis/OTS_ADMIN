/**
 * @author brenda.estrada
 * @since 24/01/2012
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.model.Bancos;
import com.datacode.avon_ots_admin.model.Campania;
import com.datacode.avon_ots_admin.model.Division;
import com.datacode.avon_ots_admin.model.EstadoOrden;
import com.datacode.avon_ots_admin.model.Orden;
import com.datacode.avon_ots_admin.model.Premios;
import com.datacode.avon_ots_admin.model.ProgramacionReparto;
import com.datacode.avon_ots_admin.model.RazonesDevolucion;
import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.model.TipoLiquidacion;
import com.datacode.avon_ots_admin.model.TipoPago;
import com.datacode.avon_ots_admin.model.TipoSiniestro;
import com.datacode.avon_ots_admin.model.Unitarios;
import com.datacode.avon_ots_admin.model.Zona;

/**
 * @author brenda.estrada
 * @since 24/01/2012
 */
public class GeneraGridBancos {
	/**
	 * 
	 * @author brenda.estrada
	 * @since 24/01/2012
	 * @return
	 */
	public static List<Bancos> generaGridBancos(){
			
		 List<Bancos> bancos = new ArrayList<Bancos>();
		 for(int i=0;i<10;i++){
			 Bancos banco = new Bancos(i, "CBE", "na", "12/01/2011", "", "YO");
			 bancos.add(banco);
		 }
		 return bancos;
	}
	
	/**
	 * Obtiene las campanias existentes y las guarda en List<Campania>
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @return List<Campania>
	 */
	public static List<Campania> generaGridCampanias(){
		
		 List<Campania> campanias = new ArrayList<Campania>();
		 for(int i=0;i<5;i++){
			 Campania campania = new Campania(i, i, i, "25/12/2011", "31/01/2012",  "12/01/2011",  "25/01/2011", "B.ESTRADA"+i, i, "22"+i);
			 campanias.add(campania);
		 }
		 return campanias;
	}
	
	/**
	 * Obtiene las Divisiones existentes y las guarda en List[Division]
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @return List[Division]
	 */
	public static List<Division> generaGridDivisiones(){
		
		 List<Division> divisiones = new ArrayList<Division>();
		 for(int i=0;i<5;i++){
			 Division division = new Division(i, "Division"+i, "Area"+i, "Administrador"+1, "Asistente"+i, "12/01/2011","25/01/2011", "B.ESTRADA"+i);
			 divisiones.add(division);
		 }
		 return divisiones;
	}
	
	/**
	 * Obtiene las Zonas existentes y las guarda en List[Zona]
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @return List[Zona]
	 */
	public static List<Zona> generaGridZonas(){
		
		 List<Zona> zonas = new ArrayList<Zona>();
		 for(int i=0;i<5;i++){
			 Zona zona = new Zona(""+i, "Zona", "201"+1, ""+1, "12/01/2011","25/01/2011", "B.ESTRADA"+1, "1", "LDC_DC", "9", "Agata", "LOCAL"+1, "GErenteZona"+1);
			 zonas.add(zona);
		 }
		 return zonas;
	}
	
	/**
	 * Obtiene las Representantes existentes y las guarda en List[Representante]
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @return List[Representante]
	 */
	public static List<Representante> generaGridRepresentantes(){
		 List<Representante> representantes = new ArrayList<Representante>();
		 byte b = 1;
		 for(int i=0;i<5;i++){
			 Representante representante = new Representante(i, 5, "1977"+1, "11011"+1, 
					 "MAria Vega"+1, "conocido"+1, "QRO","Activo", 1, 
					 1.1, 1.1, "442000000"+1, "0000"+1, b, b, "25/01/2011", "", "25/01/2011", "B.ESTRADA",
					 "Si", "Si", "R001", false);
			 representantes.add(representante);
		 }
		 return representantes;
	}

	/**
	 * Obtiene los TipoPago existentes y las guarda en List[TipoPago]
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @return List[TipoPago]
	 */
	public static List<TipoPago> generaGridTipoPago(){
		 List<TipoPago> arrData = new ArrayList<TipoPago>();
		 for(int i=0;i<5;i++){
			 TipoPago tipoPago = new TipoPago(i, "EFECTIVO"+i, "25/01/2011", "B.ESTRADA");
			 arrData.add(tipoPago);
		 }
		 return arrData;
	}
	
	public static List<Orden> generaGridOrden(){
		 List<Orden> arrData = new ArrayList<Orden>();
		 for(int i=0;i<100;i++){
			 Orden obj = new Orden(i, i, i, ""+i, "25/01/2011", i, "Orden"+i, "25/01/2011", "B.ESTRADA"+i,
					 i, i, "11011"+i, "LDC_CD", "201"+i, ""+i, "Ruta"+i, "Si");
			 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	
	public static List<RazonesDevolucion> generaGridRazonDevolucion(){
		 List<RazonesDevolucion> arrData = new ArrayList<RazonesDevolucion>();
		 for(int i=0;i<5;i++){
			 RazonesDevolucion obj = new RazonesDevolucion(i, "OTRAS"+i, "25/01/2011", "25/01/2011", "B.ESTRADA"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	
	public static List<TipoLiquidacion> generaGridTipoLiquidacion(){
		 List<TipoLiquidacion> arrData = new ArrayList<TipoLiquidacion>();
		 for(int i=0;i<100;i++){
			 TipoLiquidacion obj = new TipoLiquidacion(i, "Tipo Liquidacion"+i, "CVELIQ"+i, "25/01/2011", "25/01/2011", "B.ESTRADA"+i);
			 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	
	public static List<EstadoOrden> generaGridEstadoOrden(){
		 List<EstadoOrden> arrData = new ArrayList<EstadoOrden>();
		 for(int i=0;i<100;i++){
			 EstadoOrden obj = new EstadoOrden(i, "TIPOLDV"+i, "EDOORDEN"+i, "25/01/2011", "25/01/2011", "B.ESTRADA"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	
	public static List<TipoSiniestro> generaGridTipoSiniestro(){
		 List<TipoSiniestro> arrData = new ArrayList<TipoSiniestro>();
		 for(int i=0;i<100;i++){
			 TipoSiniestro obj = new TipoSiniestro(i, "CVESINIESTRO"+i, "DESCSINIESTRO"+i, "25/01/2011", "25/01/2011", "B.ESTRADA"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	public static List<Unitarios> generaGridUnitarios(){
		 List<Unitarios> arrData = new ArrayList<Unitarios>();
		 for(int i=0;i<100;i++){
			 Unitarios obj = new Unitarios(0, "UNITARIO", "ACCESORIO"+i, "NA", "707100"+i, "NA", "9091"+i, 
					 "NA", "NA", "NA", "NA", "NA", ""+1, "201"+1, "25/01/2011", "B.ESTRADA"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	public static List<Premios> generaGridPremios(){
		 List<Premios> arrData = new ArrayList<Premios>();
		 for(int i=0;i<100;i++){
			 Premios obj = new Premios(0, "878654"+i, "DECORADO"+i, "NA", "NA", "UNITARIO", "5976000"+i,  ""+1, "201"+1, "NA", "25/01/2011", "B.ESTRADA"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	public static List<ProgramacionReparto> generaGridProgramacionReparto(){
		 List<ProgramacionReparto> arrData = new ArrayList<ProgramacionReparto>();
		 for(int i=0;i<100;i++){
			 ProgramacionReparto obj = new ProgramacionReparto(i, "n"+i, "201"+i, "mail@mail.com", "25/01/2011", "30/01/2011", "31/01/2012", "02/02/2012", 
					 "29/02/2012", "01/03/2012",  "25/01/2011", i, "zona"+i, i, ""+i, i, "LDC"+i, i, "Division"+i, "2012", "B.ESTRADA"+i);
			 
			 arrData.add(obj);
		 }
		 return arrData;
	}
}
