package com.datacode.avon_ots_admin.reports;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.axis2.AxisFault;
import org.apache.commons.io.IOUtils;

import com.datacode.avon_ots_admin.model.ModelRemito;
import com.datacode.avon_ots_admin.replicacion.Replicacion;
import com.datacode.avon_ots_admin.replicacion.ReplicacionDetalle;
import com.datacode.avon_ots_admin.replicacion.ReplicacionTabla;
import com.datacode.avon_ots_admin.reports.model.LiquidacionesRep;
import com.datacode.avon_ots_admin.reports.model.ModelAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelDetalleCajas;
import com.datacode.avon_ots_admin.reports.model.ModelDetalleDocumento;
import com.datacode.avon_ots_admin.reports.model.ModelDetallePremios;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderReparto;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderResumen;
import com.datacode.avon_ots_admin.reports.model.ModelHistorialPorRepresentante;
import com.datacode.avon_ots_admin.reports.model.ModelHistoricoDeRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelManifiestoRutaEnCampania;
import com.datacode.avon_ots_admin.reports.model.ModelOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosPrestados;
import com.datacode.avon_ots_admin.reports.model.ModelRepConsultaRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelRepDescargaEnrutado;
import com.datacode.avon_ots_admin.reports.model.ModelRepDomiciliosIncorrectos;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesCajas;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesTotales;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesUnitarios;
import com.datacode.avon_ots_admin.reports.model.ModelRepPremios;
import com.datacode.avon_ots_admin.reports.model.ModelRepRecepcionCarga;
import com.datacode.avon_ots_admin.reports.model.ModelRepRecoleccionRemitos;
import com.datacode.avon_ots_admin.reports.model.ModelRepReparto;
import com.datacode.avon_ots_admin.reports.model.ModelRepSalidaCamioneta;
import com.datacode.avon_ots_admin.reports.model.ModelRepTablaPremios;
import com.datacode.avon_ots_admin.reports.model.ModelReporteConcultaBuenVecino;
import com.datacode.avon_ots_admin.reports.model.ModelReporteConsultaEstatusOdenesRep;
import com.datacode.avon_ots_admin.reports.model.ModelReporteConsultaEstatusOrdenesRepDetalle;
import com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEscaneados;
import com.datacode.avon_ots_admin.reports.model.ModelReporteResumen;
import com.datacode.avon_ots_admin.reports.model.ModelReporteResumenGeneralZonas;
import com.datacode.avon_ots_admin.reports.model.ModelTablaAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelTablaAnalisisEfectivoLiquidacion;
import com.datacode.avon_ots_admin.reports.model.ModelTablaDescargaEnrutado;
import com.datacode.avon_ots_admin.reports.model.ModelTablaHistorialPorRepresentante;
import com.datacode.avon_ots_admin.reports.model.ModelTablaHistoricoDeRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelTablaManifiestoRutaEnCampania;
import com.datacode.avon_ots_admin.reports.model.ModelTablaRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelTablaRelPedidosPrestados;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionReparto;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoDetalle;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotal;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotalDetLibres;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotalDetRED;
import com.datacode.avon_ots_admin.reports.model.TablaCajasMaltratadas;
import com.datacode.avon_ots_admin.reports.model.TablaCargaRecibida;
import com.datacode.avon_ots_admin.reports.model.TablaInfoCodFaltantes;
import com.datacode.avon_ots_admin.reports.model.TablaOrdenesXZona;
import com.datacode.avon_ots_admin.reports.model.TablaPapeleo;
import com.datacode.avon_ots_admin.reports.test.GeneraReporteReparto;
import com.datacode.avon_ots_admin.utils.AccesoBD;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.CampaniaControllerStub.GetCampanias;
import com.datacode.avon_ots_ws.CampaniaControllerStub.GetUltimasCampaniasSinID;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesRemitosControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.ObtenerListaLiquidacionesMail;
import com.datacode.avon_ots_ws.ModelRutaEspecialRemitos;
import com.datacode.avon_ots_ws.ObtenerCajasOrdenDejadaRecolectada;
import com.datacode.avon_ots_ws.ObtenerCajasOrdenDejadaRecolectadaResponse;
import com.datacode.avon_ots_ws.ObtenerDocumentosOrdenDejadaRecolectada;
import com.datacode.avon_ots_ws.ObtenerDocumentosOrdenDejadaRecolectadaResponse;
import com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMailResponse;
import com.datacode.avon_ots_ws.ObtenerPUPOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_ws.ObtenerPUPOrdenesDejadasRecolectadasResponse;
import com.datacode.avon_ots_ws.ObtenerPremiosUnitariosOrdenDejadaRecolectada;
import com.datacode.avon_ots_ws.ObtenerPremiosUnitariosOrdenDejadaRecolectadaResponse;
import com.datacode.avon_ots_ws.ObtieneRemitos;
import com.datacode.avon_ots_ws.ObtieneRemitosResponse;
import com.datacode.avon_ots_ws.OrdenesDejadasRecolectadasStub;
import com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub;
import com.datacode.avon_ots_ws.ReporteConsultaEstatusOrdenesControllerStub;
import com.datacode.avon_ots_ws.ReporteConsultaEstatusOrdenesControllerStub.ModelReporteConsultaEstatus;
import com.datacode.avon_ots_ws.ReporteConsultaEstatusOrdenesControllerStub.ObtenerConsultaEstatusOrdenes;
import com.datacode.avon_ots_ws.ReporteDomiciliosIncorrectosControllerStub;
import com.datacode.avon_ots_ws.ReporteDomiciliosIncorrectosControllerStub.ModelReporteDomiciliosIncorrectos;
import com.datacode.avon_ots_ws.ReporteItemsFaltantesControllerStub;
import com.datacode.avon_ots_ws.ReporteItemsFaltantesControllerStub.ModelReporteItemsFaltantesDetalleCajas;
import com.datacode.avon_ots_ws.ReporteItemsFaltantesControllerStub.ModelReporteItemsFaltantesDetalleUnitarios;
import com.datacode.avon_ots_ws.ReporteItemsFaltantesControllerStub.ModelReporteItemsFaltantesTotales;
import com.datacode.avon_ots_ws.ReporteItemsNoEscaneadosControllerStub;
import com.datacode.avon_ots_ws.ReporteItemsNoEscaneadosControllerStub.ModelRepItemsNoEscaneados;
import com.datacode.avon_ots_ws.ReporteRecoleccionRemitosControllerStub;
import com.datacode.avon_ots_ws.ReporteRecoleccionRemitosControllerStub.ModelReporteRecoleccionRemitos;
import com.datacode.avon_ots_ws.ReportesControllerStub;
import com.datacode.avon_ots_ws.ReportesControllerStub.DesgloceEfectivo;
import com.datacode.avon_ots_ws.ReportesControllerStub.DestinatarioReporte;
import com.datacode.avon_ots_ws.ReportesControllerStub.ItemSubBodega;
import com.datacode.avon_ots_ws.ReportesControllerStub.LiquidacionRep;
import com.datacode.avon_ots_ws.ReportesControllerStub.ModelReparto;
import com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteItemsNoEntregadosEnAlmacen;
import com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerEncabezadoPedidosEntregadosGerenteZonal;
import com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerPedidosEntregadosGerenteZonal;
import com.datacode.avon_ots_ws.ReportesControllerStub.PedidoPrestado;
import com.datacode.avon_ots_ws.ReportesControllerStub.RelPedDejadosHeader;
import com.datacode.avon_ots_ws.ReportesControllerStub.RelPelDejadosDetalle;
import com.datacode.avon_ots_ws.ReportesControllerStub.Reporte;
import com.datacode.avon_ots_ws.ReportesControllerStub.RepresentanteReporte;
import com.datacode.avon_ots_ws.ReportesControllerStub.SalidaCamioneta;
import com.datacode.avon_ots_ws.ReportesControllerStub.SubBodegaAlmacen;
import com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub;
import com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub.ObtenerSubBodegaAlmacen;
import com.datacode.avon_ots_ws.ZonaControllerStub;
import com.datacode.avon_ots_ws.ZonaControllerStub.GetZonas;
import com.datacode.avon_ots_ws.ZonaControllerStub.Zona;
import com.datacode.avon_ots_ws.model.xsd.CajaOrdenDejadaRecolectadaPUPDTO;
import com.datacode.avon_ots_ws.model.xsd.DocumentoOrdenDejadaRecolectadaPUPDTO;
import com.datacode.avon_ots_ws.model.xsd.LiquidacionRepartoDTO;
import com.datacode.avon_ots_ws.model.xsd.PUPDTO;
import com.datacode.avon_ots_ws.model.xsd.PremioUnitarioOrdenDejadaRecolectadaPUPDTO;

public class ConsultaDatosReportes {
	// Obtiene variable de configuración
	Configuracion config = null;

	public ConsultaDatosReportes() {
		if (config == null) {
			if (FacesContext.getCurrentInstance() != null) {
				config = (Configuracion) FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap()
						.get("configuracion");
			}
		}
	}

	protected List<ModelRelPedidosDejados> datosReporteRelacionPedidosDejados(
			int ruta, int campania, int idLdc, int idSubBodega) {
		List<ModelRelPedidosDejados> listaRep = new ArrayList<ModelRelPedidosDejados>();
		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReportesControllerStub.ObtieneHeaderRelPedDejados param = new ReportesControllerStub.ObtieneHeaderRelPedDejados();
			param.setCampania(campania);
			param.setRuta(ruta);
			param.setIdUsuario(config.getIdUsuario());
			param.setIdLdc(idLdc);
			param.setIdSubBodega(idSubBodega);
			ReportesControllerStub.ObtieneHeaderRelPedDejadosResponse response = stub
					.obtieneHeaderRelPedDejados(param);

			RelPedDejadosHeader header = response.get_return();
			ModelRelPedidosDejados ped = new ModelRelPedidosDejados();
			ped.setAyudante(header.getNombreAyudante());
			ped.setCampania(header.getCampania());
			ped.setChofer(header.getNombreChofer());
			ped.setPoblacionP(header.getPoblacion());
			ped.setRepartidor(header.getNombreEntrega());
			ped.setEncargado(header.getNombreRecibe());
			ped.setRuta(header.getRuta());
			ped.setZona(header.getZona());

			ReportesControllerStub.ObtenerRelPedDejadosDetalle param2 = new ReportesControllerStub.ObtenerRelPedDejadosDetalle();
			param2.setCampania(campania);
			param2.setIdUsuario(config.getIdUsuario());
			param2.setRuta(ruta);
			param2.setIdLdc(idLdc);
			param2.setIdSubBodega(idSubBodega);
			ReportesControllerStub.ObtenerRelPedDejadosDetalleResponse respuesta = stub
					.obtenerRelPedDejadosDetalle(param2);
			RelPelDejadosDetalle[] arreglo = respuesta.get_return();
			List<ModelTablaRelPedidosDejados> detalle = new ArrayList<ModelTablaRelPedidosDejados>();

			if (arreglo != null) {
				for (RelPelDejadosDetalle reg : arreglo) {
					ModelTablaRelPedidosDejados re = new ModelTablaRelPedidosDejados();
					re.setCajas(reg.getCajas());
					re.setImporte(reg.getImporte());
					re.setCont(reg.getNumero());
					re.setRuta(reg.getRuta());
					re.setRegistro(reg.getRegistro());
					re.setPremio("" + reg.getPremios());
					re.setNombre(reg.getNombre());
					re.setUnitarios(reg.getUnitarios());
					re.setCausa1("");
					re.setCausa2("");
					re.setCausa3("");
					re.setCausa4("");
					re.setCausa5("");
					re.setCausa6("");
					re.setCausa7("");
					re.setCausa8("");
					re.setCausa9("");
					re.setCausa10("");
					re.setCausa11("");

					switch (reg.getCausaDev()) {
					case 1:
						re.setCausa1("X");
						break;
					case 2:
						re.setCausa2("X");
						break;
					case 3:
						re.setCausa3("X");
						break;
					case 4:
						re.setCausa4("X");
						break;
					case 5:
						re.setCausa5("X");
						break;
					case 6:
						re.setCausa6("X");
						break;
					case 7:
						re.setCausa7("X");
						break;
					case 8:
						re.setCausa8("X");
						break;
					case 9:
						re.setCausa9("X");
						break;
					case 10:
						re.setCausa10("X");
						break;
					case 11:
						re.setCausa11("X");
						break;
					}

					detalle.add(re);
				}
			}
			ped.setPedidosDejados(detalle);
			listaRep.add(ped);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelRepConsultaRepresentantes> datosReporteConsultaRepresentantes(
			String account, int idLDC) {
		List<ModelRepConsultaRepresentantes> listaRep = new ArrayList<ModelRepConsultaRepresentantes>();
		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReportesControllerStub.ObtenerRepresentantesReportes param = new ReportesControllerStub.ObtenerRepresentantesReportes();
			param.setAccount(account);
			param.setIdLDC(idLDC);
			param.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerRepresentantesReportesResponse response = stub
					.obtenerRepresentantesReportes(param);

			RepresentanteReporte[] arreglo = response.get_return();
			if (arreglo != null) {
				for (RepresentanteReporte reg : arreglo) {
					ModelRepConsultaRepresentantes regN = new ModelRepConsultaRepresentantes();
					regN.setBancoFolio(reg.getBancoFolio());
					regN.setCampania(reg.getCampania());
					regN.setCausaDevolucion(reg.getCausaDevolucion());
					regN.setDireccionRepresentante(reg
							.getDireccionRepresentante());
					regN.setEntregadoEn(reg.getEntregadoEn());
					regN.setFechaDevolucion(reg.getFechaDevolucion());
					regN.setFechaEntrega(reg.getFechaEntrega());
					regN.setFechaPago(reg.getFechaPago());
					regN.setGps(reg.getGps());
					regN.setIdRepresentante(reg.getIdRepresentante());
					regN.setMontoPagado(reg.getMontoPagado());
					regN.setNombreRepresentante(reg.getNombreRepresentante());
					regN.setOrder(reg.getOrder());
					regN.setTipoPago(reg.getTipoPago());
					regN.setFotoDomicilio(devolverFotoDomicilio(reg
							.getFotoDomicilio()));
					regN.setRegistroRepresentante(reg
							.getRegistroRepresentante());
					listaRep.add(regN);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelReporteConcultaBuenVecino> datosReporteConsultaBuenVecino(
			int idUsuario, int zona, int ruta, String registro) {
		List<ModelReporteConcultaBuenVecino> listaRep = new ArrayList<ModelReporteConcultaBuenVecino>();
		try {
			ReporteConsultaBuenVecinoControllerStub stub = new ReporteConsultaBuenVecinoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino param = new ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino();
			param.setRuta(ruta);
			param.setZona(zona);
			param.setRegistro(registro);
			param.setIdUsuario(config.getIdUsuario());
			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecinoResponse response = stub
					.obtenerConsultaBuenVecino(param);

			com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel[] arreglo = response
					.get_return();
			if (arreglo != null) {
				for (com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel reg : arreglo) {
					ModelReporteConcultaBuenVecino r = new ModelReporteConcultaBuenVecino();
					r.setClaveRuta(reg.getClaveRuta());
					r.setDomicilio(reg.getDomicilio());
					r.setDomicilioAlterno(reg.getDomicilioAlterno());
					r.setFechaModificacionDomAlterno(reg
							.getFechaModificacionDomAlterno());
					r.setIdRuta(reg.getIdRuta());
					r.setIdZona(reg.getIdZona());
					r.setNombre(reg.getNombre());
					r.setRegistro(reg.getRegistro());
					r.setFechaModificacionDomAlternoS(reg
							.getFechaModificacionDomAlternoS());
					if (reg.getCampaniaCambio() == null) {
						r.setCampaniaCambio("");
					} else {
						r.setCampaniaCambio(reg.getCampaniaCambio());
					}

					r.setEntregarEnDomicilioAlterno(reg
							.getEntregaEnDomicilioalterno());

					listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelReporteConcultaBuenVecino> datosReporteControlRecepcionDeCarga(
			int idUsuario, int zona, int campania) {
		List<ModelReporteConcultaBuenVecino> listaRep = new ArrayList<ModelReporteConcultaBuenVecino>();
		try {
			ReporteConsultaBuenVecinoControllerStub stub = new ReporteConsultaBuenVecinoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino param = new ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino();
			param.setZona(zona);
			param.setIdUsuario(config.getIdUsuario());
			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecinoResponse response = stub
					.obtenerConsultaBuenVecino(param);

			com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel[] arreglo = response
					.get_return();
			if (arreglo != null) {
				for (com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel reg : arreglo) {
					ModelReporteConcultaBuenVecino r = new ModelReporteConcultaBuenVecino();
					r.setClaveRuta(reg.getClaveRuta());
					r.setDomicilio(reg.getDomicilio());
					r.setDomicilioAlterno(reg.getDomicilioAlterno());
					r.setFechaModificacionDomAlterno(reg
							.getFechaModificacionDomAlterno());
					r.setIdRuta(reg.getIdRuta());
					r.setIdZona(reg.getIdZona());
					r.setNombre(reg.getNombre());
					r.setRegistro(reg.getRegistro());
					r.setFechaModificacionDomAlternoS(reg
							.getFechaModificacionDomAlternoS());
					if (reg.getCampaniaCambio() == null) {
						r.setCampaniaCambio("");
					} else {
						r.setCampaniaCambio(reg.getCampaniaCambio());
					}

					r.setEntregarEnDomicilioAlterno(reg
							.getEntregaEnDomicilioalterno());

					listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelRepRecoleccionRemitos> datosReporteFaltanteCajas(
			int idUsuario, int zona, int campania) {
		List<ModelRepRecoleccionRemitos> listaRep = new ArrayList<ModelRepRecoleccionRemitos>();
		try {
			ReporteConsultaBuenVecinoControllerStub stub = new ReporteConsultaBuenVecinoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino param = new ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecino();
			param.setZona(zona);
			param.setIdUsuario(config.getIdUsuario());
			ReporteConsultaBuenVecinoControllerStub.ObtenerConsultaBuenVecinoResponse response = stub
					.obtenerConsultaBuenVecino(param);

			com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel[] arreglo = response
					.get_return();
			if (arreglo != null) {
				for (com.datacode.avon_ots_ws.ReporteConsultaBuenVecinoControllerStub.ReporteConsultaBuenVecinoModel reg : arreglo) {
					ModelReporteConcultaBuenVecino r = new ModelReporteConcultaBuenVecino();
					r.setClaveRuta(reg.getClaveRuta());
					r.setDomicilio(reg.getDomicilio());
					r.setDomicilioAlterno(reg.getDomicilioAlterno());
					r.setFechaModificacionDomAlterno(reg
							.getFechaModificacionDomAlterno());
					r.setIdRuta(reg.getIdRuta());
					r.setIdZona(reg.getIdZona());
					r.setNombre(reg.getNombre());
					r.setRegistro(reg.getRegistro());
					r.setFechaModificacionDomAlternoS(reg
							.getFechaModificacionDomAlternoS());
					if (reg.getCampaniaCambio() == null) {
						r.setCampaniaCambio("");
					} else {
						r.setCampaniaCambio(reg.getCampaniaCambio());
					}

					r.setEntregarEnDomicilioAlterno(reg
							.getEntregaEnDomicilioalterno());

					// listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelRepFaltantesTotales> datosReporteItemsFaltantesTotales(
			int idUsuario, int zona, int campania) {
		List<ModelRepFaltantesTotales> listaRep = new ArrayList<ModelRepFaltantesTotales>();
		try {
			ReporteItemsFaltantesControllerStub stub = new ReporteItemsFaltantesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteItemsFaltantesControllerStub.ObtenerTotales param = new ReporteItemsFaltantesControllerStub.ObtenerTotales();
			param.setZona(zona);
			param.setIdUsuario(config.getIdUsuario());
			param.setCampania(campania);
			ReporteItemsFaltantesControllerStub.ObtenerTotalesResponse response = stub
					.obtenerTotales(param);

			ModelReporteItemsFaltantesTotales a = response.get_return();
			if (a != null) {
				ModelRepFaltantesTotales r = new ModelRepFaltantesTotales();
				r.setCajasFacturadas(a.getCajasFacturadas());
				r.setCajasFaltantes(a.getCajasFaltantes());
				r.setCajasRecibidas(a.getCajasFacturadas()
						- a.getCajasFaltantes());
				r.setPremiosFacturadas(a.getPremiosFacturados());
				r.setPremiosFaltantes(a.getPremiosFaltantes());
				r.setPremiosRecibidas(a.getPremiosFacturados()
						- a.getPremiosFaltantes());
				r.setUnitariosFacturadas(a.getUnitariosFacturados());
				r.setUnitariosFaltantes(a.getUnitariosFaltantes());
				r.setUnitariosRecibidas(a.getUnitariosFacturados()
						- a.getUnitariosFaltantes());
				listaRep.add(r);

			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	protected List<ModelRepFaltantesCajas> datosReporteItemsFaltantesDetalleCajas(
			int idUsuario, int zona, int campania) {
		List<ModelRepFaltantesCajas> lista = new ArrayList<ModelRepFaltantesCajas>();
		try {
			ReporteItemsFaltantesControllerStub stub = new ReporteItemsFaltantesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteItemsFaltantesControllerStub.ObtenerDetalleCajas param = new ReporteItemsFaltantesControllerStub.ObtenerDetalleCajas();
			param.setZona(zona);
			param.setIdUsuario(config.getIdUsuario());
			param.setCampania(campania);
			ReporteItemsFaltantesControllerStub.ObtenerDetalleCajasResponse response = stub
					.obtenerDetalleCajas(param);

			ModelReporteItemsFaltantesDetalleCajas[] a = response.get_return();
			if (a != null) {

				for (ModelReporteItemsFaltantesDetalleCajas r : a) {
					ModelRepFaltantesCajas s = new ModelRepFaltantesCajas();
					s.setCantFaltante(r.getFaltantes());
					s.setRuta(r.getRuta());
					s.setRepresentante(r.getNombre());
					s.setRegistro(r.getRegistro());
					s.setFaltante(r.getCodigoBarras());
					s.setOrden(r.getOrden());
					s.setFacturado(r.getCantidadCajas());
					s.setRecibido(r.getCantidadCajas() - r.getFaltantes());

					lista.add(s);
				}

			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	protected List<ModelRepFaltantesUnitarios> datosReporteItemsFaltantesDetalleUnitarios(
			int idUsuario, int zona, int campania) {
		List<ModelRepFaltantesUnitarios> lista = new ArrayList<ModelRepFaltantesUnitarios>();
		try {
			ReporteItemsFaltantesControllerStub stub = new ReporteItemsFaltantesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteItemsFaltantesControllerStub.ObtenerDetalleUnitarios param = new ReporteItemsFaltantesControllerStub.ObtenerDetalleUnitarios();
			param.setZona(zona);
			param.setIdUsuario(config.getIdUsuario());
			param.setCampania(campania);
			ReporteItemsFaltantesControllerStub.ObtenerDetalleUnitariosResponse response = stub
					.obtenerDetalleUnitarios(param);

			ModelReporteItemsFaltantesDetalleUnitarios[] a = response
					.get_return();
			if (a != null) {

				for (ModelReporteItemsFaltantesDetalleUnitarios r : a) {
					ModelRepFaltantesUnitarios s = new ModelRepFaltantesUnitarios();
					s.setDescripcion(r.getDescripcion());
					s.setEAN13(r.getCodigoEAN13());
					s.setFacturado(r.getCantidadCajas());
					s.setFaltante(r.getFaltantes());
					s.setFSC(r.getCodigoFSC());
					s.setRecibido(r.getCantidadCajas() - r.getFaltantes());
					s.setTipo(r.getTipoItem());

					lista.add(s);
				}

			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	protected List<ModelRepRecoleccionRemitos> datosReporteRecoleccionDeRemito(
			int idUsuario, int zona, int campania, int ruta) {
		List<ModelRepRecoleccionRemitos> listaRep = new ArrayList<ModelRepRecoleccionRemitos>();
		try {
			ReporteRecoleccionRemitosControllerStub stub = new ReporteRecoleccionRemitosControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteRecoleccionRemitosControllerStub.ObtenerConsultaRecoleccionRemitos param = new ReporteRecoleccionRemitosControllerStub.ObtenerConsultaRecoleccionRemitos();
			param.setZona(zona);
			param.setCampania(campania);
			param.setRuta(ruta);
			param.setIdUsuario(config.getIdUsuario());
			ReporteRecoleccionRemitosControllerStub.ObtenerConsultaRecoleccionRemitosResponse response = stub
					.obtenerConsultaRecoleccionRemitos(param);

			ModelReporteRecoleccionRemitos[] arreglo = response.get_return();
			if (arreglo != null) {
				for (ModelReporteRecoleccionRemitos reg : arreglo) {
					ModelRepRecoleccionRemitos r = new ModelRepRecoleccionRemitos();
					r.setCampania(reg.getCampania());
					r.setEstatus(reg.getEstatus());
					r.setFecha(reg.getFecha());
					r.setRecibido(reg.getRecibido());
					r.setRecolectar(reg.getRecolectar());
					r.setRegistro(reg.getRegistro());
					r.setRuta(reg.getRuta());
					r.setZona(reg.getZona());
					r.setComentarios(reg.getComentarios());
					listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	public List<ModelRepDomiciliosIncorrectos> datosReporteDomiciliosIncorrectos(
			int idUsuario) {
		List<ModelRepDomiciliosIncorrectos> listaRep = new ArrayList<ModelRepDomiciliosIncorrectos>();
		try {
			ReporteDomiciliosIncorrectosControllerStub stub = new ReporteDomiciliosIncorrectosControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteDomiciliosIncorrectosControllerStub.ObtenerRepresentantesDomiciliosIncorrectos param = new ReporteDomiciliosIncorrectosControllerStub.ObtenerRepresentantesDomiciliosIncorrectos();
			param.setIdUsuario(idUsuario);
			ReporteDomiciliosIncorrectosControllerStub.ObtenerRepresentantesDomiciliosIncorrectosResponse response = stub
					.obtenerRepresentantesDomiciliosIncorrectos(param);

			ModelReporteDomiciliosIncorrectos[] arreglo = response.get_return();
			if (arreglo != null) {

				for (ModelReporteDomiciliosIncorrectos reg : arreglo) {
					ModelRepDomiciliosIncorrectos r = new ModelRepDomiciliosIncorrectos();
					r.setDomicilioActual(reg.getDomicilioActual());
					r.setDomicilioCorrecto(reg.getDomicilioCorrecto());
					r.setEmail(reg.getEmail());
					r.setFechaReporte(reg.getFechaReporte());
					r.setNombre(reg.getNombre());
					r.setRegistro(reg.getRegistro());
					r.setRuta(reg.getRuta());
					r.setZona(reg.getZona());
					r.setFechaReporteS(reg.getFechaReporteS());
					listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	private ByteArrayInputStream devolverFotoDomicilio(DataHandler dataImagen) {

		ByteArrayInputStream imagen = null;

		if (dataImagen != null) {

			InputStream inputStream;
			try {
				inputStream = dataImagen.getInputStream();
				byte[] arrayFotoDomicilio = IOUtils.toByteArray(inputStream);
				imagen = new ByteArrayInputStream(arrayFotoDomicilio);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagen;
	}

	private InputStream devolverFotoReporte(DataHandler dataImagen) {

		InputStream imagen = null;

		if (dataImagen != null) {

			InputStream inputStream;
			try {
				inputStream = dataImagen.getInputStream();
				byte[] arrayFotoDomicilio = IOUtils.toByteArray(inputStream);
				FileOutputStream fos = new FileOutputStream(
						"c:\\fotosAdmin\\fotoAdmin.jpg");
				fos.write(arrayFotoDomicilio);
				fos.close();
				imagen = new ByteArrayInputStream(arrayFotoDomicilio);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagen;
	}

	protected List<ModelRelPedidosPrestados> datosReportePedidosPrestados(
			int idZona, int idCampania, int idEntregado, Date fecha) {

		ModelRelPedidosPrestados pedidoPrestado = null;
		List<ModelRelPedidosPrestados> pedidosPrestados = new ArrayList<ModelRelPedidosPrestados>();
		List<ModelTablaRelPedidosPrestados> listaDetallePedPrestados = null;

		try {
			ReportesControllerStub reporteStub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(reporteStub
					._getServiceClient().getOptions().getTo().getAddress());
			reporteStub
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ObtenerEncabezadoPedidosEntregadosGerenteZonal pedidos = new ObtenerEncabezadoPedidosEntregadosGerenteZonal();

			pedidos.setIdCampania(idCampania);
			pedidos.setIdZona(idZona);
			pedidos.setIdUsuario(config.getIdUsuario());

			ReportesControllerStub.ObtenerEncabezadoPedidosEntregadosGerenteZonalResponse response = reporteStub
					.obtenerEncabezadoPedidosEntregadosGerenteZonal(pedidos);

			PedidoPrestado[] pedidosPres = response.get_return();

			if (pedidosPres != null) {

				for (int i = 0; i < pedidosPres.length; i++) {
					pedidoPrestado = new ModelRelPedidosPrestados();
					pedidoPrestado.setZona(pedidosPres[i].getZona());
					pedidoPrestado.setCampania(pedidosPres[i].getCampania());
					pedidoPrestado.setEntregado(pedidosPres[i].getEntregado());
					listaDetallePedPrestados = consultarDetallePedidosEntregadosGerenteZonal(
							idZona, idCampania);
					pedidoPrestado.setListaPedidos(listaDetallePedPrestados);
				}
				pedidosPrestados.add(pedidoPrestado);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return pedidosPrestados;
	}

	protected List<ModelTablaRelPedidosPrestados> consultarDetallePedidosEntregadosGerenteZonal(
			int idZona, int idCampania) {
		List<ModelTablaRelPedidosPrestados> listaDetallePedPrestados = new ArrayList<ModelTablaRelPedidosPrestados>();
		ModelTablaRelPedidosPrestados detallePedPrestado = null;

		try {
			ReportesControllerStub reporteStub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(reporteStub
					._getServiceClient().getOptions().getTo().getAddress());
			reporteStub
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ObtenerPedidosEntregadosGerenteZonal pedidos = new ObtenerPedidosEntregadosGerenteZonal();

			pedidos.setIdCampania(idCampania);
			pedidos.setIdZona(idZona);
			pedidos.setIdUsuario(config.getIdUsuario());

			ReportesControllerStub.ObtenerPedidosEntregadosGerenteZonalResponse response = reporteStub
					.obtenerPedidosEntregadosGerenteZonal(pedidos);

			com.datacode.avon_ots_ws.ReportesControllerStub.ModelTablaRelPedidosPrestados[] pedidosPres = response
					.get_return();

			if (pedidosPres != null) {

				for (int i = 0; i < pedidosPres.length; i++) {
					detallePedPrestado = new ModelTablaRelPedidosPrestados();
					detallePedPrestado.setCont(pedidosPres[i].getCont());
					detallePedPrestado
							.setRegistro(pedidosPres[i].getRegistro());
					detallePedPrestado.setNombre(pedidosPres[i].getNombre());
					detallePedPrestado.setRuta(pedidosPres[i].getRuta());
					detallePedPrestado.setSalidaReparto(pedidosPres[i]
							.getSalidaReparto());
					detallePedPrestado.setCajas(pedidosPres[i].getCajas());
					detallePedPrestado.setUnitarios(pedidosPres[i]
							.getUnitarios());
					detallePedPrestado.setImporte(pedidosPres[i].getImporte());
					detallePedPrestado.setPremio(pedidosPres[i].getPremio());
					detallePedPrestado.setCausa(pedidosPres[i].getCausa());
					listaDetallePedPrestados.add(detallePedPrestado);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDetallePedPrestados;
	}

	protected List<SelectItem> obtenerCampanias(int idLDC, int idRuta,
			int idUsuario, int idZona) {
		List<SelectItem> campaniasCombo = new ArrayList<SelectItem>();
		try {
			CampaniaControllerStub campaniaStub = new CampaniaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(campaniaStub
					._getServiceClient().getOptions().getTo().getAddress());
			campaniaStub
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			GetCampanias campanias = new GetCampanias();
			campanias.setTipoCasoUso("REPORTES");
			campanias.setIdLDC(idLDC);
			campanias.setIdRuta(idRuta);
			campanias.setIdUsuario(idUsuario);
			campanias.setIdZona(idZona);
			CampaniaControllerStub.GetCampaniasResponse response = campaniaStub
					.getCampanias(campanias);

			Campania[] arregloCampanias = response.get_return();
			SelectItem itemSel = new SelectItem();
			itemSel.setLabel("Selecciona Campaña");
			itemSel.setValue(0);
			campaniasCombo.add(itemSel);
			if (arregloCampanias != null) {
				for (Campania camp : arregloCampanias) {
					SelectItem item = new SelectItem();
					item.setLabel(camp.getCampania() + " - "
							+ camp.getAnioCampania());
					item.setValue(camp.getIdCampania());
					campaniasCombo.add(item);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return campaniasCombo;
	}

	protected List<SelectItem> obtenerCampaniasSinId() {
		List<SelectItem> campaniasCombo = new ArrayList<SelectItem>();
		try {
			CampaniaControllerStub campaniaStub = new CampaniaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(campaniaStub
					._getServiceClient().getOptions().getTo().getAddress());
			campaniaStub
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			GetUltimasCampaniasSinID campanias = new GetUltimasCampaniasSinID();
			campanias.setTipoCasoUso("ULTIMAS_CAMPANIAS");
			campanias.setIdUsuario(0);
			CampaniaControllerStub.GetUltimasCampaniasSinIDResponse response = campaniaStub
					.getUltimasCampaniasSinID(campanias);

			Campania[] arregloCampanias = response.get_return();
			SelectItem itemSel = new SelectItem();
			itemSel.setLabel("Selecciona Campaña");
			itemSel.setValue("0");
			campaniasCombo.add(itemSel);
			if (arregloCampanias != null) {
				for (Campania camp : arregloCampanias) {
					SelectItem item = new SelectItem();
					item.setLabel(camp.getUsuarioActualiza());
					item.setValue(camp.getUsuarioActualiza());
					campaniasCombo.add(item);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return campaniasCombo;
	}

	protected List<SelectItem> obtenerZonas(int p_idLDC) {
		List<SelectItem> valores = new ArrayList<SelectItem>();
		try {
			ZonaControllerStub Stub = new ZonaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			GetZonas param = new GetZonas();
			param.setIdUsuario(config != null ? config.getIdUsuario() : 20);
			param.setIdLDC(p_idLDC);
			param.setTipoCasoUso("REPORTES");
			ZonaControllerStub.GetZonasResponse response = Stub.getZonas(param);

			Zona[] arreglo = response.get_return();
			SelectItem itemSel = new SelectItem();
			itemSel.setLabel("Selecciona Zona");
			itemSel.setValue(0);
			valores.add(itemSel);
			if (arreglo != null) {
				for (Zona itemA : arreglo) {
					SelectItem item = new SelectItem();
					item.setLabel(itemA.getZona());
					item.setValue(itemA.getIdZona());
					valores.add(item);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valores;
	}

	protected List<SelectItem> obtenerSubBodegasPedidosDejados(
			int idSubBodegaAlmacen, int idZona, int idPais, int idLDC,
			int idUsuario) {

		List<SelectItem> valores = new ArrayList<SelectItem>();

		try {
			SubBodegaAlmacenControllerStub stub = new SubBodegaAlmacenControllerStub();
			ZonaControllerStub Stub = new ZonaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ObtenerSubBodegaAlmacen param = new ObtenerSubBodegaAlmacen();
			param.setP_idSubBodega(idSubBodegaAlmacen);
			param.setP_idZona(idZona);
			param.setP_idPais(idPais);
			param.setP_idLDC(idLDC);
			param.setP_idUsuario(idUsuario);

			SubBodegaAlmacenControllerStub.ObtenerSubBodegaAlmacenResponse response = stub
					.obtenerSubBodegaAlmacen(param);
			com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub.SubBodegaAlmacen[] arreglo = response
					.get_return();

			// SelectItem itemSel = new SelectItem();
			// itemSel.setLabel("Selecciona Sub bodega");
			// itemSel.setValue(0);
			// valores.add(itemSel);
			if (arreglo != null) {
				for (com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub.SubBodegaAlmacen itemA : arreglo) {
					SelectItem item = new SelectItem();
					item.setLabel(itemA.getClave() + " - "
							+ itemA.getDescripcion());
					item.setValue(itemA.getIdSubbodegaAlmacen());
					valores.add(item);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valores;

	}

	protected List<SelectItem> obtenerListaEntregadoA(int idLDC) {
		List<SelectItem> valores = new ArrayList<SelectItem>();
		try {
			ZonaControllerStub Stub = new ZonaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			GetZonas param = new GetZonas();
			param.setIdUsuario(1);
			param.setIdLDC(idLDC);
			param.setTipoCasoUso("REPORTESENTREGADOA");
			ZonaControllerStub.GetZonasResponse response = Stub.getZonas(param);

			Zona[] arreglo = response.get_return();
			SelectItem itemSel = new SelectItem();
			itemSel.setLabel("Seleccione Gerente");
			itemSel.setValue(0);
			valores.add(itemSel);
			if (arreglo != null) {
				for (Zona itemA : arreglo) {
					if (itemA.getNombreGerenteZonal() != null) {
						SelectItem item = new SelectItem();
						item.setLabel(itemA.getNombreGerenteZonal());
						item.setValue(itemA.getIdZona());
						valores.add(item);
					}
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valores;
	}

	public List<DestinatarioReporte> obtenerDestinatariosReporte(
			String nombreReporte) {
		List<DestinatarioReporte> arregloD = new ArrayList<ReportesControllerStub.DestinatarioReporte>();
		DestinatarioReporte[] arreglo = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ReportesControllerStub.ObtenerDestinatariosReportes param = new ReportesControllerStub.ObtenerDestinatariosReportes();
			param.setNombreReporte(nombreReporte);
			param.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerDestinatariosReportesResponse response = Stub
					.obtenerDestinatariosReportes(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				for (DestinatarioReporte des : arreglo) {
					arregloD.add(des);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arregloD;
	}

	public List<DestinatarioReporte> obtenerDestinatariosReportePorTipoReporte(
			String p_cveReporteTipo, int p_idLDC, int p_idUsuario) {
		List<DestinatarioReporte> arregloD = new ArrayList<ReportesControllerStub.DestinatarioReporte>();
		DestinatarioReporte[] arreglo = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ReportesControllerStub.ObtenerDestinatariosReportesPorTipoReporte param = new ReportesControllerStub.ObtenerDestinatariosReportesPorTipoReporte();
			param.setP_cveReporteTipo(p_cveReporteTipo);
			param.setP_idLDC(p_idLDC);
			param.setP_idUsuario(p_idUsuario);
			ReportesControllerStub.ObtenerDestinatariosReportesPorTipoReporteResponse response = Stub
					.obtenerDestinatariosReportesPorTipoReporte(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				for (DestinatarioReporte des : arreglo) {
					arregloD.add(des);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arregloD;
	}

	protected SubBodegaAlmacen[] obtenerSubodegas(int idSalidaReparto) {
		SubBodegaAlmacen[] arregloCampanias = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ReportesControllerStub.DevuelveSubBodegasSalidaReparto param = new ReportesControllerStub.DevuelveSubBodegasSalidaReparto();
			param.setIdUsuario((config != null ? config.getIdUsuario() : 20));
			param.setIdSalidaReparto(idSalidaReparto);
			ReportesControllerStub.DevuelveSubBodegasSalidaRepartoResponse response = Stub
					.devuelveSubBodegasSalidaReparto(param);

			arregloCampanias = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arregloCampanias;
	}

	protected ItemSubBodega[] obtenerListaItemsSubBodega(int idSubbodega,
			int idSalidaReparto) {
		ItemSubBodega[] arreglo = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			ReportesControllerStub.DevuelveOrdenesDejadasSubBodega param = new ReportesControllerStub.DevuelveOrdenesDejadasSubBodega();
			param.setIdSubbodega(idSubbodega);
			param.setIdSalidaReparto(idSalidaReparto);
			param.setIdUsuario(1);
			ReportesControllerStub.DevuelveOrdenesDejadasSubBodegaResponse response = Stub
					.devuelveOrdenesDejadasSubBodega(param);

			arreglo = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arreglo;
	}

	public ModelManifiestoRutaEnCampania obtenerReporteManifiestoRutaEnCampania(
			int p_idRuta, int p_idCampania, int p_idUsuario, int p_idLdc) {
		ModelManifiestoRutaEnCampania rep = null;
		List<ModelTablaManifiestoRutaEnCampania> lDet = null;

		ReportesControllerStub.ModelManifiestoRutaEnCampania reporte = new ReportesControllerStub.ModelManifiestoRutaEnCampania();
		ReportesControllerStub.ModelTablaManifiestoRutaEnCampania[] detalle;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania request = new ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania();

			// parámetros
			request.setP_idCampania(p_idCampania);
			request.setP_idRuta(p_idRuta);
			request.setP_idUsuario(p_idUsuario);
			request.setP_idLDC(p_idLdc);

			// invocamos
			ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampaniaResponse response = stub
					.obtenerReporteManifiestoRutaEnCampania(request);

			// obtenemos respuesta
			reporte = response.get_return();
			if (reporte != null) {
				rep = new ModelManifiestoRutaEnCampania();
				rep.setCajasTotales(reporte.getCajasTotales());
				rep.setCampania_Anio(reporte.getCampania_Anio());
				rep.setFecha(reporte.getFecha());
				rep.setNombreAyudante(reporte.getNombreAyudante());
				rep.setNombreChofer(reporte.getNombreChofer());
				rep.setOrdenesTotales(reporte.getOrdenesTotales());
				rep.setPoblacionPrincipal(reporte.getPoblacionPrincipal());
				rep.setPremiosTotales(reporte.getPremiosTotales());
				rep.setRuta(reporte.getRuta());
				rep.setUnitariosTotales(reporte.getUnitariosTotales());
				rep.setZona(reporte.getZona());

				// Obtenemos detalle
				ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania_Detalle request2 = new ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania_Detalle();

				// parámetros
				request2.setP_idCampania(p_idCampania);
				request2.setP_idRuta(p_idRuta);
				request2.setP_idUsuario(p_idUsuario);
				request2.setP_idLDC(p_idLdc);

				// invocamos
				ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania_DetalleResponse response2 = stub
						.obtenerReporteManifiestoRutaEnCampania_Detalle(request2);

				// obtenemos respuesta
				detalle = response2.get_return();

				if (detalle != null) {
					lDet = new ArrayList<ModelTablaManifiestoRutaEnCampania>();
					for (ReportesControllerStub.ModelTablaManifiestoRutaEnCampania registro : detalle) {
						ModelTablaManifiestoRutaEnCampania det = new ModelTablaManifiestoRutaEnCampania();
						det.setAccount(registro.getAccount());
						det.setAddress1(registro.getAddress1());
						det.setAddress2(registro.getAddress2());
						det.setAddress3(registro.getAddress3());
						det.setCajas(registro.getCajas());
						det.setEntregado(registro.getEntregado());
						det.setName(registro.getName());
						det.setPremios(registro.getPremios());
						det.setSecuenciaEntrega(registro.getSecuenciaEntrega());
						det.setToPay(registro.getToPay());
						det.setUnitarios(registro.getUnitarios());
						det.setVisitado(registro.getVisitado());

						lDet.add(det);
					}

					rep.setListaManifiesto(lDet);
				}
			}

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M1",
					"No se pudo consultar la información del reporte",
					ex.getMessage(), p_idUsuario);
		}

		return rep;
	}

	public List<com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen> obtenerItemsNoEntregadosEnAlmacen(
			int p_idRuta, int p_idCampania, int p_idUsuario, int idZona) {

		List<com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen> rep = new ArrayList<com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen>();

		ModelReporteItemsNoEntregadosEnAlmacen[] reporte = null;
		ReportesControllerStub.ModelTablaManifiestoRutaEnCampania[] detalle;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtenerItemsNoEntregadosEnAlmacen request = new ReportesControllerStub.ObtenerItemsNoEntregadosEnAlmacen();

			// parámetros

			request.setIdCampania("" + p_idCampania);
			request.setIdRuta("" + p_idRuta);
			request.setIdUsuario(p_idUsuario);
			request.setIdZona("" + idZona);

			// invocamos
			ReportesControllerStub.ObtenerItemsNoEntregadosEnAlmacenResponse response = stub
					.obtenerItemsNoEntregadosEnAlmacen(request);

			// obtenemos respuesta
			reporte = response.get_return();
			if (reporte != null) {
				for (ModelReporteItemsNoEntregadosEnAlmacen rp : reporte) {
					com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen r = new com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen();
					r.setCodigoItem(rp.getCodigoItem());
					r.setDescItem(rp.getDescItem());
					r.setFechaHoraDevolucion(rp.getFechaHoraDevolucion());
					r.setFechaHoraDevolucionS(rp.getFechaHoraDevolucionS());
					r.setNombreChofer(rp.getNombreChofer());
					r.setNombreRepresentante(rp.getNombreRepresentante());
					r.setNumeroOrden(rp.getNumeroOrden());
					r.setRegistro(rp.getRegistro());
					r.setRuta(rp.getRuta());
					r.setCampania(rp.getCampania());
					r.setZona(rp.getZona());
					rep.add(r);
				}
			}

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M1",
					"No se pudo consultar la información del reporte",
					ex.getMessage(), p_idUsuario);
		}

		return rep;
	}

	public List<ModelReporteConsultaEstatusOdenesRep> obtenerConsultaEstatusOrdenesRep(
			String anio, String mes, int p_idUsuario, String registro) {

		List<ModelReporteConsultaEstatusOdenesRep> rep = new ArrayList<ModelReporteConsultaEstatusOdenesRep>();

		try {
			ReporteConsultaEstatusOrdenesControllerStub stub = new ReporteConsultaEstatusOrdenesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ObtenerConsultaEstatusOrdenes request = new ObtenerConsultaEstatusOrdenes();

			// parámetros

			request.setAnio(Integer.parseInt(anio));
			request.setMes(Integer.parseInt(mes));
			request.setRegistro(registro);
			request.setIdUsuario(p_idUsuario);
			DecimalFormat def = new DecimalFormat("#,###.00");
			// invocamos
			ReporteConsultaEstatusOrdenesControllerStub.ObtenerConsultaEstatusOrdenesResponse response = stub
					.obtenerConsultaEstatusOrdenes(request);
			int idOrden = 0;
			// obtenemos respuesta
			ModelReporteConsultaEstatus[] reporte = response.get_return();
			Boolean entre = false;
			if (reporte != null) {
				List<ModelReporteConsultaEstatusOrdenesRepDetalle> detalle = new ArrayList<ModelReporteConsultaEstatusOrdenesRepDetalle>();
				ModelReporteConsultaEstatusOdenesRep reg = new ModelReporteConsultaEstatusOdenesRep();
				for (ModelReporteConsultaEstatus rp : reporte) {
					entre = true;
					// por cada registro tenemos que llenar el DTO del reporte y
					// el dto del detalle del reporte
					if (idOrden == 0) {
						// esporque es la primera vez

						idOrden = Integer.parseInt(rp.getIdOrden());

						reg.setIdOrden(idOrden);
						reg.setClaveOrden(rp.getClave_orden());
						reg = dividirReg(reg, rp.getPosiciones());
						if (rp.getFoto() != null) {
							reg.setFoto(devolverFotoReporte(rp.getFoto()));

						}
						reg.setPagos(rp.getPagos());
						reg.setZona(rp.getZona());
						reg.setNombre(rp.getNombre());
						reg.setMontoCobrar(rp.getMonto());
						reg.setMontoCobrarS(def.format(reg.getMontoCobrar()));
						reg.setEstatusActual(rp.getUltimoEstatus());
						reg.setUltimoEstatus(rp.getUltimoEstatusNum());

						if (reg.getUltimoEstatus() != null
								&& (reg.getUltimoEstatus().equals("8")
										|| reg.getUltimoEstatus().equals("9")
										|| reg.getUltimoEstatus().equals("12") || reg
										.getUltimoEstatus().equals("13"))) {
							if (rp.getHhadmin() != null) {
								// quiere decir que esta entregada y se tiene
								// que concatenar - HH
								reg.setEstatusActual(reg.getEstatusActual()
										+ " - HH");
							} else {
								// quiere decir que esta entregada y se tiene
								// que concatenar - Admin
								reg.setEstatusActual(reg.getEstatusActual()
										+ " - Admin");
							}
						}

						ModelReporteConsultaEstatusOrdenesRepDetalle det = new ModelReporteConsultaEstatusOrdenesRepDetalle();
						det.setCodigoBarras(rp.getCodigoBarras());
						det.setEstatusItem(rp.getEstatusItem());
						det.setFechaEstatus(rp.getFechaEstatus());
						det.setItem(rp.getItem());

						det.setTipoItem(rp.getTipoItem());
						det.setUsuario(rp.getUsuario());
						detalle.add(det);

					} else {
						// tenemos que evaluar si sigue siendo la misma orden
						if (idOrden == Integer.parseInt(rp.getIdOrden())) {
							// es porque sigue siendo la misma orden, solo
							// aumentamos detalle
							ModelReporteConsultaEstatusOrdenesRepDetalle det = new ModelReporteConsultaEstatusOrdenesRepDetalle();
							det.setCodigoBarras(rp.getCodigoBarras());
							det.setEstatusItem(rp.getEstatusItem());
							det.setFechaEstatus(rp.getFechaEstatus());
							det.setItem(rp.getItem());
							det.setTipoItem(rp.getTipoItem());
							det.setUsuario(rp.getUsuario());
							detalle.add(det);

						} else {
							// es diferente orden
							// primero seteamos el detalle en el anterior
							reg.setItems(detalle);
							// luego agregamos el registro a la salida
							rep.add(reg);
							// luego hacemos nuevo el registro
							reg = new ModelReporteConsultaEstatusOdenesRep();
							// actualizamos idOrden
							idOrden = Integer.parseInt(rp.getIdOrden());

							// lo seteamos completo
							reg.setIdOrden(idOrden);
							reg = dividirReg(reg, rp.getPosiciones());

							if (rp.getFoto() != null) {
								reg.setFoto(devolverFotoReporte(rp.getFoto()));
							}
							reg.setPagos(rp.getPagos());
							reg.setEstatusActual(rp.getUltimoEstatus());
							reg.setUltimoEstatus(rp.getUltimoEstatusNum());

							if (reg.getUltimoEstatus() != null
									&& (reg.getUltimoEstatus().equals("8")
											|| reg.getUltimoEstatus().equals(
													"9")
											|| reg.getUltimoEstatus().equals(
													"12") || reg
											.getUltimoEstatus().equals("13"))) {
								if (rp.getHhadmin() != null) {
									// quiere decir que esta entregada y se
									// tiene
									// que concatenar - HH
									reg.setEstatusActual(reg.getEstatusActual()
											+ " - HH");
								} else {
									// quiere decir que esta entregada y se
									// tiene
									// que concatenar - Admin
									reg.setEstatusActual(reg.getEstatusActual()
											+ " - Admin");
								}
							}

							// inicializamos el detalle nuevamente
							detalle = new ArrayList<ModelReporteConsultaEstatusOrdenesRepDetalle>();
							ModelReporteConsultaEstatusOrdenesRepDetalle det = new ModelReporteConsultaEstatusOrdenesRepDetalle();
							det.setCodigoBarras(rp.getCodigoBarras());
							det.setEstatusItem(rp.getEstatusItem());
							det.setFechaEstatus(rp.getFechaEstatus());
							det.setItem(rp.getItem());
							det.setTipoItem(rp.getTipoItem());
							det.setUsuario(rp.getUsuario());
							detalle.add(det);

						}
					}
				}

				if (entre) {
					// al final traemos un detall sin setear
					reg.setItems(detalle);
					rep.add(reg);
				}
			}

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M1",
					"No se pudo consultar la información del reporte",
					ex.getMessage(), p_idUsuario);
		}

		return rep;
	}

	private ModelReporteConsultaEstatusOdenesRep dividirReg(
			ModelReporteConsultaEstatusOdenesRep reg, String valores) {
		String[] datos = valores.split("\\|");

		if (datos != null && datos.length == 7) {
			reg.setLatitud(datos[0]);
			reg.setLongitud(datos[1]);
			reg.setCausaDevolucion(datos[2]);
			reg.setQuienInforma(datos[3]);
			reg.setQuienRecibe(datos[4]);
			reg.setTieneFoto(datos[5]);
			if (datos[6].equals("NULL")) {
				reg.setFechaReparto("");
			} else {
				reg.setFechaReparto(datos[6]);
			}
		}

		return reg;
	}

	protected List<ModelRepSalidaCamioneta> datosReporteSalidaCamioneta(
			int idCampania, int ruta, int zona, int idLDC, Date fecha) {

		List<ModelRepSalidaCamioneta> salidas = new ArrayList<ModelRepSalidaCamioneta>();
		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			// se llena el header de la salida
			ReportesControllerStub.ObtenerHeadersSalidaCamioneta param = new ReportesControllerStub.ObtenerHeadersSalidaCamioneta();
			param.setCampania(idCampania);
			param.setRuta(ruta);
			param.setZona(zona);
			param.setFecha(fecha);
			param.setIdLDC(idLDC);
			param.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerHeadersSalidaCamionetaResponse resHeader = stub
					.obtenerHeadersSalidaCamioneta(param);

			SalidaCamioneta sal = resHeader.get_return();
			ModelRepSalidaCamioneta salida = new ModelRepSalidaCamioneta();
			if (sal != null) {
				salida.setCajas(sal.getCajas());
				salida.setCampania(sal.getCampania());
				salida.setOrdenes(sal.getOrdenes());
				salida.setUnitarios(sal.getUnitarios());
				salida.setPremios(sal.getPremios());
				salida.setNombreChofer(sal.getNombreChofer());
				salida.setNombreAyudante(sal.getNombreAyudante());
				salida.setZona(sal.getZona());
				salida.setRuta(sal.getRuta());
				if (sal.getFechaSalidaReparto() != null) {
					SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
							"dd/MM/yyyy");
					Date fechaSalida = null;
					fechaSalida = formatoDeFecha.parse(sal
							.getFechaSalidaReparto());
					salida.setFecha(fechaSalida);
				}
			}
			// se llena los kilometros y las hrs
			ReportesControllerStub.ObtenerKilometrosHorasSalidaCamioneta paramkmhr = new ReportesControllerStub.ObtenerKilometrosHorasSalidaCamioneta();
			paramkmhr.setCampania(idCampania);
			paramkmhr.setFecha(fecha);
			paramkmhr.setRuta(ruta);
			paramkmhr.setZona(zona);
			paramkmhr.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerKilometrosHorasSalidaCamionetaResponse resKmHr = stub
					.obtenerKilometrosHorasSalidaCamioneta(paramkmhr);
			SalidaCamioneta KilHor = resKmHr.get_return();
			if (KilHor != null) {
				salida.setHrPrimerVisita(KilHor.getHrPrimerVisita());
				salida.setHrRegresoBodega(KilHor.getHrRegresoBodega());
				salida.setHrSalidaReparto(KilHor.getHrSalidaReparto());
				salida.setHrUltimaVisita(KilHor.getHrUltimaVisita());
				salida.setKmPrimerVisita(KilHor.getKmPrimerVisita());
				salida.setKmRegresoBodega(KilHor.getKmRegresoBodega());
				salida.setKmSalidaReparto(KilHor.getKmSalidaReparto());
				salida.setKmUltimaVisita(KilHor.getKmUltimaVisita());
			}
			// se llenan las liquidaciones
			ReportesControllerStub.ObtenerLiquidacionesSalidaCamioneta paramliq = new ReportesControllerStub.ObtenerLiquidacionesSalidaCamioneta();
			paramliq.setCampania(idCampania);
			paramliq.setFecha(fecha);
			paramliq.setRuta(ruta);
			paramliq.setZona(zona);
			paramliq.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerLiquidacionesSalidaCamionetaResponse resLiq = stub
					.obtenerLiquidacionesSalidaCamioneta(paramliq);
			LiquidacionRep[] liquidaciones = resLiq.get_return();
			List<LiquidacionesRep> liquidacionesRep = new ArrayList<LiquidacionesRep>();
			if (liquidaciones != null) {

				for (LiquidacionRep liq : liquidaciones) {
					LiquidacionesRep liqrep = new LiquidacionesRep();
					liqrep.setCajas(liq.getCajas());
					liqrep.setCods(liq.getCods());
					liqrep.setConcepto(liq.getConcepto());
					liqrep.setPremios(liq.getPremios());
					liqrep.setUnitarios(liq.getUnitarios());
					liqrep.setRecibio(liq.getRecibio());
					liquidacionesRep.add(liqrep);
				}
			}
			salida.setLiquidaciones(liquidacionesRep);
			// se llenan los desgloces de efectivo

			ReportesControllerStub.ObtenerDesgloceEfectivo paramdes = new ReportesControllerStub.ObtenerDesgloceEfectivo();
			paramdes.setCampania(idCampania);
			paramdes.setFecha(fecha);
			paramdes.setRuta(ruta);
			paramdes.setZona(zona);
			paramdes.setIdUsuario(config.getIdUsuario());
			ReportesControllerStub.ObtenerDesgloceEfectivoResponse resDes = stub
					.obtenerDesgloceEfectivo(paramdes);
			DesgloceEfectivo[] desgloce = resDes.get_return();
			List<com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo> desgloceBilletes = new ArrayList<com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo>();
			List<com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo> desgloceMonedas = new ArrayList<com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo>();
			if (desgloce != null) {
				// String granTotal = "GRAN TOTAL: ";
				String totalBilletes = "TOTAL BILLETES: ";
				String totalModenas = "TOTAL MONEDAS: ";
				double efectivoTotal = 0;
				for (DesgloceEfectivo desw : desgloce) {
					com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo des = new com.datacode.avon_ots_admin.reports.model.DesgloceEfectivo();
					des.setCantidad(desw.getCantidad());
					des.setDenominacion(desw.getDenominacion());
					des.setSubTotal(desw.getSubTotal());
					des.setTotalParcial(desw.getSubTotal());
					des.setTotal(desw.getSubTotal());
					if (desw.getBillete_Moneda().equals("B")
							|| totalBilletes.equals(desw.getBillete_Moneda())) {
						if (desw.getDenominacion() != null) {
							desgloceBilletes.add(des);
							efectivoTotal += des.getSubTotal();
						}
					} else if ((desw.getBillete_Moneda().equals("M"))
							|| totalModenas.equals(desw.getBillete_Moneda())) {
						if (desw.getDenominacion() != null) {
							desgloceMonedas.add(des);
							efectivoTotal += des.getSubTotal();
						}
					}
				}
				salida.setEfectivoTotal(efectivoTotal);
			}
			salida.setDesgloceBilletes(desgloceBilletes);
			salida.setDesgloceMoneda(desgloceMonedas);
			salidas.add(salida);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return salidas;
	}

	/**
	 * Invoca el WS que obtiene los datos de [NoAceptacionReparto]
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 * @param p_idZona
	 * @param p_idCampania
	 * @param p_idUsuario
	 * @return List[NoAceptacionReparto]
	 */
	public List<NoAceptacionReparto> obtenerDatosNAR(int p_idZona,
			int p_idCampania, int p_idUsuario, String descZona, String descCamp) {
		List<NoAceptacionReparto> nar = new ArrayList<NoAceptacionReparto>();
		com.datacode.avon_ots_ws.ReportesControllerStub.NoAceptacionReparto[] arrData = null;

		List<NoAceptacionRepartoDetalle> det = new ArrayList<NoAceptacionRepartoDetalle>();
		com.datacode.avon_ots_ws.ReportesControllerStub.NoAceptacionRepartoDetalle[] lstData = null;

		ReportesControllerStub ct = null;
		ReportesControllerStub.GetObtenerNoAceptacionRepartoExistentes req = null;
		ReportesControllerStub.GetObtenerNoAceptacionRepartoExistentesResponse res = null;

		ReportesControllerStub.GetDetalleNoAceptacionReparto req1 = null;
		ReportesControllerStub.GetDetalleNoAceptacionRepartoResponse res1 = null;

		try {
			ct = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient()
					.getOptions().getTo().getAddress());
			ct._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Agrega al request la operacion a ejecutar
			req1 = new ReportesControllerStub.GetDetalleNoAceptacionReparto();
			req1.setIdCampania(p_idCampania);
			req1.setIdZona(p_idZona);
			req1.setIdUser(p_idUsuario);
			res1 = ct.getDetalleNoAceptacionReparto(req1);
			// variable heredada del WS
			lstData = res1.get_return();
			if (lstData != null) {
				// Ciclo que obtiene los datos del WS
				for (int i = 0; i < lstData.length; i++) {
					// Añade los datos a la lista de forma local mediante el
					// constructor
					det.add(new NoAceptacionRepartoDetalle(lstData[i].getNo(),
							lstData[i].getRegistro(), lstData[i].getNombre(),
							lstData[i].getDireccion(), lstData[i]
									.getEntreCalles(), lstData[i]
									.getPoblacion(), lstData[i].getValorCOD(),
							lstData[i].getValorPedido(), lstData[i].getRed(),
							lstData[i].getCajas(), lstData[i].getUnitarios(),
							lstData[i].getGeoReferencia(), lstData[i]
									.getHrVisita(), lstData[i]
									.getCausaDevolucion()));
				}
			}
			if (det.size() > 0) {
				ct = new ReportesControllerStub();

				// Obtiene y asigna url de configuración de web services
				url = Utils.modificarUrlServicioWeb(ct._getServiceClient()
						.getOptions().getTo().getAddress());
				ct._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				// Agrega al request la operacion a ejecutar
				req = new ReportesControllerStub.GetObtenerNoAceptacionRepartoExistentes();
				req.setIdCampania(p_idCampania);
				req.setIdZona(p_idZona);
				req.setIdUser(p_idUsuario);
				res = ct.getObtenerNoAceptacionRepartoExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				// Ciclo que obtiene los datos del WS
				for (int i = 0; i < arrData.length; i++) {
					// Añade los datos a la lista de forma local mediante el
					// constructor
					// NoAceptacionRepartoDetalle d =
					// (NoAceptacionRepartoDetalle)
					// arrData[i].getLstNoAceptacionReparto();
					nar.add(new NoAceptacionReparto(descZona, descCamp,
							arrData[i].getPorcentaAceptacion(), arrData[i]
									.getMiVentaNAR(), arrData[i]
									.getOrdenPendienteEntregar(), det));
				}
			}

		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M3",
					"Error al invocar el servicios que consulta los datos de No Aceptacion de Reparto.",
					excepcionDeInvocacion.toString(), p_idUsuario);
		} finally {
		}

		return nar;
	}

	/**
	 * Invoca el WS que obtiene los datos de [NoAceptacionRepartoTotal]
	 * 
	 * @author brenda.estrada
	 * @since 20/02/2012
	 * @param p_idZona
	 * @param p_idCampania
	 * @param p_idUsuario
	 * @param descZona
	 * @param descCamp
	 * @return List[NoAceptacionRepartoTotal]
	 */
	public List<NoAceptacionRepartoTotal> obtenerDatosNARTotal(int p_idZona,
			int p_idCampania, int p_idUsuario, String descZona, String descCamp) {
		List<NoAceptacionRepartoTotal> nart = new ArrayList<NoAceptacionRepartoTotal>();
		com.datacode.avon_ots_ws.ReportesControllerStub.NoAceptacionRepartoTotal[] arrData = null;
		// Detalles 1
		List<NoAceptacionRepartoTotalDetRED> det1 = new ArrayList<NoAceptacionRepartoTotalDetRED>();
		com.datacode.avon_ots_ws.ReportesControllerStub.NoAceptacionRepartoTotalDetRED[] lstData1 = null;
		// Detalles 2
		List<NoAceptacionRepartoTotalDetLibres> det2 = new ArrayList<NoAceptacionRepartoTotalDetLibres>();
		// com.datacode.avon_ots_ws.ReportesControllerStub.NoAceptacionRepartoTotalDetLibres[]
		// lstData2 = null;

		ReportesControllerStub ct = null;
		ReportesControllerStub.GetObtenerNARTotalExistentes req = null;
		ReportesControllerStub.GetObtenerNARTotalExistentesResponse res = null;
		// Obtener Detalles
		ReportesControllerStub.GetDetalleNARTotalRED req1 = null;
		ReportesControllerStub.GetDetalleNARTotalREDResponse res1 = null;
		// ReportesControllerStub.GetDetalleNARTotalLibres req2 = null;
		// ReportesControllerStub.GetDetalleNARTotalLibresResponse res2 = null;

		try {
			// Obtener Detalle 1 de NAR Total
			ct = new ReportesControllerStub();
			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient()
					.getOptions().getTo().getAddress());
			ct._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Agrega al request la operacion a ejecutar -Obtener DEtalle 1
			req1 = new ReportesControllerStub.GetDetalleNARTotalRED();
			req1.setIdCampania(p_idCampania);
			req1.setIdZona(p_idZona);
			req1.setIdUser(p_idUsuario);
			res1 = ct.getDetalleNARTotalRED(req1);
			lstData1 = res1.get_return();
			if (lstData1 != null && lstData1.length > 0) {
				for (int i = 0; i < lstData1.length; i++) {
					det1.add(new NoAceptacionRepartoTotalDetRED(lstData1[i]
							.getNoRed(), lstData1[i]
							.getPorcentajeAceptacionRed(), lstData1[i]
							.getOrdenDevolucion(), lstData1[i]
							.getSumValorPedidoRed(), lstData1[i]
							.getTotalOrdenes()));

					det2.add(new NoAceptacionRepartoTotalDetLibres(lstData1[i]
							.getOrdenLibreCobroNoEntregado(), lstData1[i]
							.getSumValorPedidoLibre(), lstData1[i]
							.getTotalLibreCobro(), lstData1[i]
							.getPorcentAceptacionLibresRed()));
				}

				// // Agrega al request la operacion a ejecutar -Obtener DEtalle
				// 2
				// ct = new ReportesControllerStub();
				// // Obtiene y asigna url de configuración de web services
				// String url1 =
				// Utils.modificarUrlServicioWeb(ct._getServiceClient()
				// .getOptions().getTo().getAddress());
				// ct._getServiceClient()
				// .getOptions()
				// .setTo(new org.apache.axis2.addressing.EndpointReference(
				// url1));
				// // Agrega al request la operacion a ejecutar -Obtener DEtalle
				// 1
				// req2 = new ReportesControllerStub.GetDetalleNARTotalLibres();
				// req2.setIdCampania(p_idCampania);
				// req2.setIdZona(p_idZona);
				// req2.setIdUser(p_idUsuario);
				// res2 = ct.getDetalleNARTotalLibres(req2);
				// // Obtiene la respuesta del WS
				// lstData2 = res2.get_return();
				// if (lstData2.length > 0) {
				// for (int i = 0; i < lstData2.length; i++) {
				// det2.add(new NoAceptacionRepartoTotalDetLibres(lstData1[i]
				// .getOrdenDevolucion(), lstData1[i]
				// .getSumValorPedidoRed(), lstData1[i]
				// .getTotalOrdenes(), lstData1[i]
				// .getPorcentajeAceptacionRed()));
				// }
				// }

				// Obtener Encabezado del Reporte
				// if(det1.size()>0 || det2.size() > 0){ //Validar si se va a
				// mostrar lso datos auqneu detalles sean null
				ct = new ReportesControllerStub();
				// Obtiene y asigna url de configuración de web services
				url = Utils.modificarUrlServicioWeb(ct._getServiceClient()
						.getOptions().getTo().getAddress());
				ct._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				// Agrega al request la operacion a ejecutar
				req = new ReportesControllerStub.GetObtenerNARTotalExistentes();
				req.setIdCampania(p_idCampania);
				req.setIdZona(p_idZona);
				req.setIdUser(p_idUsuario);
				res = ct.getObtenerNARTotalExistentes(req);
				// Obtiene Valores del WS
				arrData = res.get_return();
				if (arrData.length > 0) {
					// for (int i = 0; i < arrData.length; i++) {
					nart.add(new NoAceptacionRepartoTotal(descZona, descCamp,
							arrData[0].getPorcentaAceptacion(), arrData[0]
									.getCantidadOrdenes(), arrData[0]
									.getImporteTotalZona(), arrData[0]
									.getTotalOrdenes(), arrData[0]
									.getCantidadLibresCobro(), arrData[0]
									.getPorcentaAceptacionLibreCobro(),
							arrData[0].getSumatoriaTotalOrdenesLibreCobro(),
							arrData[0].getOrdenesLibresCobroTotales(), det1,
							det2));
					// }
				}
			}
			// }
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M4",
					"Error al invocar el servicio que consulta los datos de No Aceptacion de Reparto Total.",
					excepcionDeInvocacion.toString(), p_idUsuario);
		} finally {
		}
		return nart;
	}

	public List<ModelRepDescargaEnrutado> obtenerDescargaEnrutado(Integer zona,
			Integer campania, Integer anioCampania, Integer idLDC) {
		List<ModelRepDescargaEnrutado> arregloD = new ArrayList<ModelRepDescargaEnrutado>();
		ModelRepDescargaEnrutado D = new ModelRepDescargaEnrutado();

		com.datacode.avon_ots_ws.ReportesControllerStub.ModelDescargaEnrutado[] arreglo = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			Stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtieneDatosDescargaEnrutado param = new ReportesControllerStub.ObtieneDatosDescargaEnrutado();
			param.setZona(zona);
			param.setCampania(campania);
			param.setAnioCampania(anioCampania);
			param.setIdUsuario(config.getIdUsuario());
			param.setIdLDC(idLDC);

			ReportesControllerStub.ObtieneDatosDescargaEnrutadoResponse response = Stub
					.obtieneDatosDescargaEnrutado(param);

			arreglo = response.get_return();
			List<ModelTablaDescargaEnrutado> tabla = new ArrayList<ModelTablaDescargaEnrutado>();
			long ordenesZonaLLegada = 0;
			long cajasBultosLlegada = 0;
			long ordenesZonaRecibo = 0;
			long cajasBultosRecibo = 0;
			long tEnCedisSeconds = 0;
			long tTotalDescarga = 0;
			long tTotalEnrutado = 0;
			long totalPersoEnrutado = 0;
			long totalPersoDescarga = 0;
			long totalProDescarga = 0;
			long totalProEnrutado = 0;
			if (arreglo != null) {
				for (com.datacode.avon_ots_ws.ReportesControllerStub.ModelDescargaEnrutado des : arreglo) {
					ModelTablaDescargaEnrutado d = new ModelTablaDescargaEnrutado();
					d.setOrdZonaCSF(des.getOrdenesZonaLlegada());
					if (des.getOrdenesZonaLlegada() != null
							&& !des.getOrdenesZonaLlegada().equals("")) {
						ordenesZonaLLegada += Long.parseLong(des
								.getOrdenesZonaLlegada());
					}
					d.setCajZonaCSF(des.getCajasZonaLlegada());
					if (des.getCajasZonaLlegada() != null
							&& !des.getCajasZonaLlegada().equals("")) {
						cajasBultosLlegada += Long.parseLong(des
								.getCajasZonaLlegada());
					}
					d.setCajOtrZonaCSF(des.getCajasOtraZonaLlegada());
					// if (!des.getCajasOtraZonaLlegada().equals("")) {
					// cajasBultosLlegada += Long.parseLong(des
					// .getCajasOtraZonaLlegada());
					// }
					d.setPreZonaCSF(des.getPremiosZonaLlegada());
					if (des.getPremiosZonaLlegada() != null
							&& !des.getPremiosZonaLlegada().equals("")) {
						cajasBultosLlegada += Long.parseLong(des
								.getPremiosZonaLlegada());
					}
					d.setOtrZonaCSF(des.getOtrosZonaLlegada());
					if (des.getOtrosZonaLlegada() != null
							&& !des.getOtrosZonaLlegada().equals("")
							&& !des.getOtrosZonaLlegada().equals("NA")) {
						cajasBultosLlegada += Long.parseLong(des
								.getOtrosZonaLlegada());
					}
					d.setOrdZona1REC(des.getOrdenesZonaDescargados());
					if (des.getOrdenesZonaDescargados() != null
							&& !des.getOrdenesZonaDescargados().equals("")) {
						ordenesZonaRecibo += Long.parseLong(des
								.getOrdenesZonaDescargados());
					}
					d.setCajZona1REC(des.getCajasZonaDescargados());
					if (des.getCajasZonaDescargados() != null
							&& !des.getCajasZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getCajasZonaDescargados());
					}
					d.setPreZona1REC(des.getPremiosZonaDescargados());
					if (des.getPremiosZonaDescargados() != null
							&& !des.getPremiosZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getPremiosZonaDescargados());
					}
					d.setOtrZona1REC(des.getOtrosZonaDescargados());
					if (des.getOtrosZonaDescargados() != null
							&& !des.getOtrosZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getOtrosZonaDescargados());
					}
					d.setOrdZona2REC(des.getOrdenesOtraZonaDescargados());
					if (des.getOrdenesOtraZonaDescargados() != null
							&& !des.getOrdenesOtraZonaDescargados().equals("")) {
						ordenesZonaRecibo += Long.parseLong(des
								.getOrdenesOtraZonaDescargados());
					}
					d.setCajZona2REC(des.getCajasOtraZonaDescargados());
					if (des.getCajasOtraZonaDescargados() != null
							&& !des.getCajasOtraZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getCajasOtraZonaDescargados());
					}
					d.setPreZona2REC(des.getPremiosOtraZonaDescargados());
					if (des.getPremiosOtraZonaDescargados() != null
							&& !des.getPremiosOtraZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getPremiosOtraZonaDescargados());
					}
					d.setOtrZona2REC(des.getOtrosOtraZonaDescargados());
					if (des.getOtrosOtraZonaDescargados() != null
							&& !des.getOtrosOtraZonaDescargados().equals("")) {
						cajasBultosRecibo += Long.parseLong(des
								.getOtrosOtraZonaDescargados());
					}

					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					d.setCampania(des.getCampania());
					d.setComentarios(des.getComentarios());
					d.setFechaLleProgramada(des.getFechaLlegadaProg());
					d.setFechaLleReal(des.getFechaLlegadaReal());
					d.setFechaSalReal(des.getFechaSalidaReal());
					d.setHrFinDescarga(des.getHrFinDescarga());
					d.setHrFinEnrutado(des.getHrFinEnrutado());
					d.setHrIniDescarga(des.getHrIniDesgarga());
					d.setHrIniEnrutado(des.getHrIniEnrutado());
					d.setHrLleProgramada(des.getHrLlegadaProg());
					d.setHrLleReal(des.getHrLlegadaReal());
					d.setHrSalReal(des.getHrSalidaReal());
					d.setProductividadDescarga(des.getProductividadDescarga());
					d.setProductividadEnrutado(des.getProductividadEnrutado());
					d.setStatusCarga(des.getStatusCarga());
					d.setTiempoEnCedis(des.getTiempoEnCedis());

					d.setTiempoTotalDesacarga(des.getTiempoTotalDescarga());
					d.setTiempoTotalEnrutado(des.getTiempoTotalEnrutado());
					d.setTotZonaREC(des.getTotalDescargados());
					d.setTotZonaCSF(des.getTotalLlegada());
					d.setZona(des.getZona());
					d.setNoPersonasEnrutado(des.getCantidadPersonasEnrutado());

					d.setNoPersonasDescarga(des.getCantidadPersonasDescarga());

					// try {
					totalPersoDescarga += Long.parseLong(des
							.getCantidadPersonasDescarga());
					totalPersoEnrutado += Long.parseLong(des
							.getCantidadPersonasEnrutado());
					totalProDescarga += Long.parseLong(des
							.getProductividadDescarga());
					totalProEnrutado += Long.parseLong(des
							.getProductividadEnrutado());

					/*
					 * } catch (Exception e) { e.printStackTrace(); }
					 */

					tabla.add(d);
					// calculos de promedios de tiempos
					// try {
					Calendar calendar = Calendar.getInstance();
					if (des.getTiempoEnCedis() != null
							&& !des.getTiempoEnCedis().equals("")) {
						Date tCedisD = sdf.parse(des.getTiempoEnCedis());
						calendar.setTime(tCedisD); // assigns calendar to given
													// date
						int hora = calendar.get(Calendar.HOUR_OF_DAY);
						int minu = calendar.get(Calendar.MINUTE);
						tEnCedisSeconds += (hora * 60 * 60) + (minu * 60);
					}

					if (des.getTiempoTotalDescarga() != null
							&& !des.getTiempoTotalDescarga().equals("")) {
						Date tTDes = sdf.parse(des.getTiempoTotalDescarga());
						calendar.setTime(tTDes);
						int hora = calendar.get(Calendar.HOUR_OF_DAY);
						int minu = calendar.get(Calendar.MINUTE);
						tTotalDescarga += (hora * 60 * 60) + (minu * 60);
					}

					if (des.getTiempoTotalEnrutado() != null
							&& !des.getTiempoTotalEnrutado().equals("")) {
						Date tTEnRu = sdf.parse(des.getTiempoTotalEnrutado());
						calendar.setTime(tTEnRu);
						int hora = calendar.get(Calendar.HOUR_OF_DAY);
						int minu = calendar.get(Calendar.MINUTE);
						tTotalEnrutado += (hora * 60 * 60) + (minu * 60);
					}

					/*
					 * } catch (ParseException e) { // TODO Auto-generated catch
					 * block e.printStackTrace(); } catch (NullPointerException
					 * ne) { ne.printStackTrace(); }
					 */

				}
				D.setListaDescargaEnrutado(tabla);
				D.setOrdenesCSF(ordenesZonaLLegada);
				D.setCajasBultosCSF(cajasBultosLlegada);
				D.setOrdenesREC(ordenesZonaRecibo);
				D.setCajasBultosREC(cajasBultosRecibo);
				if (tabla.size() > 0)// validacion para no dividir entre 0
				{
					long promEnCedis = tEnCedisSeconds / tabla.size();
					D.setTiempoPromEnCedis(formatIntoHHMMSS(promEnCedis));
					long promDescarga = tTotalDescarga / tabla.size();
					D.setTiempoProDescarga(formatIntoHHMMSS(promDescarga));
					long promEnrutado = tTotalEnrutado / tabla.size();
					D.setTiempoProEnrutado(formatIntoHHMMSS(promEnrutado));
					D.setPromPersonasDescarga(totalPersoDescarga / tabla.size());
					D.setPromPersonasEnrutado(totalPersoEnrutado / tabla.size());
					D.setProductividadDescarga(totalProDescarga / tabla.size());
					D.setProductividadEnrutado(totalProEnrutado / tabla.size());
				}

				D.setNoEmbarques(tabla.size());
				arregloD.add(D);
			}

		} catch (AxisFault e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
					e.toString(), config.getIdUsuario());
			arregloD = null;
			e.printStackTrace();
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
					e.toString(), config.getIdUsuario());
			arregloD = null;
			e.printStackTrace();
		} catch (ParseException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
					e.toString(), config.getIdUsuario());
			arregloD = null;
			e.printStackTrace();
		} catch (NullPointerException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
					e.toString(), config.getIdUsuario());
			arregloD = null;
			e.printStackTrace();
		} catch (Exception e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
					e.toString(), config.getIdUsuario());
			arregloD = null;
			e.printStackTrace();
		}

		return arregloD;
	}

	public ModelHeaderReparto obtenerDatosReparto(Integer zona, int campania,
			int anioCampania, int idLdc, int idUsuario) throws Exception {

		ModelHeaderReparto header = null;

		ModelReparto[] arreglo = null;
		try {
			ReportesControllerStub Stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			Stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtieneDatosReparto param = new ReportesControllerStub.ObtieneDatosReparto();
			param.setZona(zona);
			param.setCampania(campania);
			param.setAnioCampania(anioCampania);
			param.setIdLdc(idLdc);
			param.setIdUsuario(idUsuario);

			ReportesControllerStub.ObtieneDatosRepartoResponse response = Stub
					.obtieneDatosReparto(param);

			arreglo = response.get_return();
			GeneraReporteReparto r = new GeneraReporteReparto();
			List<ModelReparto> arreglo2 = r.obtenerDatosTest();
			List<ModelRepReparto> tabla = new ArrayList<ModelRepReparto>();
			// variables para los totales del header

			long envTOrdCReparDirecto = 0;
			long envTOrdSReparDirecto = 0;
			long envTPrimerasOrdenes = 0;
			long envTOrdenesTotales = 0;
			long envTCajasTotales = 0;
			double envTCajOrd = 0;
			long envTPremios = 0;
			long kmTInicioRuta = 0;
			long segTInicioRuta = 0;
			long kmTPrimeraEntrega = 0;
			long segTPrimeraEntrega = 0;
			long kmTUltimaEntrega = 0;
			long segTUltimaEntrega = 0;
			long kmTFinRuta = 0;
			long segTFinRuta = 0;
			long kmTotRepartoGlobal = 0;
			long segTRepartoGlobal = 0;
			long kmTotRepartoEfectivo = 0;
			long segTRepartoEfectivo = 0;
			long kmTotArrastre = 0;
			long segTArrastre = 0;
			double renTotalLitros = 0;
			long entTPrimeraOrden = 0;
			long entTOrdenesEstablecidas = 0;
			long entTCajasTotales = 0;
			long entTPremios = 0;
			double acpTPremios = 0;
			double acpTPrimerasOrdenes = 0;
			double acpTTotal = 0;
			long devTOrdenes = 0;
			long devTCajas = 0;
			long devTPremios = 0;
			long CauCambioDom = 0;
			long CauCerradoTotal = 0;
			long CauDifEnCobro = 0;
			long CauDiferencia = 0;
			long CauDomIncompleto = 0;
			long CauExtravioFicha = 0;
			long CauFueraZona = 0;
			long CauNoDejoFicha = 0;
			long CauNoEsperaReparto = 0;
			long CauNoMetioPedido = 0;
			long CauNoPago = 0;
			long CauNoViveAhi = 0;
			long CauOtros = 0;

			if (arreglo != null) {
				header = new ModelHeaderReparto();
				for (ModelReparto ds : arreglo) {
					ModelRepReparto d = new ModelRepReparto();
					d.setAnio(ds.getAnio());
					d.setCampania(ds.getCampania());
					d.setZona(ds.getZona());
					d.setRuta(ds.getRuta());
					d.setPoblacion(ds.getPoblacion());
					d.setTipoRuta(ds.getTipoRuta());
					d.setFechaReparto(ds.getFechaReparto());
					d.setDiaReparto(ds.getDiaReparto());
					d.setNombreChofer(ds.getNombreChofer());
					d.setNombreAyudante(ds.getNombreAyudante());
					d.setEnvOrdenesCReparto(ds.getEnvOrdenesCReparto());
					envTOrdCReparDirecto += ds.getEnvOrdenesCReparto();// th
					d.setEnvOrdenesSReparto(ds.getEnvOrdenesSReparto());
					envTOrdSReparDirecto += ds.getEnvOrdenesSReparto();// th
					d.setEnvPrimerasOrdenes(ds.getEnvPrimerasOrdenes());
					envTPrimerasOrdenes += ds.getEnvPrimerasOrdenes();// th
					int ordenesTotales = ds.getEnvOrdenesCReparto()
							+ ds.getEnvOrdenesSReparto()
							+ ds.getEnvPrimerasOrdenes();
					envTOrdenesTotales += ordenesTotales;// th
					d.setEnvOrdenesTotales(ordenesTotales);
					d.setEnvCajasTotales(ds.getEnvCajasTotales());
					envTCajasTotales += ds.getEnvCajasTotales();// th
					if (ordenesTotales > 0) {
						double envCajasTotales = ds.getEnvCajasTotales();
						double envOrdenesTotales = ordenesTotales;
						d.setEnvCajOrd(formatearDosDigitos(envCajasTotales
								/ envOrdenesTotales));
						// d.setEnvCajOrd(ds.getEnvCajasTotales() /
						// ordenesTotales);
						envTCajOrd += (envCajasTotales / envOrdenesTotales);
					} else {
						d.setEnvCajOrd(0);
					}

					d.setEnvPremios(ds.getEnvPremios());
					envTPremios += ds.getEnvPremios();

					// inicia la parte de Kilometraje

					d.setKmInicioRuta(ds.getKmInicioRuta());
					kmTInicioRuta += ds.getKmInicioRuta();// th
					d.setHrInicioRuta(formatIntoHHMMSS(obtenerSegundosHora(ds
							.getHrInicioRuta())));
					segTInicioRuta += obtenerSegundosHora(ds.getHrInicioRuta());// th
					d.setKmPrimeraEntrega(ds.getKmPrimeraEntrega());
					kmTPrimeraEntrega += ds.getKmPrimeraEntrega();// th
					d.setHrPrimeraEntrega(formatIntoHHMMSS(obtenerSegundosHora(ds
							.getHrPrimeraEntrega())));
					segTPrimeraEntrega += obtenerSegundosHora(ds
							.getHrPrimeraEntrega());// th
					d.setKmUltimaEntrega(ds.getKmUltimaEntrega());
					kmTUltimaEntrega += ds.getKmUltimaEntrega();// th
					d.setHrUltimaEntrega(formatIntoHHMMSS(obtenerSegundosHora(ds
							.getHrUltimaEntrega())));
					segTUltimaEntrega += obtenerSegundosHora(ds
							.getHrUltimaEntrega());// th
					d.setKmFinRuta(ds.getKmFinRuta());
					kmTFinRuta += ds.getKmFinRuta();// th
					d.setHrFinRuta(formatIntoHHMMSS(obtenerSegundosHora(ds
							.getHrFinRuta())));
					segTFinRuta += obtenerSegundosHora(ds.getHrFinRuta());// th

					// los calculados
					long kmRepartoGlobal = ds.getKmFinRuta()
							- ds.getKmInicioRuta();
					d.setKmRepartoGlobal(kmRepartoGlobal);
					kmTotRepartoGlobal += kmRepartoGlobal;

					long segRepartoGlobal = obtenerSegundosHora(ds
							.getHrFinRuta())
							- obtenerSegundosHora(ds.getHrInicioRuta());
					d.setTiempoRepartoGlobal(formatIntoHHMMColumnasCalculadas(segRepartoGlobal));
					segTRepartoGlobal += segRepartoGlobal;

					long kmRepartoEfectivo = ds.getKmUltimaEntrega()
							- ds.getKmPrimeraEntrega();
					d.setKmRepartoEfectivo(kmRepartoEfectivo);
					kmTotRepartoEfectivo += kmRepartoEfectivo;

					long segRepartoEfectivo = obtenerSegundosHora(ds
							.getHrUltimaEntrega())
							- obtenerSegundosHora(ds.getHrPrimeraEntrega());
					d.setTiempoRepartoEfectivo(formatIntoHHMMColumnasCalculadas(segRepartoEfectivo));
					segTRepartoEfectivo += segRepartoEfectivo;

					long kmArrastre = kmRepartoGlobal - kmRepartoEfectivo;
					d.setKmArrastre(kmArrastre);
					kmTotArrastre += kmArrastre;
					long segArrastre = segRepartoGlobal - segRepartoEfectivo;
					d.setTiempoArrastre(formatIntoHHMMColumnasCalculadas(segArrastre));
					segTArrastre += segArrastre;

					// productividad
					if (d.getEnvOrdenesTotales() > 0) {
						d.setProduMinutos(formatIntoHHMMSS(segRepartoEfectivo
								/ d.getEnvOrdenesTotales()));
					} else {
						d.setProduMinutos("00:00");
					}
					DecimalFormat df = new DecimalFormat("#########0.00");
					double ordHr = 0.00;
					if (segRepartoEfectivo > 0) {
						ordHr = ((double) d.getEnvOrdenesTotales() / (double) segRepartoEfectivo) * 60 * 60;
					}
					String formattedValue = df.format(ordHr);
					d.setProduOrdHr(formattedValue);

					// Rendimiento
					d.setLitros(ds.getLitros());
					renTotalLitros += ds.getLitros();
					d.setKmLitros(ds.getKmLitros());

					// Entregados en Reparto
					d.setEntPrimeraOrden(ds.getEntPrimeraOrden());
					entTPrimeraOrden += ds.getEntPrimeraOrden();// th
					d.setEntOrdenesEstablecidas(ds.getEntOrdenesEstablecidas());
					entTOrdenesEstablecidas += ds.getEntOrdenesEstablecidas();// th
					d.setEntCajasTotales(ds.getEntCajasTotales());
					entTCajasTotales += ds.getEntCajasTotales();// th
					d.setEntPremios(ds.getEntPremios());
					entTPremios += ds.getEntPremios();// th

					if (ds.getEnvPremios() > 0) {
						d.setAcpPremios(formatearDosDigitos(((double) ds
								.getEntPremios() / (double) ds.getEnvPremios()) * 100));
						acpTPremios += ((double) ds.getEntPremios() / (double) ds
								.getEnvPremios()) * 100;
					} else {
						d.setAcpPremios(0);
					}
					if (ds.getEnvPrimerasOrdenes() > 0) {
						d.setAcpPrimeraOrden(formatearDosDigitos(((double) ds
								.getEntPrimeraOrden() / (double) ds
								.getEnvPrimerasOrdenes()) * 100));
						acpTPrimerasOrdenes += ((double) ds
								.getEntPrimeraOrden() / (double) ds
								.getEnvPrimerasOrdenes()) * 100;
					} else {
						d.setAcpPrimeraOrden(0);
					}
					if (d.getEnvOrdenesTotales() > 0) {
						d.setAcpTotal(formatearDosDigitos((((double) ds
								.getEntPrimeraOrden() + (double) ds
								.getEntOrdenesEstablecidas()) / (double) d
								.getEnvOrdenesTotales()) * 100));
						acpTTotal += (((double) ds.getEntPrimeraOrden() + (double) ds
								.getEntOrdenesEstablecidas()) / (double) d
								.getEnvOrdenesTotales()) * 100;
					} else {
						d.setAcpTotal(0);
					}
					int devOrdenes = d.getEnvOrdenesTotales()
							- ds.getEntOrdenesEstablecidas()
							- ds.getEntPrimeraOrden();
					d.setDevOrdenes(ds.getDevOrdenes());
					// d.setDevOrdenes(devOrdenes);
					// devTOrdenes += devOrdenes;
					devTOrdenes += ds.getDevOrdenes();
					d.setDevCajas(d.getEnvCajasTotales()
							- d.getEntCajasTotales());
					devTCajas += (d.getEnvCajasTotales() - d
							.getEntCajasTotales());
					d.setDevPremios(d.getEnvPremios() - d.getEntPremios());
					devTPremios += (d.getEnvPremios() - d.getEntPremios());

					int difOrdenes = d.getEnvOrdenesTotales()
							- d.getEntPrimeraOrden()
							- d.getEntOrdenesEstablecidas() - d.getDevOrdenes();
					d.setDifOrdenes(ds.getDifOrdenes());
					// d.setDifOrdenes(difOrdenes);
					int difCajas = d.getEnvCajasTotales()
							- d.getEntCajasTotales() - d.getDevCajas();
					d.setDifCajas(difCajas);
					int difPremios = d.getEnvPremios() - ds.getEntPremios()
							- d.getDevPremios();
					d.setDifPremios(difPremios);

					// causas de devolucion

					d.setCauCambioDom(ds.getCauCambioDom());
					CauCambioDom += ds.getCauCambioDom();
					d.setCauCerradoTotal(ds.getCauCerradoTotal());
					CauCerradoTotal += ds.getCauCerradoTotal();
					d.setCauDifEnCobro(ds.getCauDifEnCobro());
					CauDifEnCobro += ds.getCauDifEnCobro();
					d.setCauDomIncompleto(ds.getCauDomIncompleto());
					CauDomIncompleto += ds.getCauDomIncompleto();
					d.setCauExtravioFicha(ds.getCauExtravioFicha());
					CauExtravioFicha += ds.getCauExtravioFicha();
					d.setCauFueraZona(ds.getCauFueraZona());
					CauFueraZona += ds.getCauFueraZona();
					d.setCauNoDejoFicha(ds.getCauNoDejoFicha());
					CauNoDejoFicha += ds.getCauNoDejoFicha();
					d.setCauNoEsperaReparto(ds.getCauNoEsperaReparto());
					CauNoEsperaReparto += ds.getCauNoEsperaReparto();
					d.setCauNoMetioPedido(ds.getCauNoMetioPedido());
					CauNoMetioPedido += ds.getCauNoMetioPedido();
					d.setCauNoPago(ds.getCauNoPago());
					CauNoPago += ds.getCauNoPago();
					d.setCauNoViveAhi(ds.getCauNoViveAhi());
					CauNoViveAhi += ds.getCauNoViveAhi();
					d.setCauOtros(ds.getCauOtros());
					CauOtros += ds.getCauOtros();
					int diferencia = d.getDevOrdenes() - ds.getCauCambioDom()
							- ds.getCauCerradoTotal() - ds.getCauDifEnCobro()
							- ds.getCauDomIncompleto()
							- ds.getCauExtravioFicha() - ds.getCauFueraZona()
							- ds.getCauNoDejoFicha()
							- ds.getCauNoEsperaReparto()
							- ds.getCauNoMetioPedido() - ds.getCauNoPago()
							- ds.getCauNoViveAhi() - ds.getCauOtros();
					d.setCauDiferencia(diferencia);
					CauDiferencia += diferencia;

					tabla.add(d);

				}

				header.setDetalleReparto(tabla);
				header.setEnvTotalOrdenesConReparto(envTOrdCReparDirecto);
				header.setEnvTotalOrdenesSinReparto(envTOrdSReparDirecto);
				header.setEnvTotalPrimerasOrdenes(envTPrimerasOrdenes);
				header.setEnvTotalOrdenesTotales(envTOrdenesTotales);
				header.setEnvTotalCajasTotales(envTCajasTotales);
				header.setEnvTotalPremios(envTPremios);
				header.setKmTotalInicioRuta(kmTInicioRuta);
				header.setKmTotalPrimeraEntrega(kmTPrimeraEntrega);
				header.setKmTotalUltimaEntrega(kmTUltimaEntrega);
				header.setKmTotalFinRuta(kmTFinRuta);
				header.setKmTotalRepartoGlobal(kmTotRepartoGlobal);
				header.setKmTotalRepartoEfectivo(kmTotRepartoEfectivo);
				header.setKmTotalArrastre(kmTotArrastre);
				header.setRenTotalLitros(renTotalLitros);
				header.setRenTotalKmLt(kmTotRepartoGlobal);
				header.setEntTotalPrimeraOrden(entTPrimeraOrden);
				header.setEntTotalOrdenesEstablecidas(entTOrdenesEstablecidas);
				header.setEntTotalCajasTotales(entTCajasTotales);
				header.setEntTotalPremios(entTPremios);
				header.setDevTotalCajas(devTCajas);
				header.setDevTotalOrdenes(devTOrdenes);
				header.setDevTotalPremios(devTPremios);
				header.setCauPorCerradoTotal(CauCerradoTotal);
				header.setCauPorCambiodedomicilio(CauCambioDom);
				header.setCauPorDiferenciaenCobro(CauDifEnCobro);
				header.setCauPorDomIncompleto(CauDomIncompleto);
				header.setCauPorExtravioFicha(CauExtravioFicha);
				header.setCauPorFueraZona(CauFueraZona);
				header.setCauPorNodejoFicha(CauNoDejoFicha);
				header.setCauPorNoEsperaReparto(CauNoEsperaReparto);
				header.setCauPorNometioPedido(CauNoMetioPedido);
				header.setCauPorNoPago(CauNoPago);
				header.setCauPorNoviveahi(CauNoViveAhi);
				header.setCauPorOtro(CauOtros);
				header.setCauTotCambiodedomicilio(formatearDosDigitos(((double) CauCambioDom / (double) devTOrdenes) * 100));
				header.setCauTotCerradoTotal(formatearDosDigitos(((double) CauCerradoTotal / (double) devTOrdenes) * 100));
				header.setCauTotDiferenciaenCobro(formatearDosDigitos(((double) CauDifEnCobro / (double) devTOrdenes) * 100));
				header.setCauTotDomIncompleto(formatearDosDigitos(((double) CauDomIncompleto / (double) devTOrdenes) * 100));
				header.setCauTotExtravioFicha(formatearDosDigitos(((double) CauExtravioFicha / (double) devTOrdenes) * 100));
				header.setCauTotFueraZona(formatearDosDigitos(((double) CauFueraZona / (double) devTOrdenes) * 100));
				header.setCauTotNodejoFicha(formatearDosDigitos(((double) CauNoDejoFicha / (double) devTOrdenes) * 100));
				header.setCauTotNoEsperaReparto(formatearDosDigitos(((double) CauNoEsperaReparto / (double) devTOrdenes) * 100));
				header.setCauTotNometioPedido(formatearDosDigitos(((double) CauNoMetioPedido / (double) devTOrdenes) * 100));
				header.setCauTotNoPago(formatearDosDigitos(((double) CauNoPago / (double) devTOrdenes) * 100));
				header.setCauTotNoviveahi(formatearDosDigitos(((double) CauNoViveAhi / (double) devTOrdenes) * 100));
				header.setCauTotOtro(formatearDosDigitos(((double) CauOtros / (double) devTOrdenes) * 100));
				header.setCauPorDiferencia(CauCerradoTotal + CauCambioDom
						+ CauDifEnCobro + CauDomIncompleto + CauExtravioFicha
						+ CauFueraZona + CauNoDejoFicha + CauNoEsperaReparto
						+ CauNoMetioPedido + CauNoPago + CauNoViveAhi
						+ CauOtros);
				header.setCauTotDiferencia(header.getCauTotCambiodedomicilio()
						+ header.getCauTotCerradoTotal()
						+ header.getCauTotDiferenciaenCobro()
						+ header.getCauTotDomIncompleto()
						+ header.getCauTotExtravioFicha()
						+ header.getCauTotFueraZona()
						+ header.getCauTotNodejoFicha()
						+ header.getCauTotNoEsperaReparto()
						+ header.getCauTotNometioPedido()
						+ header.getCauTotNoPago()
						+ header.getCauTotNoviveahi() + header.getCauTotOtro());
				// validacion de ordenes totales
				if (envTOrdenesTotales > 0) {
					header.setProTotalTiempoVisita(formatIntoHHMMSS(segTRepartoEfectivo
							/ envTOrdenesTotales));
				} else {
					header.setProTotalTiempoVisita("00:00");
				}

				if (segTRepartoEfectivo > 0) {
					header.setProTotalOrdHr(formatearDosDigitos(((double) envTOrdenesTotales / (double) segTRepartoEfectivo) * 60 * 60));
				} else {
					header.setProTotalOrdHr(0);
				}

				// validacion para no dividir entre 0
				if (tabla.size() > 0) {
					header.setEnvTotalCajProm(formatearDosDigitos(envTCajOrd
							/ tabla.size()));
					// hr totales
					header.setHrTotalInicioRuta(formatIntoHHMMSS(segTInicioRuta
							/ tabla.size()));
					header.setHrTotalPrimeraEntrega(formatIntoHHMMSS(segTPrimeraEntrega
							/ tabla.size()));
					header.setHrTotalUltimaEntrega(formatIntoHHMMSS(segTUltimaEntrega
							/ tabla.size()));
					header.setHrTotalFinRuta(formatIntoHHMMSS(segTFinRuta
							/ tabla.size()));
					header.setHrTotalRepartoGlobal(formatIntoHHMMColumnasCalculadas(segTRepartoGlobal
							/ tabla.size()));
					header.setHrTotalRepartoEfectivo(formatIntoHHMMColumnasCalculadas(segTRepartoEfectivo
							/ tabla.size()));
					header.setHrTotalArrastre(formatIntoHHMMColumnasCalculadas(segTArrastre
							/ tabla.size()));
					header.setAcpTotalPrimeraOrden(formatearDosDigitos(acpTPrimerasOrdenes
							/ tabla.size()));
					header.setAcpTotalPremios(formatearDosDigitos(acpTPremios
							/ tabla.size()));
					header.setAcpTotalTotal(formatearDosDigitos(acpTTotal
							/ tabla.size()));

				} else {
					header.setEnvTotalCajProm(0);
					header.setHrTotalArrastre("00:00");
					header.setHrTotalRepartoEfectivo("00:00");
					header.setHrTotalRepartoGlobal("00:00");
					header.setHrTotalFinRuta("00:00");
					header.setHrTotalUltimaEntrega("00:00");
					header.setHrTotalInicioRuta("00:00");
					header.setHrTotalPrimeraEntrega("00:00");
					header.setAcpTotalPremios(0);
					header.setAcpTotalPrimeraOrden(0);
					header.setAcpTotalTotal(0);
				}
			}
		} catch (AxisFault e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Reparto,obtenerDatosReparto",
					e.getMessage(), idUsuario);
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Reparto,obtenerDatosReparto",
					e.getMessage(), idUsuario);
		} catch (Exception e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Reparto,obtenerDatosReparto",
					e.getMessage(), idUsuario);
			throw new Exception();
		}

		return header;
	}

	private double formatearDosDigitos(double d) {
		DecimalFormat df = new DecimalFormat("##########.##");
		// DecimalFormat df = new DecimalFormat("#########0.00");
		return Double.parseDouble(df.format(d));
	}

	private long obtenerSegundosHora(String horaS) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long tiempoSegundos = 0;
		Date tTEnRu;
		int hora = 0;
		int minu = 0;
		int segundos = 0;
		int dia = 0;
		try {
			tTEnRu = sdf.parse(horaS);
			calendar.setTime(tTEnRu);
			dia = calendar.get(Calendar.DAY_OF_MONTH);
			hora = calendar.get(Calendar.HOUR_OF_DAY);
			minu = calendar.get(Calendar.MINUTE);
			segundos = calendar.get(Calendar.SECOND);
			tiempoSegundos = (dia * 86400) + (hora * 3600) + (minu * 60)
					+ segundos;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}

		return tiempoSegundos;
		// return (hora * 60 * 60) + (minu * 60);
		// return calendar.getTimeInMillis();
	}

	private String formatIntoHHMMSS(long secsIn) {

		String resultado = null;

		if (secsIn > 0) {
			long segundosDias = secsIn % 86400;
			long hours = segundosDias / 3600;
			long remainder = segundosDias % 3600;
			long minutes = remainder / 60;
			resultado = ((hours < 10 ? "0" : "") + hours + ":"
					+ (minutes < 10 ? "0" : "") + minutes);
		} else {
			resultado = "00:00";
		}
		return resultado;
	}

	private String formatIntoHHMMColumnasCalculadas(long secsIn) {
		String resultado = null;

		if (secsIn > 0) {
			long hours = secsIn / 3600;
			long remainder = secsIn % 3600;
			long minutes = remainder / 60;
			resultado = ((hours < 10 ? "0" : "") + hours + ":"
					+ (minutes < 10 ? "0" : "") + minutes);
		} else {
			resultado = "00:00";
		}
		return resultado;
	}

	public List<ModelHistoricoDeRepresentantes> consultarDatosReporteHistoricoRepresentantes(
			Integer zona, Integer campania, int idUsuario, int idLDC,
			Integer anioCampania) {

		List<ModelHistoricoDeRepresentantes> valores = new ArrayList<ModelHistoricoDeRepresentantes>();
		ModelHistoricoDeRepresentantes encabezado = new ModelHistoricoDeRepresentantes();
		List<ModelTablaHistoricoDeRepresentantes> tabla = new ArrayList<ModelTablaHistoricoDeRepresentantes>();
		ModelTablaHistoricoDeRepresentantes detalle = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtenerDatosReporteHistoricoRepresentantes param = new ReportesControllerStub.ObtenerDatosReporteHistoricoRepresentantes();

			param.setZona(zona);
			param.setCampania(campania);
			param.setIdLDC(idLDC);
			param.setIdUsuario(config.getIdUsuario());
			param.setAnioCampania(anioCampania);

			ReportesControllerStub.ObtenerDatosReporteHistoricoRepresentantesResponse representantesResponse = null;

			representantesResponse = stub
					.obtenerDatosReporteHistoricoRepresentantes(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelTablaHistoricoDeRepresentantes[] detallesRepresentantes = representantesResponse
					.get_return();

			if (detallesRepresentantes != null) {
				for (int j = 0; j < detallesRepresentantes.length; j++) {
					detalle = new ModelTablaHistoricoDeRepresentantes();
					detalle.setCEDI(detallesRepresentantes[j].getCEDI());
					detalle.setRegistro(detallesRepresentantes[j].getRegistro());
					detalle.setClaveRuta(detallesRepresentantes[j]
							.getClaveRuta());
					detalle.setNombre(detallesRepresentantes[j].getNombre());
					detalle.setDireccion(detallesRepresentantes[j]
							.getDireccion());
					detalle.setDireccion1(detallesRepresentantes[j]
							.getDireccion1());
					detalle.setDireccion2(detallesRepresentantes[j]
							.getDireccion2());
					detalle.setRed(detallesRepresentantes[j].getRed());
					detalle.setaPagar(detallesRepresentantes[j].getAPagar());
					detalle.setCajas(detallesRepresentantes[j].getCajas());
					detalle.setGeoreferenciaDomicilio(detallesRepresentantes[j]
							.getGeoreferenciaDomicilio());
					detalle.setTipoDePago(detallesRepresentantes[j]
							.getTipoDePago());
					detalle.setFolios(detallesRepresentantes[j].getFolios());
					detalle.setCantidadPago(detallesRepresentantes[j]
							.getCantidadPago());
					detalle.setDiferencia(detallesRepresentantes[j]
							.getDiferencia());
					detalle.setFechaDeEntrega(detallesRepresentantes[j]
							.getFechaDeEntrega());
					detalle.setFechaDePago(detallesRepresentantes[j]
							.getFechaDePago());
					detalle.setEntregado(detallesRepresentantes[j]
							.getEntregado());
					detalle.setSlipValue(detallesRepresentantes[j]
							.getSlipValue());
					detalle.setGeoreferenciaDeEntrega(detallesRepresentantes[j]
							.getGeoreferenciaDeEntrega());
					detalle.setDevueltoA(detallesRepresentantes[j]
							.getDevueltoA());
					detalle.setCausaDeDevolucion(detallesRepresentantes[j]
							.getCausaDeDevolucion());
					detalle.setFechaDeCierre(detallesRepresentantes[j]
							.getFechaDeCierre());
					detalle.setFechaDevolucionFinal(detallesRepresentantes[j]
							.getFechaDevolucionFinal());
					detalle.setObservaciones(detallesRepresentantes[j]
							.getObservaciones());

					// Modificacion, HIJACK, RETURN, CAUSA_DEVOLUCION_FINAL,
					// RETURN_VAL
					detalle.setModificacion(detallesRepresentantes[j]
							.getModificacion());
					detalle.setHijack(detallesRepresentantes[j].getHijack());
					detalle.setRepReturn(detallesRepresentantes[j]
							.getRepReturn());
					detalle.setReturnValue(detallesRepresentantes[j]
							.getReturnValue());
					detalle.setCausaDevolucionFinal(detallesRepresentantes[j]
							.getCausaDevolucionFinal());

					tabla.add(detalle);
				}
			}
			encabezado.setDetalleRepresentantes(tabla);
			valores.add(encabezado);
		} catch (Exception ex) {
			ex.printStackTrace();
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Historico de Representantes",
					ex.toString(), config.getIdUsuario());
			valores = null;
		}
		return valores;
	}

	public List<ModelHistorialPorRepresentante> consultarDatosReporteHistorialPorRepresentante(
			String campania, int idUsuario, int idLDC,
			int registroRepresentante, String anioCampania) {

		List<ModelHistorialPorRepresentante> valores = new ArrayList<ModelHistorialPorRepresentante>();
		ModelHistorialPorRepresentante encabezado = new ModelHistorialPorRepresentante();
		List<ModelTablaHistorialPorRepresentante> tabla = new ArrayList<ModelTablaHistorialPorRepresentante>();
		ModelTablaHistorialPorRepresentante detalle = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtenerEncabezadoHistorialPorRepresentante param = new ReportesControllerStub.ObtenerEncabezadoHistorialPorRepresentante();

			param.setRegistro(registroRepresentante);
			param.setIdUsuario(config.getIdUsuario());

			ReportesControllerStub.ObtenerEncabezadoHistorialPorRepresentanteResponse representanteResponse = null;

			representanteResponse = stub
					.obtenerEncabezadoHistorialPorRepresentante(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelHistorialPorRepresentante representante = representanteResponse
					.get_return();

			if (representante != null) {
				encabezado.setRegistro(representante.getRegistro());
				encabezado.setNombre(representante.getNombre());
				encabezado.setDireccion(representante.getDireccion());
				encabezado.setDireccion1(representante.getDireccion1());
				encabezado.setDireccion2(representante.getDireccion2());
				encabezado.setRed(representante.getRed());
				encabezado.setZona(representante.getZona());
			} else {
				encabezado.setRegistro("");
				encabezado.setNombre("");
				encabezado.setDireccion("");
				encabezado.setDireccion1("");
				encabezado.setDireccion2("");
				encabezado.setRed("");
				encabezado.setZona("");
			}
			// int idRepresentante = Integer.parseInt(representante
			// .getIdRepresentante());

			ReportesControllerStub.ObtenerDatosReporteHistorialPorRepresentante param2 = new ReportesControllerStub.ObtenerDatosReporteHistorialPorRepresentante();

			param2.setRegistro(String.valueOf(registroRepresentante));
			// param2.setIdZona(idZona);
			// param2.setIdCampania(idCampania);
			param2.setIdLDC(idLDC);
			param2.setIdUsuario(config.getIdUsuario());
			param2.setCampania(campania);
			param2.setAnioCampania(anioCampania);

			ReportesControllerStub.ObtenerDatosReporteHistorialPorRepresentanteResponse representantesResponse = null;

			representantesResponse = stub
					.obtenerDatosReporteHistorialPorRepresentante(param2);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelTablaHistorialPorRepresentante[] detallesRepresentantes = representantesResponse
					.get_return();

			if (detallesRepresentantes != null) {// COMENTAR
				for (int j = 0; j < detallesRepresentantes.length; j++) {
					detalle = new ModelTablaHistorialPorRepresentante();
					detalle.setCampania(detallesRepresentantes[j].getCampania());
					detalle.setaPagar(detallesRepresentantes[j].getAPagar());
					detalle.setCajas(detallesRepresentantes[j].getCajas());
					detalle.setGeoreferenciaDomicilio(detallesRepresentantes[j]
							.getGeoreferenciaDomicilio());
					detalle.setTipoDePago(detallesRepresentantes[j]
							.getTipoDePago());
					detalle.setFolios(detallesRepresentantes[j].getFolios());
					detalle.setCantidadPago(detallesRepresentantes[j]
							.getCantidadPago());
					detalle.setDiferencia(detallesRepresentantes[j]
							.getDiferencia());
					detalle.setFechaDeEntrega(detallesRepresentantes[j]
							.getFechaDeEntrega());
					detalle.setFechaDePago(detallesRepresentantes[j]
							.getFechaDePago());
					detalle.setEntregado(detallesRepresentantes[j]
							.getEntregado());
					detalle.setSlipValue(detallesRepresentantes[j]
							.getSlipValue());
					detalle.setGeoreferenciaDeEntrega(detallesRepresentantes[j]
							.getGeoreferenciaDeEntrega());
					detalle.setDevueltoA(detallesRepresentantes[j]
							.getDevueltoA());
					detalle.setCausaDeDevolucion(detallesRepresentantes[j]
							.getCausaDeDevolucion());
					detalle.setFechaDeCierre(detallesRepresentantes[j]
							.getFechaDeCierre());
					detalle.setFechaDevolucionFinal(detallesRepresentantes[j]
							.getFechaDevolucionFinal());
					detalle.setObservaciones(detallesRepresentantes[j]
							.getObservaciones());
					detalle.setFotoDomicilio(devolverFotoDomicilio(detallesRepresentantes[j]
							.getFotoDomicilio()));
					detalle.setFotoIdentificacion(devolverFotoDomicilio(detallesRepresentantes[j]
							.getFotoIdentificacion()));

					detalle.setModificacion(detallesRepresentantes[j]
							.getModificacion());
					detalle.setHijack(detallesRepresentantes[j].getHijack());
					detalle.setRepReturn(detallesRepresentantes[j]
							.getRepReturn());
					detalle.setReturnValue(detallesRepresentantes[j]
							.getReturnValue());
					detalle.setCausaDevolucionFinal(detallesRepresentantes[j]
							.getCausaDevolucionFinal());

					tabla.add(detalle);
				}
			}// comentar
			encabezado.setDetalleRepresentante(tabla);
			valores.add(encabezado);
			// }
		} catch (Exception ex) {
			ex.getMessage();
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M11",
					"Surgió un error al obtener los datos del reporte Historial de Representante",
					ex.toString(), config.getIdUsuario());
			valores = null;
		}
		return valores;
	}

	public List<ModelRepRecepcionCarga> consultarDatosReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<ModelRepRecepcionCarga> valores = new ArrayList<ModelRepRecepcionCarga>();
		ModelRepRecepcionCarga recepcionCarga = null;
		List<TablaOrdenesXZona> listaOrdenesPorZona = new ArrayList<TablaOrdenesXZona>();
		TablaOrdenesXZona ordenPorZona = null;
		List<TablaPapeleo> listaPapeleo = new ArrayList<TablaPapeleo>();
		TablaPapeleo papeleo = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ConsultarEncabezadoReporteControlRecepcionCarga param = new ReportesControllerStub.ConsultarEncabezadoReporteControlRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLDC(idLDC);

			ReportesControllerStub.ConsultarEncabezadoReporteControlRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.consultarEncabezadoReporteControlRecepcionCarga(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelRepRecepcionCarga recepcionCargaWs[] = recepcionCargaResponse
					.get_return();

			if (recepcionCargaWs != null && recepcionCargaWs.length > 0) {

				recepcionCarga = new ModelRepRecepcionCarga();
				recepcionCarga.setPorteador(recepcionCargaWs[0].getPorteador());
				recepcionCarga.setPoblacion(recepcionCargaWs[0].getPoblacion());
				recepcionCarga.setcTransportista(recepcionCargaWs[0]
						.getCTransportista());
				recepcionCarga.setOperador(recepcionCargaWs[0].getOperador());
				recepcionCarga.setCampania(recepcionCargaWs[0].getCampania());
				recepcionCarga.setZonaH(recepcionCargaWs[0].getZonaH());
				recepcionCarga.setCampaniaH(recepcionCargaWs[0].getCampaniaH());
				recepcionCarga.setFecha(recepcionCargaWs[0].getFecha());
				recepcionCarga.setHoraLlegadaReal(recepcionCargaWs[0]
						.getHoraLlegadaReal());
				recepcionCarga.setHoraLlegadaProg(recepcionCargaWs[0]
						.getHoraLlegadaProg());
				recepcionCarga.setFechaLlegadaReal(recepcionCargaWs[0]
						.getFechaLlegadaReal());
				recepcionCarga.setFechaLlegadaProg(recepcionCargaWs[0]
						.getFechaLlegadaProg());
				recepcionCarga.setObservaciones(recepcionCargaWs[0]
						.getObservaciones());

				for (int i = 0; i < recepcionCargaWs.length; i++) {
					ordenPorZona = new TablaOrdenesXZona();
					ordenPorZona.setZona(recepcionCargaWs[i].getZona());
					ordenPorZona.setOrdEnvGerZon(recepcionCargaWs[i]
							.getOrdEnvGerZon());
					ordenPorZona.setCancel(recepcionCargaWs[i].getCancel());
					ordenPorZona.setRelacion(recepcionCargaWs[i].getRelacion());
					ordenPorZona.setCods(recepcionCargaWs[i].getCods());
					ordenPorZona.setCajasOrd(recepcionCargaWs[i].getCajasOrd());
					ordenPorZona.setPremios(recepcionCargaWs[i].getPremios());
					ordenPorZona.setCajasPre(recepcionCargaWs[i].getCajasPre());
					listaOrdenesPorZona.add(ordenPorZona);

					papeleo = new TablaPapeleo();
					papeleo.setPanoram(recepcionCargaWs[i].getPanoram());
					papeleo.setPortaf(recepcionCargaWs[i].getPortaf());
					papeleo.setPapGerenZonal(recepcionCargaWs[i]
							.getPapGerenZonal());
					papeleo.setPapPortead(recepcionCargaWs[i].getPapPortead());
					papeleo.setOtros(recepcionCargaWs[i].getOtros());
					papeleo.setTotCajas(recepcionCargaWs[i].getTotCajas());
					listaPapeleo.add(papeleo);
				}

				List<TablaInfoCodFaltantes> listaCodigosFaltantes = consultarInfoCodigosFaltantesReporteControlRecepcionCarga(
						idZona, idCampania, idLDC, idUsuario);
				List<TablaCajasMaltratadas> listaCajasMaltratadas = consultarCajasMaltratadasReporteControlRecepcionCarga(
						idZona, idCampania, idLDC, idUsuario);
				List<TablaCargaRecibida> listaCargasRecibidas = consultarCargaRecibidaReporteControlRecepcionCarga(
						idZona, idCampania, idLDC, idUsuario);

				recepcionCarga.setListaOrdenesFaltantes(listaOrdenesPorZona);
				recepcionCarga.setListaPapeleo(listaPapeleo);
				recepcionCarga.setListaCodFaltantes(listaCodigosFaltantes);
				recepcionCarga.setListaCajaRecibida(listaCargasRecibidas);
				recepcionCarga.setListaCajasMaltratadas(listaCajasMaltratadas);

				valores.add(recepcionCarga);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valores;
	}

	public String consultarDatosReporteControlRecepcionCargaObservaciones(
			Integer idZona, Integer idCampania, int idUsuario) {

		String res = "";

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ObtenerObservacionesReporteRecepcionCarga param = new ReportesControllerStub.ObtenerObservacionesReporteRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);

			ReportesControllerStub.ObtenerObservacionesReporteRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.obtenerObservacionesReporteRecepcionCarga(param);
			res = recepcionCargaResponse.get_return();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public boolean consultarDatosReporteControlRecepcionCargaActualizaObservaciones(
			Integer idZona, Integer idCampania, String obsevaciones,
			int idUsuario) {

		boolean res1 = true;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ActualizaObservacionesReporteRecepcionCarga param = new ReportesControllerStub.ActualizaObservacionesReporteRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setObservaciones(obsevaciones);

			ReportesControllerStub.ActualizaObservacionesReporteRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.actualizaObservacionesReporteRecepcionCarga(param);
			res1 = recepcionCargaResponse.get_return();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return res1;
	}

	public List<TablaInfoCodFaltantes> consultarInfoCodigosFaltantesReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaInfoCodFaltantes> listaCodigosFaltantes = new ArrayList<TablaInfoCodFaltantes>();
		TablaInfoCodFaltantes codigoFaltante = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();
			ReportesControllerStub.ConsultarInfoCodigosFaltantesReporteControlRecepcionCarga param = new ReportesControllerStub.ConsultarInfoCodigosFaltantesReporteControlRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLDC(idLDC);

			ReportesControllerStub.ConsultarInfoCodigosFaltantesReporteControlRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.consultarInfoCodigosFaltantesReporteControlRecepcionCarga(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.TablaInfoCodFaltantes[] arrayCodFaltantes = recepcionCargaResponse
					.get_return();

			if (arrayCodFaltantes != null) {
				for (int i = 0; i < arrayCodFaltantes.length; i++) {
					codigoFaltante = new TablaInfoCodFaltantes();
					codigoFaltante.setRegistro(arrayCodFaltantes[i]
							.getRegistro());
					codigoFaltante.setTotalCajas(arrayCodFaltantes[i]
							.getTotalCajas());
					codigoFaltante.setTotalRecibido(arrayCodFaltantes[i]
							.getTotalRecibido());
					codigoFaltante.setCajasFaltantes(arrayCodFaltantes[i]
							.getCajasFaltantes());
					codigoFaltante.setNumCajaFaltante(arrayCodFaltantes[i]
							.getNumCajaFaltante());
					codigoFaltante.setObservaciones(arrayCodFaltantes[i]
							.getObservaciones());
					listaCodigosFaltantes.add(codigoFaltante);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaCodigosFaltantes;
	}

	public List<TablaCajasMaltratadas> consultarCajasMaltratadasReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaCajasMaltratadas> listaCajasMaltratadas = new ArrayList<TablaCajasMaltratadas>();
		TablaCajasMaltratadas cajaMaltratada = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();
			ReportesControllerStub.ConsultarCajasMaltratadasReporteControlRecepcionCarga param = new ReportesControllerStub.ConsultarCajasMaltratadasReporteControlRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLDC(idLDC);

			ReportesControllerStub.ConsultarCajasMaltratadasReporteControlRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.consultarCajasMaltratadasReporteControlRecepcionCarga(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.TablaCajasMaltratadas[] arrayCajasMaltratadas = recepcionCargaResponse
					.get_return();

			if (arrayCajasMaltratadas != null) {
				for (int i = 0; i < arrayCajasMaltratadas.length; i++) {

					cajaMaltratada = new TablaCajasMaltratadas();
					cajaMaltratada.setRegistro(arrayCajasMaltratadas[i]
							.getRegistro());
					cajaMaltratada.setNoCaja(arrayCajasMaltratadas[i]
							.getNoCaja());

					// NO SE ENCUENTRAN ESTOS DATOS EN LA CONSULTA, LO MAS
					// PROBABLE ES QUE NO LOS LLEVE EL REPORTE
					// cajaMaltratada.setAveriada("");
					// cajaMaltratada.setDespegada("");
					// cajaMaltratada.setMojada("");
					listaCajasMaltratadas.add(cajaMaltratada);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaCajasMaltratadas;
	}

	public List<TablaCargaRecibida> consultarCargaRecibidaReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaCargaRecibida> listaCargasRecibidas = new ArrayList<TablaCargaRecibida>();
		TablaCargaRecibida cargaRecibida = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();
			ReportesControllerStub.ConsultarCargaRecibidaReporteControlRecepcionCarga param = new ReportesControllerStub.ConsultarCargaRecibidaReporteControlRecepcionCarga();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLDC(idLDC);

			ReportesControllerStub.ConsultarCargaRecibidaReporteControlRecepcionCargaResponse recepcionCargaResponse = null;

			recepcionCargaResponse = stub
					.consultarCargaRecibidaReporteControlRecepcionCarga(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.TablaCargaRecibida[] arrayCargasRecibidas = recepcionCargaResponse
					.get_return();

			if (arrayCargasRecibidas != null) {
				for (int i = 0; i < arrayCargasRecibidas.length; i++) {
					cargaRecibida = new TablaCargaRecibida();

					cargaRecibida.setZona(arrayCargasRecibidas[i].getZona());
					cargaRecibida.setCods(arrayCargasRecibidas[i].getCods());
					cargaRecibida.setCajasOrd(arrayCargasRecibidas[i]
							.getCajasOrd());
					cargaRecibida.setPremios(arrayCargasRecibidas[i]
							.getPremios());
					cargaRecibida.setCajasPre(arrayCargasRecibidas[i]
							.getCajasPre());
					cargaRecibida.setPanoram(arrayCargasRecibidas[i]
							.getPanoram());
					cargaRecibida
							.setPortaf(arrayCargasRecibidas[i].getPortaf());
					cargaRecibida.setPapGerZonal(arrayCargasRecibidas[i]
							.getPapGerZonal());
					cargaRecibida.setPapPorteador(arrayCargasRecibidas[i]
							.getPapPorteador());
					cargaRecibida.setOtros(arrayCargasRecibidas[i].getOtros());
					cargaRecibida.setTotCajas(arrayCargasRecibidas[i]
							.getTotCajas());
					listaCargasRecibidas.add(cargaRecibida);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaCargasRecibidas;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 15/02/2012
	 * @param idCampania
	 * @param idZona
	 * @param idRuta
	 * @return
	 * 
	 */
	public List<ModelAnalisisEfectivo> consultarDatosReporteAnalisisEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {

		List<ModelAnalisisEfectivo> listaDatosReporte = new ArrayList<ModelAnalisisEfectivo>();
		ModelAnalisisEfectivo analisisEfectivo = null;

		ReportesControllerStub stub;
		try {
			stub = new ReportesControllerStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(600000);

			ReportesControllerStub.ObtenerEncabezadoAnalisisDeEfectivo param = new ReportesControllerStub.ObtenerEncabezadoAnalisisDeEfectivo();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLdc(idLdc);
			param.setIdRuta(idRuta);

			ReportesControllerStub.ObtenerEncabezadoAnalisisDeEfectivoResponse analisisEfectivoResponse = null;
			analisisEfectivoResponse = stub
					.obtenerEncabezadoAnalisisDeEfectivo(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelAnalisisEfectivo analisiEfectivoWs = analisisEfectivoResponse
					.get_return();

			if (analisiEfectivoWs != null) {
				analisisEfectivo = new ModelAnalisisEfectivo();
				analisisEfectivo.setPorteador(analisiEfectivoWs.getPorteador());
				analisisEfectivo.setPlaza(analisiEfectivoWs.getPlaza());
				analisisEfectivo.setZona(analisiEfectivoWs.getZona());
				analisisEfectivo.setCampania(analisiEfectivoWs.getCampania());
				List<ModelTablaAnalisisEfectivo> listaPrimeraLiquidacion = consultarPrimeraLiquidacionAnalisisEfectivo(
						idCampania, idZona, idRuta, idUsuario, idLdc);
				List<ModelTablaAnalisisEfectivoLiquidacion> listaSegundaLiquidacion = consultarSegundaLiquidacionAnalisisEfectivo(
						idCampania, idZona, idRuta, idUsuario, idLdc);
				analisisEfectivo
						.setListaAnalisisEfectivo(listaPrimeraLiquidacion);
				analisisEfectivo
						.setListaAnalisisEfectivoLiquidacion(listaSegundaLiquidacion);
				listaDatosReporte.add(analisisEfectivo);
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDatosReporte;
	}

	public List<ModelTablaAnalisisEfectivo> consultarPrimeraLiquidacionAnalisisEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {
		List<ModelTablaAnalisisEfectivo> listaAnalisisEfectivo = new ArrayList<ModelTablaAnalisisEfectivo>();
		ModelTablaAnalisisEfectivo primeraLiquidacion = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReportesControllerStub.ConsultarPrimeraLiquidacionAnalisisEfectivo param = new ReportesControllerStub.ConsultarPrimeraLiquidacionAnalisisEfectivo();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLdc(idLdc);
			param.setIdRuta(idRuta);

			ReportesControllerStub.ConsultarPrimeraLiquidacionAnalisisEfectivoResponse primeraLiquidacionResponse = null;
			primeraLiquidacionResponse = stub
					.consultarPrimeraLiquidacionAnalisisEfectivo(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelTablaAnalisisEfectivo[] arrPrimeraLiquidacion = primeraLiquidacionResponse
					.get_return();

			if (arrPrimeraLiquidacion != null) {
				for (int i = 0; i < arrPrimeraLiquidacion.length; i++) {
					primeraLiquidacion = new ModelTablaAnalisisEfectivo();
					primeraLiquidacion.setRuta(arrPrimeraLiquidacion[i]
							.getRuta());
					primeraLiquidacion
							.setEfectivoRecolectado(arrPrimeraLiquidacion[i]
									.getEfectivoRecolectado());
					primeraLiquidacion
							.setFechaRecepcionEfectivo(arrPrimeraLiquidacion[i]
									.getFechaRecepcionEfectivo());
					primeraLiquidacion
							.setDepositoGlobal(arrPrimeraLiquidacion[i]
									.getDepositoGlobal());
					primeraLiquidacion
							.setBancoDeposito(arrPrimeraLiquidacion[i]
									.getBancoDeposito());
					listaAnalisisEfectivo.add(primeraLiquidacion);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaAnalisisEfectivo;
	}

	public List<ModelTablaAnalisisEfectivoLiquidacion> consultarSegundaLiquidacionAnalisisEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {

		List<ModelTablaAnalisisEfectivoLiquidacion> listaAnalisisEfectivoLiquidacion = new ArrayList<ModelTablaAnalisisEfectivoLiquidacion>();
		ModelTablaAnalisisEfectivoLiquidacion segundaLiquidacion = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReportesControllerStub.ConsultarSegundaLiquidacionAnalisisEfectivo param = new ReportesControllerStub.ConsultarSegundaLiquidacionAnalisisEfectivo();

			param.setIdZona(idZona);
			param.setIdCampania(idCampania);
			param.setIdUsuario(idUsuario);
			param.setIdLdc(idLdc);
			param.setIdRuta(idRuta);

			ReportesControllerStub.ConsultarSegundaLiquidacionAnalisisEfectivoResponse segundaLiquidacionResponse = null;
			segundaLiquidacionResponse = stub
					.consultarSegundaLiquidacionAnalisisEfectivo(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelTablaAnalisisEfectivoLiquidacion[] arrSegundaLiquidacion = segundaLiquidacionResponse
					.get_return();

			if (arrSegundaLiquidacion != null) {
				for (int i = 0; i < arrSegundaLiquidacion.length; i++) {
					segundaLiquidacion = new ModelTablaAnalisisEfectivoLiquidacion();
					segundaLiquidacion.setBodegaSub(arrSegundaLiquidacion[i]
							.getBodegaSub());
					segundaLiquidacion.setImporte(arrSegundaLiquidacion[i]
							.getImporte());
					segundaLiquidacion.setFecha(arrSegundaLiquidacion[i]
							.getFecha());
					// segundaLiquidacion.setBanco(arrSegundaLiquidacion[i].getBanco());
					listaAnalisisEfectivoLiquidacion.add(segundaLiquidacion);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaAnalisisEfectivoLiquidacion;
	}

	public List<ModelRepPremios> consultarDatosReportePremios(String zona,
			String campania, String numeroRegistro, String descripcionPremio,
			int idLdc, int idUsuario, String anioCampania) {

		List<ModelRepPremios> listaPremios = new ArrayList<ModelRepPremios>();
		ModelRepPremios premio = null;
		List<ModelRepTablaPremios> listaDetallePremios = null;
		ModelRepTablaPremios detallePremio = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ConsultarDatosReportePremios param = new ReportesControllerStub.ConsultarDatosReportePremios();
			param.setZona(zona);
			param.setCampania(campania);
			param.setNumeroRegistro(numeroRegistro);
			param.setDescripcionPremio(descripcionPremio);
			param.setIdLdc(idLdc);
			param.setIdUsuario(idUsuario);
			param.setAnioCampania(anioCampania);

			ReportesControllerStub.ConsultarDatosReportePremiosResponse datosReportePremiosResponse = null;

			datosReportePremiosResponse = stub
					.consultarDatosReportePremios(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelRepTablaPremios[] arrayPremios = datosReportePremiosResponse
					.get_return();

			if (arrayPremios != null) {
				listaPremios = new ArrayList<ModelRepPremios>();
				premio = new ModelRepPremios();
				listaDetallePremios = new ArrayList<ModelRepTablaPremios>();
				detallePremio = new ModelRepTablaPremios();
				for (int i = 0; i < arrayPremios.length; i++) {
					detallePremio = new ModelRepTablaPremios();
					detallePremio.setCedi(arrayPremios[i].getCedi());
					detallePremio.setZona(arrayPremios[i].getZona());
					detallePremio.setPoblacion(arrayPremios[i].getPoblacion());
					detallePremio.setCampania(arrayPremios[i].getCampania());
					detallePremio.setAccount(arrayPremios[i].getAccount());
					detallePremio.setNombre(arrayPremios[i].getNombre());
					detallePremio.setRutaSecuenciaEntrega(arrayPremios[i]
							.getRutaSecuenciaEntrega());
					detallePremio.setLastUpdTs(arrayPremios[i].getLastUpdTs());
					detallePremio.setCampaniaEnvio(arrayPremios[i]
							.getCampaniaEnvio());
					detallePremio.setCode(arrayPremios[i].getCode());
					detallePremio.setDescripcionPremio(arrayPremios[i]
							.getDescripcionPremio());
					detallePremio.setFechaEntregaDevolucion(arrayPremios[i]
							.getFechaEntregaDevolucion());
					detallePremio.setEntregado(arrayPremios[i].getEntregado());
					detallePremio.setObservaciones(arrayPremios[i]
							.getObservaciones());
					detallePremio.setGeoreferenciaDomicilio(arrayPremios[i]
							.getGeoreferenciaDomicilio());
					detallePremio.setGeoreferenciaEntrega(arrayPremios[i]
							.getGeoreferenciaEntrega());
					detallePremio.setDevueltoA(arrayPremios[i].getDevueltoA());
					detallePremio.setCausaDevolucion(arrayPremios[i]
							.getCausaDevolucion());
					listaDetallePremios.add(detallePremio);
				}
				premio.setListaPremios(listaDetallePremios);
				listaPremios.add(premio);
			}
		} catch (Exception e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M14",
					"Surgió un error al consultar los datos del reporte de premios",
					e.toString(), idUsuario);
			listaPremios = null;
		}
		return listaPremios;
	}

	public ModelHeaderResumen consultarDatosReporteResumen(int campania,
			int anioCampania, int idLdc, int idUsuario) throws RemoteException {

		List<ModelReporteResumen> detallesResumen = null;
		ModelReporteResumen detalle = null;
		ModelHeaderResumen headerResumen = null;

		try {
			ReportesControllerStub stub = new ReportesControllerStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			stub._getServiceClient()
					.getOptions()
					.setTimeOutInMilliSeconds(
							Utils.ObtenerConfiguracionTiempoEsperaWS());

			ReportesControllerStub.ConsultarDatosReporteResumen param = new ReportesControllerStub.ConsultarDatosReporteResumen();
			param.setCampania(campania);
			param.setAnioCampania(anioCampania);
			param.setIdLdc(idLdc);
			param.setIdUsuario(idUsuario);

			ReportesControllerStub.ConsultarDatosReporteResumenResponse datosReporteResumenResponse = null;

			datosReporteResumenResponse = stub
					.consultarDatosReporteResumen(param);
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen[] arrayRepResumen = datosReporteResumenResponse
					.get_return();

			if (arrayRepResumen != null && arrayRepResumen.length > 1) {
				detallesResumen = new ArrayList<ModelReporteResumen>();
				headerResumen = new ModelHeaderResumen();
				int totalRegistros = arrayRepResumen.length - 1;
				for (int i = 0; i < totalRegistros; i++) {
					detalle = new ModelReporteResumen();
					detalle.setCampania(arrayRepResumen[i].getCampania());
					detalle.setProductividadDescarga(formatearDosDigitos(arrayRepResumen[i]
							.getProductividadDescarga()));
					detalle.setProductividadEnrutado(formatearDosDigitos(arrayRepResumen[i]
							.getProductividadEnrutado()));
					detalle.setRutasNormales(arrayRepResumen[i]
							.getRutasNormales());
					detalle.setRutasEspeciales(arrayRepResumen[i]
							.getRutasEspeciales());
					detalle.setOrdenConRepartoDir(arrayRepResumen[i]
							.getOrdenConRepartoDir());
					detalle.setOrdenSinRepartoDir(arrayRepResumen[i]
							.getOrdenSinRepartoDir());
					detalle.setPrimerasOrdenes(arrayRepResumen[i]
							.getPrimerasOrdenes());
					detalle.setCajas(arrayRepResumen[i].getCajas());
					detalle.setPremios(arrayRepResumen[i].getPremios());
					detalle.setBultosMatPromocional(arrayRepResumen[i]
							.getBultosMatPromocional());

					detalle.setRepInicioRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[i]
							.getRepInicioRutaPromedio())));
					detalle.setRepPrimeraVisitaProm(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[i]
							.getRepPrimeraVisitaProm())));
					detalle.setRepUltimaVisitaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[i]
							.getRepUltimaVisitaPromedio())));
					detalle.setRepFinRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[i]
							.getRepFinRutaPromedio())));
					detalle.setRepKmGlobal(formatearDosDigitos(arrayRepResumen[i]
							.getRepKmGlobal()));
					detalle.setRepKmEfectivo(formatearDosDigitos(arrayRepResumen[i]
							.getRepKmEfectivo()));

					detalle.setRepTiempoPromVisita("00:00");

					if (arrayRepResumen[i].getRutasNormales() > 0) {
						long tiempoPromedioVisita = (arrayRepResumen[i]
								.getOrdenConRepartoDir() + arrayRepResumen[i]
								.getOrdenSinRepartoDir())
								/ arrayRepResumen[i].getRutasNormales();
						if (tiempoPromedioVisita > 0) {
							long tiempoPromedio = (obtenerSegundosHora(arrayRepResumen[i]
									.getRepUltimaVisitaPromedio()) - obtenerSegundosHora(arrayRepResumen[i]
									.getRepPrimeraVisitaProm()))
									/ tiempoPromedioVisita;
							detalle.setRepTiempoPromVisita(formatIntoHHMMColumnasCalculadas(tiempoPromedio));
						}
					}

					double productividadOrdenHora = calcularProductividadOrdenHoraDetalle(
							arrayRepResumen[i].getRepUltimaVisitaPromedio(),
							arrayRepResumen[i].getRepPrimeraVisitaProm(),
							arrayRepResumen[i].getOrdenConRepartoDir(),
							arrayRepResumen[i].getOrdenSinRepartoDir(),
							arrayRepResumen[i].getPrimerasOrdenes(),
							arrayRepResumen[i].getRutasNormales());

					detalle.setRepProductividadOrHr(formatearDosDigitos(productividadOrdenHora));

					detalle.setRepRendimientoProm(formatearDosDigitos(arrayRepResumen[i]
							.getRepRendimientoProm()));
					detalle.setEntPrimeraOrden(arrayRepResumen[i]
							.getEntPrimeraOrden());
					detalle.setEntOrdenPtoEntrega(arrayRepResumen[i]
							.getEntOrdenPtoEntrega());
					detalle.setEntOrdenEstablecidas(arrayRepResumen[i]
							.getEntOrdenEstablecidas());
					detalle.setEntCajasTotales(arrayRepResumen[i]
							.getEntCajasTotales());
					detalle.setEntPremios(arrayRepResumen[i].getEntPremios());
					detalle.setEntMaterialPromocinal(arrayRepResumen[i]
							.getEntMaterialPromocinal());

					detalle.setDevOrdenes(arrayRepResumen[i].getDevOrdenes());
					detalle.setDevCajas(arrayRepResumen[i].getDevCajas());
					detalle.setDevPremios(arrayRepResumen[i].getDevPremios());
					detalle.setDevMatPromocional(arrayRepResumen[i]
							.getDevMatPromocional());

					detalle.setCauNoViveAhi(formatearDosDigitos(arrayRepResumen[i]
							.getCauNoViveAhi()));
					detalle.setCauNoPago(formatearDosDigitos(arrayRepResumen[i]
							.getCauNoPago()));
					detalle.setCauNoDejoFicha(formatearDosDigitos(arrayRepResumen[i]
							.getCauNoDejoFicha()));
					detalle.setCauCambioDom(formatearDosDigitos(arrayRepResumen[i]
							.getCauCambioDom()));
					detalle.setCauCerradoTotal(formatearDosDigitos(arrayRepResumen[i]
							.getCauCerradoTotal()));
					detalle.setCauDifEnCobro(formatearDosDigitos(arrayRepResumen[i]
							.getCauDifEnCobro()));
					detalle.setCauFueraZona(formatearDosDigitos(arrayRepResumen[i]
							.getCauFueraZona()));
					detalle.setCauNoMetioPedido(formatearDosDigitos(arrayRepResumen[i]
							.getCauNoMetioPedido()));
					detalle.setCauDomIncompleto(formatearDosDigitos(arrayRepResumen[i]
							.getCauDomIncompleto()));
					detalle.setCauNoEsperaReparto(formatearDosDigitos(arrayRepResumen[i]
							.getCauNoEsperaReparto()));
					detalle.setCauExtravioFicha(formatearDosDigitos(arrayRepResumen[i]
							.getCauExtravioFicha()));
					detalle.setCauOtro(formatearDosDigitos(arrayRepResumen[i]
							.getCauOtro()));
					detalle.setCauTotal(formatearDosDigitos(arrayRepResumen[i]
							.getCauTotal()));
					detallesResumen.add(detalle);
				}
				headerResumen = obtenerTotalesReporteResumen(headerResumen,
						arrayRepResumen);
				headerResumen.setDetalles(detallesResumen);
			}
		} catch (ArithmeticException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M15",
					"Surgió un error al generar los calculos en el reporte de Resumen",
					e.getMessage(), idUsuario);
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD(
					"ReportesAdmin",
					"M15",
					"Surgió un error al consultar los datos del reporte de resumen",
					e.getMessage(), idUsuario);
			throw e;
		}

		return headerResumen;
	}

	private ModelHeaderResumen obtenerTotalesReporteResumen(
			ModelHeaderResumen headerResumen,
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen[] arrayRepResumen) {

		int ultimoElemento = arrayRepResumen.length - 1;
		headerResumen
				.setTotalInicioRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[ultimoElemento]
						.getRepInicioRutaPromedio())));
		headerResumen
				.setTotalPrimerVisitaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[ultimoElemento]
						.getRepPrimeraVisitaProm())));
		headerResumen
				.setTotalUltimaVisitaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[ultimoElemento]
						.getRepUltimaVisitaPromedio())));
		headerResumen
				.setTotalFinRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen[ultimoElemento]
						.getRepFinRutaPromedio())));

		headerResumen.setTotalCauNoViveAhi(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauNoViveAhi()));
		headerResumen.setTotalCauNoPago(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauNoPago()));
		headerResumen.setTotalCauNoDejoFicha(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauNoDejoFicha()));
		headerResumen.setTotalCauCambioDom(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauCambioDom()));
		headerResumen.setTotalCauCerradoTotal(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauCambioDom()));
		headerResumen.setTotalCauDifEnCobro(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauDifEnCobro()));
		headerResumen.setTotalCauFueraZona(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauFueraZona()));
		headerResumen
				.setTotalCauNoMetioPedido(String
						.valueOf(arrayRepResumen[ultimoElemento]
								.getCauNoMetioPedido()));
		headerResumen
				.setTotalCauDomIncompleto(String
						.valueOf(arrayRepResumen[ultimoElemento]
								.getCauDomIncompleto()));
		headerResumen.setTotalCauNoEsperaReparto(String
				.valueOf(arrayRepResumen[ultimoElemento]
						.getCauNoEsperaReparto()));
		headerResumen
				.setTotalCauExtravioFicha(String
						.valueOf(arrayRepResumen[ultimoElemento]
								.getCauExtravioFicha()));
		headerResumen.setTotalCauOtro(String
				.valueOf(arrayRepResumen[ultimoElemento].getCauOtro()));

		headerResumen
				.setTotalTiempoPromedioVisita(calcularTiempoPromedioVisitaRepResumen(arrayRepResumen));
		headerResumen
				.setTotalProductividadOrdenHora(formatearDosDigitos(calcularProductividadOrdenHoraRepResumen(arrayRepResumen)));
		headerResumen
				.setTotalCauTotal(calcularCausasNoAceptacionRepartoTotalRepResumen(arrayRepResumen));
		return headerResumen;
	}

	private String calcularTiempoPromedioVisitaRepResumen(
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen[] arrayRepResumen) {
		String tiempoPromedioVisita = "00:00";
		long sumUltimaVisitaPromedio = 0;
		long sumPrimerVisitaPromedio = 0;
		int sumOrdenRepDirecto = 0;
		int sumOrdenSinRepDirecto = 0;
		int sumPrimerasOrdenes = 0;
		int sumRutasNormales = 0;
		int totalDivisor = 0;
		int ultimoElemento = 0;
		long resultado = 0;
		List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> listaDetalles = Arrays
				.asList(arrayRepResumen);

		for (com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen detalle : listaDetalles) {
			sumOrdenRepDirecto += detalle.getOrdenConRepartoDir();
			sumOrdenSinRepDirecto += detalle.getOrdenSinRepartoDir();
			sumPrimerasOrdenes += detalle.getPrimerasOrdenes();
			sumRutasNormales += detalle.getRutasNormales();
		}

		try {
			if (sumRutasNormales > 0) {
				totalDivisor = (sumOrdenRepDirecto + sumOrdenSinRepDirecto + sumPrimerasOrdenes)
						/ sumRutasNormales;
				if (totalDivisor > 0) {
					ultimoElemento = listaDetalles.size() - 1;
					sumUltimaVisitaPromedio = obtenerSegundosHora(listaDetalles
							.get(ultimoElemento).getRepUltimaVisitaPromedio());
					sumPrimerVisitaPromedio = obtenerSegundosHora(listaDetalles
							.get(ultimoElemento).getRepPrimeraVisitaProm());
					resultado = (sumUltimaVisitaPromedio - sumPrimerVisitaPromedio)
							/ totalDivisor;
					tiempoPromedioVisita = formatIntoHHMMColumnasCalculadas(resultado);
				}
			}
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		return tiempoPromedioVisita;
	}

	private double calcularProductividadOrdenHoraRepResumen(
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen[] arrayRepResumen) {

		double productividadOrdenHora = 0;
		double sumUltimaVisitaPromedio = 0;
		double sumPrimerVisitaPromedio = 0;
		double sumOrdenRepDirecto = 0;
		double sumOrdenSinRepDirecto = 0;
		double sumPrimerasOrdenes = 0;
		double sumRutasNormales = 0;
		double totalDivisor = 0;
		int ultimoElemento = 0;

		List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> listaDetalles = Arrays
				.asList(arrayRepResumen);
		ultimoElemento = listaDetalles.size() - 1;
		sumUltimaVisitaPromedio = obtenerTiempoEnDias(listaDetalles.get(
				ultimoElemento).getRepUltimaVisitaPromedio());
		sumPrimerVisitaPromedio = obtenerTiempoEnDias(listaDetalles.get(
				ultimoElemento).getRepPrimeraVisitaProm());

		for (com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen detalle : listaDetalles) {
			sumOrdenRepDirecto += detalle.getOrdenConRepartoDir();
			sumOrdenSinRepDirecto += detalle.getOrdenSinRepartoDir();
			sumPrimerasOrdenes += detalle.getPrimerasOrdenes();
			sumRutasNormales += detalle.getRutasNormales();
		}

		totalDivisor = (sumUltimaVisitaPromedio - sumPrimerVisitaPromedio) * 24;

		try {
			if (totalDivisor > 0 && sumRutasNormales > 0) {
				productividadOrdenHora = ((sumOrdenRepDirecto
						+ sumOrdenSinRepDirecto + sumPrimerasOrdenes) / sumRutasNormales)
						/ totalDivisor;
			}
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		return productividadOrdenHora;
	}

	private String calcularCausasNoAceptacionRepartoTotalRepResumen(
			com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen[] arrayReporteResumen) {
		String causaTotal = null;
		double resultadoTotal = 0;
		List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> arrayRepResumen = Arrays
				.asList(arrayReporteResumen);
		int ultimoElemento = arrayRepResumen.size() - 1;

		double causaNoViveAhi = arrayRepResumen.get(ultimoElemento)
				.getCauNoViveAhi();
		double causaNoPago = arrayRepResumen.get(ultimoElemento).getCauNoPago();
		double causaNoDejoFicha = arrayRepResumen.get(ultimoElemento)
				.getCauNoDejoFicha();
		double causaCambioDom = arrayRepResumen.get(ultimoElemento)
				.getCauCambioDom();
		double causaCerradoTotal = arrayRepResumen.get(ultimoElemento)
				.getCauCerradoTotal();
		double causaDifEnCobro = arrayRepResumen.get(ultimoElemento)
				.getCauDifEnCobro();
		double causaFueraZona = arrayRepResumen.get(ultimoElemento)
				.getCauFueraZona();
		double causaNoMetioPedido = arrayRepResumen.get(ultimoElemento)
				.getCauNoMetioPedido();
		double causaDomIncompleto = arrayRepResumen.get(ultimoElemento)
				.getCauDomIncompleto();
		double causaNoEsperaReparto = arrayRepResumen.get(ultimoElemento)
				.getCauNoEsperaReparto();
		double causaExtravioFicha = arrayRepResumen.get(ultimoElemento)
				.getCauExtravioFicha();
		double causaOtro = arrayRepResumen.get(ultimoElemento).getCauOtro();

		resultadoTotal = causaNoViveAhi + causaNoPago + causaNoDejoFicha
				+ causaCambioDom + causaCerradoTotal + causaDifEnCobro
				+ causaFueraZona + causaNoMetioPedido + causaDomIncompleto
				+ causaNoEsperaReparto + causaExtravioFicha + causaOtro;
		resultadoTotal = formatearDosDigitos(resultadoTotal);
		causaTotal = String.valueOf(resultadoTotal);
		return causaTotal;
	}

	private double calcularProductividadOrdenHoraDetalle(
			String repUltimaVisitaPromedio, String repPrimerVisitaPromedio,
			int ordenRepDirecto, int ordenesSinRepDirecto, int primerasOrdenes,
			int rutasNormales) {
		double productividadOrdenHora = 0;
		double segRepUltimaVisitaProm = obtenerTiempoEnDias(repUltimaVisitaPromedio);
		double segRepPrimerVisitaProm = obtenerTiempoEnDias(repPrimerVisitaPromedio);
		double ordenesRepDir = ordenRepDirecto;
		double ordenesSinRepDir = ordenesSinRepDirecto;
		double primerasOrdenes1 = primerasOrdenes;
		double rutasNormales1 = rutasNormales;

		double productividadOrdenHoraDivisor = (segRepUltimaVisitaProm - segRepPrimerVisitaProm) * 24;

		if (productividadOrdenHoraDivisor > 0 && rutasNormales > 0) {

			productividadOrdenHora = ((ordenesRepDir + ordenesSinRepDir + primerasOrdenes1) / rutasNormales1)
					/ productividadOrdenHoraDivisor;
		}
		return productividadOrdenHora;
	}

	private double obtenerTiempoEnDias(String horaS) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date tTEnRu;
		double horas = 0;
		double minutos = 0;
		try {
			tTEnRu = sdf.parse(horaS);
			calendar.setTime(tTEnRu);
			horas = calendar.get(Calendar.HOUR_OF_DAY);
			minutos = calendar.get(Calendar.MINUTE);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		return (horas / 24) + ((minutos / 60) / 24);
	}

	public Reporte[] obtenerReportes(int idLdc) {

		List<SelectItem> valores = new ArrayList<SelectItem>();
		Reporte[] arrayReportes = null;
		try {

			ReportesControllerStub stub = new ReportesControllerStub();
			ReportesControllerStub.ObtenerReportes request = null;
			ReportesControllerStub.ObtenerReportesResponse response = null;

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			request = new ReportesControllerStub.ObtenerReportes();
			request.setIdLdc(idLdc);
			request.setIdUsuario(config != null ? config.getIdUsuario() : 20);
			response = stub.obtenerReportes(request);
			arrayReportes = response.get_return();

			/*
			 * SelectItem itemSel = new SelectItem();
			 * itemSel.setLabel("Selecciona Reporte"); itemSel.setValue(0);
			 * valores.add(itemSel);
			 */

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayReportes;
	}

	protected List<ModelReporteItemsNoEscaneados> datosReporteItemsNoEscaneados(
			int idUsuario, int zona, int campania) {
		List<ModelReporteItemsNoEscaneados> listaRep = new ArrayList<ModelReporteItemsNoEscaneados>();
		try {
			ReporteItemsNoEscaneadosControllerStub stub = new ReporteItemsNoEscaneadosControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ReporteItemsNoEscaneadosControllerStub.ObtenerConsultaItemsNoEscaneados param = new ReporteItemsNoEscaneadosControllerStub.ObtenerConsultaItemsNoEscaneados();
			param.setZona(zona);
			param.setCampania(campania);
			param.setIdUsuario(config.getIdUsuario());
			ReporteItemsNoEscaneadosControllerStub.ObtenerConsultaItemsNoEscaneadosResponse response = stub
					.obtenerConsultaItemsNoEscaneados(param);

			ModelRepItemsNoEscaneados[] arreglo = response.get_return();
			if (arreglo != null) {
				for (ModelRepItemsNoEscaneados reg : arreglo) {
					ModelReporteItemsNoEscaneados r = new ModelReporteItemsNoEscaneados();
					r.setRuta(reg.getRuta());
					r.setNombreChofer(reg.getNombreChofer());
					r.setRegistro(reg.getRegistro());
					r.setCodItem(reg.getCodItem());
					r.setDescrItem(reg.getDescrItem());
					r.setCampania(reg.getCampania());
					r.setZona(reg.getZona());
					r.setClaveOrden(reg.getClaveOrden());
					r.setEan13(reg.getEan13());
					r.setFsc(reg.getFsc());
					listaRep.add(r);
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRep;
	}

	public List<ModelReporteResumenGeneralZonas> consultaDatosReporteResumenGeneralZona(
			String campania, String idZona, String idDivision) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		List<ModelReporteResumenGeneralZonas> res = new ArrayList<ModelReporteResumenGeneralZonas>();
		CallableStatement cs = null;
		NumberFormat nf = new DecimalFormat("##0.00");
		if (conn != null) {
			try {

				cs = conn
						.prepareCall("{call SP_ReporteResumenGeneralZona( ?, ?, ?)}");
				cs.setObject("P_CAMPANIA", campania, Types.VARCHAR);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_ID_DIVISION", idDivision, Types.INTEGER);
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);

				while (rs.next()) {
					try {
						ModelReporteResumenGeneralZonas r = new ModelReporteResumenGeneralZonas();
						r.setZona(rs.getString("ZONA"));
						r.setCampania(rs.getString("CAMPANIA"));
						r.setMail(rs.getString("MAIL"));
						r.setLDC(rs.getString("LDC"));
						r.setOrdenes("" + rs.getInt("ORDENES"));
						r.setPendientes("" + rs.getInt("PENDIENTES"));

						r.setEfectivoOrds1a("" + rs.getInt("ORD1A_EFECTIVO"));
						r.setEfectivoOrds2a("" + rs.getInt("ORD2A_EFECTIVO"));
						r.setEfectivoMonto1a(nf.format(rs
								.getDouble("MONTO1A_EFECTIVO")));
						r.setEfectivoMonto2a(nf.format(rs
								.getDouble("MONTO2A_EFECTIVO")));
						int efectivoOrdenes = rs.getInt("ORD1A_EFECTIVO")
								+ rs.getInt("ORD2A_EFECTIVO");
						r.setEfectivoOrdenes("" + efectivoOrdenes);
						double efectivoMonto = rs.getDouble("MONTO1A_EFECTIVO")
								+ rs.getDouble("MONTO2A_EFECTIVO");
						r.setEfectivoMonto(nf.format(efectivoMonto));
						int ordBanco = rs.getInt("ORD1A_BANCO")
								+ rs.getInt("ORD2A_BANCO");
						int ordRecibo = rs.getInt("ORD1A_RECIBO")
								+ rs.getInt("ORD2A_RECIBO");
						r.setBancoOrdenes("" + ordBanco);
						r.setFichaOrdenes("" + ordRecibo);
						double bancoMonto = rs.getDouble("MONTO1A_BANCO")
								+ rs.getDouble("MONTO2A_BANCO");
						double reciboMonto = rs.getDouble("MONTO1A_RECIBO")
								+ rs.getDouble("MONTO2A_RECIBO");
						r.setBencoMonto(nf.format(bancoMonto));
						r.setFichaMonto(nf.format(reciboMonto));
						r.setCierreOrdenes("" + rs.getInt("ORDENES_CIERRE"));
						r.setCierreMonto(nf.format(rs.getDouble("MONTO_CIERRE")));
						r.setEnvioOrdenes("" + rs.getInt("ORDENES_BAJA"));
						r.setEnvioMonto(nf.format(rs.getDouble("MONTO_BAJA")));
						int ordenesDevueltas = rs.getInt("ORDENES_DEVUELTAS");
						double ordsDevueltasDou = rs
								.getDouble("ORDENES_DEVUELTAS");
						double porcentajeDev = ordsDevueltasDou
								/ rs.getDouble("ORDENES") * 100.00;
						if (porcentajeDev > 100) {
							porcentajeDev = 100;
						}
						r.setPorcDevolucion(nf.format(porcentajeDev));
						double ordsAceptacion = rs.getDouble("ORDS_ACEPTACION");
						double porcentajeAc = ordsAceptacion
								/ rs.getDouble("ORDENES") * 100.00;
						if (porcentajeAc > 100) {
							porcentajeAc = 100;
						}
						r.setPorcAceptacion(nf.format(porcentajeAc));
						r.setOrdsAceptacion("" + ordsAceptacion);
						r.setNr("" + rs.getInt("NR"));
						res.add(r);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}

		return res;
	}

	public List<SelectItem> obtenerDivisiones() {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		List<SelectItem> res = new ArrayList<SelectItem>();
		CallableStatement cs = null;

		if (conn != null) {
			try {

				cs = conn.prepareCall("{call SP_ObtenerDivisiones()}");

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);

				while (rs.next()) {
					try {
						SelectItem r = new SelectItem();
						r.setValue(rs.getInt("ID_DIVISION"));
						r.setLabel(rs.getString("NOMBRE"));
						res.add(r);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}

		return res;
	}

	public List<SelectItem> obtenerCampaniasReportes() {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		List<SelectItem> res = new ArrayList<SelectItem>();
		CallableStatement cs = null;

		if (conn != null) {
			try {

				cs = conn
						.prepareCall("{call SP_ObtenerCampaniasReporteResumenGeneralZona()}");

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);

				while (rs.next()) {
					try {
						SelectItem r = new SelectItem();
						r.setValue(rs.getString("ID"));
						r.setLabel(rs.getString("DESCRIPCION"));
						res.add(r);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}

		return res;
	}
	
	/**
	 *Metodo para generar la lista de objetos con los detalles de los reportes de las liquidaciones pendientes
	 *por enviar correo 
	 *previamente recuperado
	 * @throws AxisFault 
	 */
	public List<ModelOrdenesDejadasRecolectadas> generarListaReportesOrdenesDejadasRecolectadas(List<LiquidacionRepartoDTO> liquidaciones, int estatus) throws AxisFault {
		OrdenesDejadasRecolectadasStub stubOrdenes = new OrdenesDejadasRecolectadasStub();
		/*if(config==null) {
			config = (Configuracion) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap()
					.get("configuracion");
		}*/
		List<ModelOrdenesDejadasRecolectadas> ordenesDejadasRecolectadas = null;
		
		ordenesDejadasRecolectadas = obtenerOrdenesPup(liquidaciones, estatus, stubOrdenes, 1);
		System.out.println("total de ordenes " + ordenesDejadasRecolectadas.size());
		if(ordenesDejadasRecolectadas != null && ordenesDejadasRecolectadas.size()>0) {
			for(ModelOrdenesDejadasRecolectadas orden: ordenesDejadasRecolectadas) {
				orden.setDetalleCajas(recuperarCajasPorOrdenPup(estatus, orden.getIdSalidaReparto(), orden.getIdPup(), 1, stubOrdenes));
				System.out.println("orden " + orden.getIdSalidaReparto() + " con cajas " + orden.getDetalleCajas().size());
				orden.setDetallePremios(recuperarPremiosPorOrdenPup(estatus, orden.getIdSalidaReparto(), orden.getIdPup(), 1, stubOrdenes));
				System.out.println("orden " + orden.getIdSalidaReparto() + " premios " + orden.getDetallePremios().size());
				orden.setDetalleDocumentos(recuperarDocumentosPorOrdenPup(estatus, orden.getIdSalidaReparto(), orden.getIdPup(), 1, stubOrdenes));
				System.out.println("orden " + orden.getIdSalidaReparto() + " documentos " + orden.getDetalleDocumentos());
			}
		}
		
		return ordenesDejadasRecolectadas;
	}
	
	/**
	 * 
	 * Metodo para generar la lista de ordenes por liquidacion
	 * @param liquidaciones lista de liquidaciones recuperada previamente al consultar los correos pendientes
	 * @param estatus para indicar que tipo de orden se va a recuperar
	 * esto se sabe en base al estatus de correo RECOLECTADO y DEJADO
	 * @param stubOrdenes objeto stub creado ya desde donde se llama a este metodo
	 * @param idUsuario
	 * @return lista con la lista de Ordenes PUP recuperadas
	 */
	private List<ModelOrdenesDejadasRecolectadas> obtenerOrdenesPup(List<LiquidacionRepartoDTO> liquidaciones, int estatus, 
			OrdenesDejadasRecolectadasStub stubOrdenes, int idUsuario) {
		
		List<ModelOrdenesDejadasRecolectadas> ordenesPup = new ArrayList<ModelOrdenesDejadasRecolectadas>();
		ObtenerPUPOrdenesDejadasRecolectadas param = new ObtenerPUPOrdenesDejadasRecolectadas();
		ObtenerPUPOrdenesDejadasRecolectadasResponse response = null;
		ModelOrdenesDejadasRecolectadas ordenPup = null;
		PUPDTO [] pupdto = null;
		
		try {
			String url = Utils.modificarUrlServicioWeb(stubOrdenes._getServiceClient()
					.getOptions().getTo().getAddress());
			stubOrdenes
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stubOrdenes._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);
			
			for(LiquidacionRepartoDTO salida: liquidaciones) {
				
				param.setIdEstatus(estatus);
				param.setIdSalidaReparto(salida.getIdSalidaReparto());
				param.setIdUsuario(idUsuario);
				param.setTipoLiquidacion(null);
				response = stubOrdenes.obtenerPUPOrdenesDejadasRecolectadas(param);
				pupdto = response.get_return();
				if(pupdto != null) {
					for(PUPDTO pup: pupdto) {
						ordenPup = new ModelOrdenesDejadasRecolectadas();
						ordenPup.setIdPup(pup.getIdPUP());
						ordenPup.setCorreo(pup.getCorreo());
						ordenPup.setIdSalidaReparto(salida.getIdSalidaReparto());
						ordenesPup.add(ordenPup);
					}
				}
				System.out.println("consulto con la liquidacion " + salida.getIdSalidaReparto() + " y obtuvo estos resultados " + ordenesPup.size() + " con estatus " + estatus);
			}
			
		}catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ordenesPup;
	}
	
	/**
	 * Metodo para recuperar las cajas relacionadas con la orden pup 
	 * @param estatus
	 * @param salidaReparto
	 * @param idPup
	 * @param idUsuario
	 * @param stubOrdenes
	 * @return lista con el detalle de cajas
	 */
	private List<ModelDetalleCajas> recuperarCajasPorOrdenPup(int estatus, long salidaReparto, long idPup, int idUsuario,
			OrdenesDejadasRecolectadasStub stubOrdenes) {
		List<ModelDetalleCajas> detalleCajas = new ArrayList<ModelDetalleCajas>();
		ObtenerCajasOrdenDejadaRecolectada param = new ObtenerCajasOrdenDejadaRecolectada();
		ModelDetalleCajas detalleCaja = null;
		ObtenerCajasOrdenDejadaRecolectadaResponse response = null;
		CajaOrdenDejadaRecolectadaPUPDTO [] cajaOrdenDTO = null;
		try {
			String url = Utils.modificarUrlServicioWeb(stubOrdenes._getServiceClient()
					.getOptions().getTo().getAddress());
			stubOrdenes
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stubOrdenes._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);
			
			param.setIdEstatus(estatus);
			param.setIdPUP(idPup);
			param.setIdSalidaReparto(salidaReparto);
			param.setIdUsuario(idUsuario);
			
			response = stubOrdenes.obtenerCajasOrdenDejadaRecolectada(param);
			cajaOrdenDTO = response.get_return();
			
			if(cajaOrdenDTO != null) {
				for(CajaOrdenDejadaRecolectadaPUPDTO caja: cajaOrdenDTO) {
					detalleCaja = new ModelDetalleCajas();
					detalleCaja.setRegistro(caja.getRegistro());
					detalleCaja.setCampana(caja.getCampania());
					detalleCaja.setCodigoBarras(caja.getCodigoBarras());
					detalleCaja.setDejadoPup(caja.getDejadoPUP());
					detalleCaja.setItem(caja.getItem());
					detalleCaja.setOrden(caja.getOrden());
					detalleCaja.setNombre(caja.getNombre());
					detalleCaja.setRecolectadoPup(caja.getRecolectadoPUP());
					detalleCaja.setZona(caja.getZona());
					detalleCajas.add(detalleCaja);
				}
			}
			
		}catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return detalleCajas;
	}
	
	private List<ModelDetallePremios> recuperarPremiosPorOrdenPup(int estatus, long salidaReparto, long idPup, int idUsuario,
			OrdenesDejadasRecolectadasStub stubOrdenes) {
		List<ModelDetallePremios> detallesPremio = new ArrayList<ModelDetallePremios>();
		ObtenerPremiosUnitariosOrdenDejadaRecolectada param = new ObtenerPremiosUnitariosOrdenDejadaRecolectada();
		ObtenerPremiosUnitariosOrdenDejadaRecolectadaResponse response = null;
		PremioUnitarioOrdenDejadaRecolectadaPUPDTO [] premioDTO = null;
		ModelDetallePremios detallePremio = null;
		
		try {
			String url = Utils.modificarUrlServicioWeb(stubOrdenes._getServiceClient()
					.getOptions().getTo().getAddress());
			stubOrdenes
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stubOrdenes._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);
			
			param.setIdEstatus(estatus);
			param.setIdPUP(idPup);
			param.setIdSalidaReparto(salidaReparto);
			param.setIdUsuario(idUsuario);
			response = stubOrdenes.obtenerPremiosUnitariosOrdenDejadaRecolectada(param);
			premioDTO = response.get_return();
			
			if(premioDTO != null) {
				for(PremioUnitarioOrdenDejadaRecolectadaPUPDTO premio: premioDTO) {
					detallePremio = new ModelDetallePremios();
					detallePremio.setCantidad(premio.getCantidad());
					detallePremio.setDejadoPup(premio.getDejadoPUP());
					detallePremio.setEan13(premio.getEan13());
					detallePremio.setFsc(premio.getFsc());
					detallePremio.setRecolectadoPup(premio.getRecolectadoPUP());
					detallesPremio.add(detallePremio);
				}
			}
		}catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return detallesPremio;
	}
	
	/**
	 * Metodo para recuperar los registros de documentos por orden pup para reportes de ordenes recolectas y dejadas
	 * @param estatus
	 * @param salidaReparto
	 * @param idPup
	 * @param idUsuario
	 * @param stubOrdenes
	 * @return
	 */
	private List<ModelDetalleDocumento> recuperarDocumentosPorOrdenPup(int estatus, long salidaReparto, long idPup, int idUsuario,
			OrdenesDejadasRecolectadasStub stubOrdenes) {
		List<ModelDetalleDocumento> detalleDocumentos = new ArrayList<ModelDetalleDocumento>();
		ObtenerDocumentosOrdenDejadaRecolectada param = new ObtenerDocumentosOrdenDejadaRecolectada();
		ObtenerDocumentosOrdenDejadaRecolectadaResponse response = null;
		DocumentoOrdenDejadaRecolectadaPUPDTO [] documentosDTO = null;
		ModelDetalleDocumento documentoModel = null;
		
		try {
			String url = Utils.modificarUrlServicioWeb(stubOrdenes._getServiceClient()
					.getOptions().getTo().getAddress());
			stubOrdenes
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stubOrdenes._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);
			
			param.setIdEstatus(estatus);
			param.setIdPUP(idPup);
			param.setIdSalidaReparto(salidaReparto);
			param.setIdUsuario(idUsuario);
			response = stubOrdenes.obtenerDocumentosOrdenDejadaRecolectada(param);
			
			documentosDTO = response.get_return();
			if(documentosDTO != null) {
				documentoModel = new ModelDetalleDocumento();
				for(DocumentoOrdenDejadaRecolectadaPUPDTO documento: documentosDTO) {
					documentoModel.setEnviadosCods(documento.getCodEnviado());
					documentoModel.setEnviadosRemitos(documento.getRemitoEnviado());
					documentoModel.setRecibidosCods(documento.getCodRecibido());
					documentoModel.setRecibidosRemitos(documento.getRemitoRecibido());
					documentoModel.setRecolectadosCods(documento.getCodRecolectado());
					documentoModel.setRecolectadosRemitos(documento.getRemitoRecolectado());
					documentoModel.setRegitro(documento.getRegistro());
					detalleDocumentos.add(documentoModel);
				}
			}
			
		}catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return detalleDocumentos;
	}
	
	/**
	 * Metodo para recuperar la lista de liquidaciones pendientes por recolectadas y dejadas
	 * @return
	 */
	public List<LiquidacionRepartoDTO> obtieneListaLiquidacionesRecolectados() {
		List<LiquidacionRepartoDTO> liquidaciones = new ArrayList<LiquidacionRepartoDTO>();
		com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMail param = new com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMail();
		ObtenerListaLiquidacionesMailResponse response = new ObtenerListaLiquidacionesMailResponse();
		LiquidacionRepartoDTO [] repartoDTO = null;
		
		try {
			OrdenesDejadasRecolectadasStub stubOrdenes = new OrdenesDejadasRecolectadasStub();
			String url = Utils.modificarUrlServicioWeb(stubOrdenes._getServiceClient()
					.getOptions().getTo().getAddress());
			stubOrdenes
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stubOrdenes._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);
			
			response = stubOrdenes.obtenerListaLiquidacionesMail(param);
			
			repartoDTO = response.get_return();
			if(repartoDTO != null) {
				for(LiquidacionRepartoDTO reparto: repartoDTO) {
					liquidaciones.add(reparto);
				}
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return liquidaciones;
		
	}
}
