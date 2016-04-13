package com.datacode.avon_ots_admin.armadoRutasEspeciales;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.types.soapencoding.Array;

import com.datacode.avon_ots_admin.model.RutaEspecial;
import com.datacode.avon_ots_admin.reports.model.RutaEspecialItems;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.Campania;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ModelRutaEspecial;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ModelRutasEspecialesItems;

public class ConsultaDatosRutasEspeciales {

	public RutaEspecial generarFolio(String folio, int idRutaEspecial,
			int idUsuario) {
		RutaEspecial r = new RutaEspecial();
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.GenerarFolio param = new ArmadoRutasEspecialesControllerStub.GenerarFolio();
			param.setFolio(folio);
			param.setIdRutaEspecial(idRutaEspecial);
			param.setIdUsuario(idUsuario);
			ArmadoRutasEspecialesControllerStub.GenerarFolioResponse response = stub
					.generarFolio(param);

			ModelRutaEspecial rs = response.get_return();
			if (rs != null) {

				r.setIdRutaEspecial(rs.getIdRutaEspecial());
				r.setClaveRutaEspecial(rs.getClaveRutaEspecial());

			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public List<RutaEspecial> obtieneRegistros(int idUsuario, String registro) {
		List<RutaEspecial> regs = new ArrayList<RutaEspecial>();
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.ObtieneRegistros param = new ArmadoRutasEspecialesControllerStub.ObtieneRegistros();
			param.setRegistro(registro);
			param.setIdUsuario(idUsuario);
			ArmadoRutasEspecialesControllerStub.ObtieneRegistrosResponse response = stub
					.obtieneRegistros(param);

			ModelRutaEspecial[] rs = response.get_return();
			if (rs != null) {
				for (ModelRutaEspecial d : rs) {
					RutaEspecial r = new RutaEspecial();
					r.setIdOrden(d.getIdOrden());
					r.setClaveOrden(d.getClaveOrden());
					r.setNombre(d.getNombre());
					r.setRegistro(d.getRegistro());
					r.setBlocked(d.getCampania());
					regs.add(r);
				}

			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regs;
	}

	public List<RutaEspecialItems> obtieneItems(int idUsuario,
			String registros, int tipo) {
		List<RutaEspecialItems> regs = new ArrayList<RutaEspecialItems>();
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.ObtieneItems param = new ArmadoRutasEspecialesControllerStub.ObtieneItems();
			param.setRegistros(registros);
			param.setIdUsuario(idUsuario);
			param.setTipo(tipo);
			ArmadoRutasEspecialesControllerStub.ObtieneItemsResponse response = stub
					.obtieneItems(param);

			ModelRutasEspecialesItems[] rs = response.get_return();
			if (rs != null) {
				for (ModelRutasEspecialesItems d : rs) {
					RutaEspecialItems r = new RutaEspecialItems();
					r.setCantidadSolicitada(d.getCantidadSolicitada());
					r.setClaveOrden(d.getClaveOrden());
					r.setCodigoBarras(d.getCodigoBarras());
					r.setDescripcion(d.getDescripcion());
					r.setIdItem(d.getIdItem());
					r.setCodigoFSC(d.getCodigo_FSC());
					r.setCodigoEAN13(d.getCodigo_FSC_EAN13());
					r.setTipo(d.getTipo());
					regs.add(r);
				}

			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regs;
	}

	public Boolean guardarRuta(int idUsuario, int idRutaEspecial, String folio,
			String campania, String ordenes, String cajas) {
		Boolean exito = false;
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.GuardarRuta param = new ArmadoRutasEspecialesControllerStub.GuardarRuta();
			param.setCajas(cajas);
			param.setCampania(campania);
			param.setFolio(folio);
			param.setIdRutaEspecial(idRutaEspecial);
			param.setOrdenes(ordenes);
			param.setIdUsuario(idUsuario);
			ArmadoRutasEspecialesControllerStub.GuardarRutaResponse response = stub
					.guardarRuta(param);

			exito = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}

	public Boolean guardarRutaUnitarios(int idUsuario, int idRutaEspecial,
			String codigoFSC, int cantidad,String CodigoFSC_EAN13) {
		Boolean exito = false;
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.GuardarRutaUnitarios param = new ArmadoRutasEspecialesControllerStub.GuardarRutaUnitarios();
			param.setIdRutaEspecial(idRutaEspecial);
			param.setCantidad(cantidad);
			param.setCodigoFSC(codigoFSC);
			param.setCodigoFSC_EAN13(CodigoFSC_EAN13);
			param.setIdUsuario(idUsuario);
			ArmadoRutasEspecialesControllerStub.GuardarRutaUnitariosResponse response = stub
					.guardarRutaUnitarios(param);

			exito = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}

	public Campania[] consultaCampanias(String tipoCasoUso, Integer idUsuario) {
		Campania[] exito = null;
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.GetCampanias param = new ArmadoRutasEspecialesControllerStub.GetCampanias();
			param.setTipoCasoUso("UCM007_1");
			param.setIdUsuario(idUsuario);
			ArmadoRutasEspecialesControllerStub.GetCampaniasResponse response = stub
					.getCampanias(param);

			exito = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}

	public List<RutaEspecial> consultarRutasEspeciales(int idUsuario,
			String claveRuta, String campania) {
		List<RutaEspecial> regs = new ArrayList<RutaEspecial>();
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspeciales param = new ArmadoRutasEspecialesControllerStub.ConsultarRutasEspeciales();
			param.setCampania(campania);
			param.setIdUsuario(idUsuario);
			param.setClaveRuta(claveRuta);
			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesResponse response = stub
					.consultarRutasEspeciales(param);

			ModelRutaEspecial[] exito = response.get_return();
			if (exito != null && exito.length > 0) {
				for (ModelRutaEspecial l : exito) {
					RutaEspecial r = new RutaEspecial();
					r.setClaveOrden(l.getClaveOrden());
					r.setFechaCreacion(l.getFechaCreacion());
					r.setFechaCreacionS(l.getFechaCreacionS());
					r.setCampaniaRelacionada(l.getCampania());
					r.setNumOrdenes(l.getNumOrdenes());
					r.setIdRutaEspecial(l.getIdRutaEspecial());
					regs.add(r);
				}
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regs;
	}

	public ModelRutasEspecialesItems[] consultarRutasEspecialesDetalleCajas(
			int idUsuario, int idRutaEspecial) {
		ModelRutasEspecialesItems[] res = null;
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalle param = new ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalle();
			param.setIdUsuario(idUsuario);
			param.setIdRutaEspecial(idRutaEspecial);
			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleResponse response = stub
					.consultarRutasEspecialesDetalle(param);

			res = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ModelRutasEspecialesItems[] consultarRutasEspecialesDetalleUnitarios(
			int idUsuario, int idRutaEspecial) {
		ModelRutasEspecialesItems[] res = null;
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleUnitarios param = new ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleUnitarios();
			param.setIdUsuario(idUsuario);
			param.setIdRutaEspecial(idRutaEspecial);
			ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleUnitariosResponse response = stub
					.consultarRutasEspecialesDetalleUnitarios(param);

			res = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public String eliminarRuta(int idUsuario, int idRuta) {
		String res = "";
		try {
			ArmadoRutasEspecialesControllerStub stub = new ArmadoRutasEspecialesControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(180000);

			ArmadoRutasEspecialesControllerStub.EliminarRuta param = new ArmadoRutasEspecialesControllerStub.EliminarRuta();

			param.setIdUsuario(idUsuario);
			param.setIdRuta(idRuta);
			ArmadoRutasEspecialesControllerStub.EliminarRutaResponse response = stub
					.eliminarRuta(param);

			res = response.get_return();

		} catch (AxisFault e) {
			res = e.getMessage();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
