/**
 * 
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
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
public class GeneraGridMtoElemento {
	/* catalogo 1*/
	public static List<UnidadReparto> generaGridUnidadReparto(){
		 List<UnidadReparto> arrData = new ArrayList<UnidadReparto>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 UnidadReparto obj = new UnidadReparto(a, "Modelo"+i, "1"+i, "00000"+i, "AABB"+i, "Blanco"+i, "ABCD"+i, ""+i, i+"%", "001001"+1, i, "Activo"+i, a, "Marca"+i, a, "LDC"+i, a, "Mexico"+i, "Kilometraje"+i, a);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 2*/
	public static List<Modelo> generaGridModelo(){
		List<Modelo> arrData = new ArrayList<Modelo>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Modelo obj = new Modelo(a, "Modelo"+i, "frecuencia"+i, a, "Marca"+i); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	
	/* catalogo 3*/
	public static List<Marca> generaGridMarca(){
		List<Marca> arrData = new ArrayList<Marca>();
		 for(int i=0;i<100;i++){
			 Marca obj = new Marca(""+i, "Marca"+i); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 4*/
	public static List<AsignacionUnidadReparto> generaGridAsignacionUnidadReparto(){
		List<AsignacionUnidadReparto> arrData = new ArrayList<AsignacionUnidadReparto>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 AsignacionUnidadReparto obj = new AsignacionUnidadReparto(a, "0000"+i, "12/01/2012", "13/01/2012", a, "Empleado"+i, a, "Unidad de Reparto"+i,
					 a, "LDC"+i, a, "Mexico"+i, a, "Activo"); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 5*/
	public static List<Rutas> generaGridRuta(){ //MAdana excepcion NoclassFound UIForm
		List<Rutas> arrData = new ArrayList<Rutas>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Rutas obj = new Rutas("ruta"+i, "1"+i, "Ruta"+i, "12/01/2012", "USER", ""+i, "Zona"+i, "j"+i, "Mexico"+i, "1"+i, "TipoRuta"+i, "1", "LDC",
					 				"Poblacion cercana"+i, 2.2, i, "12:12:12","13:13:13", a, "Alto"+i, 1, true, 0, "");
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 6*/
	public static List<TipoRuta> generaGridTipoRuta(){
		List<TipoRuta> arrData = new ArrayList<TipoRuta>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 TipoRuta obj = new TipoRuta(a, "TRUT"+i, "Tipo de Ruta "+i, a, "LDC"+i, a, "Mexico"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 7*/
	public static List<RepresentantesPorRuta> generaGridRepresentantesPorRuta(){  //Marca null al cargar este metodo-no cargo atributo de zona
		List<RepresentantesPorRuta> arrData = new ArrayList<RepresentantesPorRuta>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 RepresentantesPorRuta obj = new RepresentantesPorRuta(a, "20/01/2012", "20/01/2013", "100", "2", a, "Maria Sanjuana Representa"+i,
					 a, "RUTA LOCAL"+i, "n"+i, "Zona"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 8*/
	public static List<AsignacionRutaChofer> generaGridAsignacionRutaChofer(){
		List<AsignacionRutaChofer> arrData = new ArrayList<AsignacionRutaChofer>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 AsignacionRutaChofer obj = new AsignacionRutaChofer(a, "20/01/2012", "20/01/2013", "TipoAsig"+i ,a, a, "Ruta menos"+i, a, "Mexico"+i, a, "Zona"+i, a, "LDC"+i, a, "Empleado Sanjuana Maria");
					 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 9*/
	public static List<DispositivoMovil> generaGridDispositivoMovil(){
		List<DispositivoMovil> arrData = new ArrayList<DispositivoMovil>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 DispositivoMovil obj = new DispositivoMovil(a, "BBBB"+i, "dispositivo"+i, "UNICO"+i, "AABBBBBBCC"+i, "1.0.0."+i, a, "LDC"+i, a, "Mexico"+i, a, "Activo"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 10*/
	public static List<Empleado> generaGridEmpleado(){
		List<Empleado> arrData = new ArrayList<Empleado>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Empleado obj = new Empleado(a, "Spirale", "Regenbogen Ich", "Lie", "Conocido de las colonia", "20/01/2000", i, "M", i, "Soltero", "SSSSSSSS"+i, "20/01/2012", "NumTELEFONO", "mail@live.com", a, "Activo"+i, a, "Puesto"+i, a, "LDC"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 11*/
	public static List<Puesto> generaGridPuesto(){
		List<Puesto> arrData = new ArrayList<Puesto>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Puesto obj = new Puesto(a, "TRDCTR"+i, "Traductor EM"+i,  a, "LDC"+i, a, "Mexico"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 12*/
	public static List<LineaTransporte> generaGridLineaTransporte(){
		List<LineaTransporte> arrData = new ArrayList<LineaTransporte>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 LineaTransporte obj = new LineaTransporte(a, "LTRSTP"+i, "Linea d eTransporte"+i, "000000000"+i, "Conocido entre las calles del centro", a, "Mexico"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 13*/
	public static List<Informante> generaGridInformante(){
		List<Informante> arrData = new ArrayList<Informante>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Informante obj = new Informante(a, "Informante"+i, a, "Tipo Chofer"+i, a, "LDC"+i, a, "Mexico"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 14*/
	public static List<SubBodegaAlmacen> generaGridSubBodegaAlmacen(){
		List<SubBodegaAlmacen> arrData = new ArrayList<SubBodegaAlmacen>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 SubBodegaAlmacen obj = new SubBodegaAlmacen(a, "Sub Bodega"+i, "Del centro"+i, "00111111"+i, "Conocido del pais mx", a, "Mexico"+i,  a, "LDC"+i, a, "Zona"+i, a, "Responsable Juan de Shire");
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 15*/
	public static List<Destinatario> generaGridDestinatario(){
		List<Destinatario> arrData = new ArrayList<Destinatario>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Destinatario obj = new Destinatario(a, "Destinatario"+i, "Juana de Arco"+i, "Materno"+i, "MiPaternal"+i, "mimaul@gggyyy.net", a, "Mexico"+i, a, "LDC"+i, a, "Tipo Destinatario"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 16*/
	public static List<TipoDestinatario> generaGridTipoDestinatario(){
		List<TipoDestinatario> arrData = new ArrayList<TipoDestinatario>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 TipoDestinatario obj = new TipoDestinatario(a, "TIPOACC"+i, "Oportu"+i, a, "Mexico"+i, a, "LDC"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 17*/
	public static List<ReporteTipoDestinatario> generaGridReporteTipoDestinatario(){
		List<ReporteTipoDestinatario> arrData = new ArrayList<ReporteTipoDestinatario>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 ReporteTipoDestinatario obj = new ReporteTipoDestinatario(a, "REPORTEOP"+i, a, "Oportu"+i,a);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 18*/
	public static List<Reporte> generaGridReporte(){
		List<Reporte> arrData = new ArrayList<Reporte>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Reporte obj = new Reporte(a, "MiReportePruebas"+i, "ReportePruebas" + i, "Directorio particular", "../c/bin/lib"+i, "Pruebas"+i, a, "Mexico"+i, a, "LDC"+i);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 19*/
	public static List<ParametrosLDC> generaGridLDC(){ //MArco null al cargar metodo
		List<ParametrosLDC> arrData = new ArrayList<ParametrosLDC>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 ParametrosLDC obj = new ParametrosLDC(a, "CEDIS Central", "Celaya DGTO", "mailldc@ldc.com", "LDC_CD"+i, "mipasss"+i, "0.0.0.1", "808"+i, "Ninnguna"+i, "100"+i, "4"+i, "00001"+i, "00002"+i, "Queretaro", "Qro", "10.0.0.27", a);
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 20*/
	public static List<Usuario> generaGridUsuario(){
		List<Usuario> arrData = new ArrayList<Usuario>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Usuario obj = new Usuario(i, "USERTST"+i, a, "TipoUsuario"+i, a, "PerfilAdmor"+i, a, "Mexico"+i, a, "LDC"+i, a, "Empleado Maria Sanjuana"+i, "mariauser"+i, "Usuario admor de prueba"); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 21*/
	public static List<Perfil> generaGridPerfil(){
		List<Perfil> arrData = new ArrayList<Perfil>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Perfil obj = new Perfil(a, "ADMOR"+i, "Administrador del Sistema"+i); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
	/* catalogo 22*/
	public static List<Denominaciones> generaGridDenominaciones(){
		List<Denominaciones> arrData = new ArrayList<Denominaciones>();
		 for(int i=0;i<100;i++){
			 Integer a = i;
			 Denominaciones obj = new Denominaciones(a, "200"+i, "Billete"+i, a); 
			 arrData.add(obj);
		 }
		 return arrData;
	}
}
