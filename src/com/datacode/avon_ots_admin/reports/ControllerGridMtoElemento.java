/**
 * 
 */
package com.datacode.avon_ots_admin.reports;

import java.util.List;

import com.datacode.avon_ots_admin.model.AsignacionRutaChofer;
import com.datacode.avon_ots_admin.model.AsignacionUnidadReparto;
import com.datacode.avon_ots_admin.model.Denominaciones;
import com.datacode.avon_ots_admin.model.Destinatario;
import com.datacode.avon_ots_admin.model.DispositivoMovil;
import com.datacode.avon_ots_admin.model.Empleado;
import com.datacode.avon_ots_admin.model.Informante;
import com.datacode.avon_ots_admin.model.LineaTransporte;
import com.datacode.avon_ots_admin.model.Marca;
import com.datacode.avon_ots_admin.model.Modelo;
import com.datacode.avon_ots_admin.model.ParametrosLDC;
import com.datacode.avon_ots_admin.model.Perfil;
import com.datacode.avon_ots_admin.model.Puesto;
import com.datacode.avon_ots_admin.model.Reporte;
import com.datacode.avon_ots_admin.model.ReporteTipoDestinatario;
import com.datacode.avon_ots_admin.model.RepresentantesPorRuta;
import com.datacode.avon_ots_admin.model.RutaAlterna;
import com.datacode.avon_ots_admin.model.Rutas;
import com.datacode.avon_ots_admin.model.SubBodegaAlmacen;
import com.datacode.avon_ots_admin.model.TipoDestinatario;
import com.datacode.avon_ots_admin.model.TipoRuta;
import com.datacode.avon_ots_admin.model.UnidadReparto;
import com.datacode.avon_ots_admin.model.Usuario;

/**
 * @author brenda.estrada
 *
 */
public class ControllerGridMtoElemento {
	JasperGenerator jasper = new JasperGenerator();
	
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 1
	 * @param lstUnidad
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC1(List<UnidadReparto> lstUnidad, String formato){
		//
		jasper.generaGridMtoElemento(lstUnidad, formato, "UnidadReparto", "\\GridMtoUnidadReparto.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 2
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC2(List<Modelo> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Modelo", "\\GridMtoModelo.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 3
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC3(List<Marca> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Marca", "\\GridMtoMarca.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 4
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC4(List<AsignacionUnidadReparto> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "AsignacionUnidadReparto", "\\GridMtoAsignaUnidadReparto.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 5
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC5(List<Rutas> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Rutas", "\\GridMtoRutas.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 6
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC6(List<TipoRuta> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "TipoRutas", "\\GridMtoTipoRutas.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 7
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC7(List<RepresentantesPorRuta> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "RepresentantePorRutas", "\\GridMtoRepresentanteRutas.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 8
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC8(List<AsignacionRutaChofer> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "AsignaciónRutasChofer", "\\GridMtoAsignaRutasChofer.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 9
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC9(List<DispositivoMovil> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "DispositivoMovil", "\\GridMtoDispositivoMovil.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 10
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC10(List<Empleado> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Empleado", "\\GridMtoEmpleadol.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 11
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC11(List<Puesto> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Puesto", "\\GridMtoPuesto.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 12
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC12(List<LineaTransporte> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "LineaTransporte", "\\GridMtoLineaTransporte.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 13
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC13(List<Informante> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Informante", "\\GridMtoInformante.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 14
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC14(List<SubBodegaAlmacen> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "SubbodegaAlmacen", "\\GridMtoSubbodega.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 15
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC15(List<Destinatario> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Destinatario", "\\GridMtoDestinatario.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 16
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC16(List<TipoDestinatario> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "TipoDestinatario", "\\GridMtoTipoDestinatario.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 17
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC17(List<ReporteTipoDestinatario> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "ReporteTipoDestinatario", "\\GridMtoReporteTipoDestinatario.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 18
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC18(List<Reporte> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Reporte", "\\GridMtoReporte.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 19
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC19(List<ParametrosLDC> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "LDC", "\\GridMtoLDC.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 20
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC20(List<Usuario> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Usuario", "\\GridMtoUsuario.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 21
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC21(List<Perfil> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Perfil", "\\GridMtoPerfil.jasper");
	}
	/**
	 * Recibe valores para generar archivo de Excel o PDF del Catalogo 22
	 * @param lstData
	 * @param formato
	 * @author brenda.estrada
	 */
	public void exportC22(List<Denominaciones> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Denominaciones", "\\GridMtoDenominaciones.jasper");
	}
	
	public void exportC23(List<RutaAlterna> lstData, String formato){
		//
		jasper.generaGridMtoElemento(lstData, formato, "Rutas Alternas", "\\GridMtoRutasAlternas.jasper");
	}
}
