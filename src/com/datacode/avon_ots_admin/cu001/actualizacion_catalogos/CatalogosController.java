/**
 *
 *  @since 20/03/2012
 *
 */
package com.datacode.avon_ots_admin.cu001.actualizacion_catalogos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ActualizacionCatalogosStub;
import com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ModelEstatusCatalogo;
import com.datacode.avon_ots_admin.model.Catalogo;

/**
 * @author jessica.leon
 * 
 */
public class CatalogosController {

	public int consultarCatalogosActivos() {

		int catalogosEjecucion = 0;
		ActualizacionCatalogosStub customer = null;

		try {
			customer = new ActualizacionCatalogosStub();
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			customer._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(10800000);

			ActualizacionCatalogosStub.ChecarDisponibilidadServicio actualizacionRequest = new ActualizacionCatalogosStub.ChecarDisponibilidadServicio();
			ActualizacionCatalogosStub.ChecarDisponibilidadServicioResponse actualizacionResponse = new ActualizacionCatalogosStub.ChecarDisponibilidadServicioResponse();
			actualizacionResponse = customer
					.checarDisponibilidadServicio(actualizacionRequest);
			catalogosEjecucion = actualizacionResponse.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalogosEjecucion;
	}

	public List<Catalogo> consultarEstatusActualizacionCatalogos() {

		ActualizacionCatalogosStub customer = null;
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		com.datacode.avon_ots_ws.ActualizacionCatalogosStub.Catalogo[] arrCatalogos = null;

		try {
			customer = new ActualizacionCatalogosStub();
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			customer._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(10800000);

			ActualizacionCatalogosStub.ConsultarEstatusActualizacion actualizacionRequest = new ActualizacionCatalogosStub.ConsultarEstatusActualizacion();
			ActualizacionCatalogosStub.ConsultarEstatusActualizacionResponse actualizacionResponse = new ActualizacionCatalogosStub.ConsultarEstatusActualizacionResponse();
			actualizacionResponse = customer
					.consultarEstatusActualizacion(actualizacionRequest);
			arrCatalogos = actualizacionResponse.get_return();

			if (arrCatalogos != null) {
				for (int i = 0; i < arrCatalogos.length; i++) {
					Catalogo catalogo = new Catalogo();
					catalogo.setDescripcion(arrCatalogos[i].getDescripcion());
					catalogo.setFechaEjecucion(arrCatalogos[i]
							.getFechaEjecucion());
					catalogo.setLastUpdTs(arrCatalogos[i].getLastUpdTs());
					catalogo.setLogUltimaEjecucion(arrCatalogos[i]
							.getLogUltimaEjecucion());
					catalogo.setEstatusEjecucion(arrCatalogos[i]
							.getEstatusEjecucion());
					catalogos.add(catalogo);
				}
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalogos;
	}

	public ModelEstatusCatalogo[] consultarEstatusActualizacionManual(
			int idUsuario) {

		ActualizacionCatalogosStub customer = null;
		ModelEstatusCatalogo[] arrCatalogos = null;

		try {
			customer = new ActualizacionCatalogosStub();
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			customer._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(10800000);

			ActualizacionCatalogosStub.ObtenerEstatusReplicacion actualizacionRequest = new ActualizacionCatalogosStub.ObtenerEstatusReplicacion();
			ActualizacionCatalogosStub.ObtenerEstatusReplicacionResponse actualizacionResponse = new ActualizacionCatalogosStub.ObtenerEstatusReplicacionResponse();
			actualizacionRequest.setIdUsuario(idUsuario);
			actualizacionResponse = customer
					.obtenerEstatusReplicacion(actualizacionRequest);
			arrCatalogos = actualizacionResponse.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrCatalogos;
	}

	public String actualizarFechaEjecucion(int idUsuario) {

		ActualizacionCatalogosStub customer = null;
		String mensaje = "";

		try {
			customer = new ActualizacionCatalogosStub();
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			customer._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(10800000);

			ActualizacionCatalogosStub.ActualizarFechaEjecucion actualizacionRequest = new ActualizacionCatalogosStub.ActualizarFechaEjecucion();
			ActualizacionCatalogosStub.ActualizarFechaEjecucionResponse actualizacionResponse = new ActualizacionCatalogosStub.ActualizarFechaEjecucionResponse();
			actualizacionRequest.setIdUsuario(idUsuario);
			actualizacionResponse = customer
					.actualizarFechaEjecucion(actualizacionRequest);
			mensaje = actualizacionResponse.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}

}
