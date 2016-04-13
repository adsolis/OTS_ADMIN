/**
 *
 *  @since 19/01/2012
 *
 */
package com.datacode.avon_ots_admin.cu003;

import java.rmi.RemoteException;
import java.util.List;

import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.RepresentanteControllerStub;

;

/**
 * @author jessica.leon
 * 
 */
public class RepresentanteController {

	private RepresentanteControllerStub customer;
	private RepresentanteControllerStub.Representante[] arrRepresentantes;

	public void actualizarSecuenciaRepresentante(
			List<Representante> representantes, int idUsuario) {

		RepresentanteControllerStub.ActualizarSecuenciaRepresentante actSecuenciaRequest = null;
		RepresentanteControllerStub.ActualizarSecuenciaRepresentanteResponse actSecuenciaResponse = null;

		for (Representante representante : representantes) {
			try {
				customer = new RepresentanteControllerStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(customer
						._getServiceClient().getOptions().getTo().getAddress());
				customer._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				actSecuenciaRequest = new RepresentanteControllerStub.ActualizarSecuenciaRepresentante();
				actSecuenciaRequest.setIdRepresentante(representante
						.getIdRepresentante());
				actSecuenciaRequest.setIdUsuario(idUsuario);
				actSecuenciaRequest.setNuevaSecuencia(Integer
						.parseInt(representante.getRepresentantePorRuta()
								.getSeqEntregaAnterior()));
				actSecuenciaRequest.setTipoCU("CUADMIN003_02");
				actSecuenciaResponse = customer
						.actualizarSecuenciaRepresentante(actSecuenciaRequest);
			} catch (RemoteException e) {
				Utils.GuardarLogMensajeBD(
						"CUADMIN003_02",
						"M5",
						"Surgió un error al actualizar en la tabla PW_REPRESENTANTES_POR_RUTA",
						e.getMessage(), idUsuario);
				e.printStackTrace();
			}
		}
	}

	public void obtenerListaRepresentantes(int idCampania, int idZona,
			int idRuta, int idUsuario, String registro, List<Representante> representantes) {

		RepresentanteControllerStub customer = null;
		RepresentanteControllerStub.SeleccionarRepresentantesConOrden request = null;
		RepresentanteControllerStub.SeleccionarRepresentantesConOrdenResponse response = null;
		Representante representante = null;

		try {
			customer = new RepresentanteControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			request = new RepresentanteControllerStub.SeleccionarRepresentantesConOrden();
			request.setIdCampania(idCampania);
			request.setIdRuta(idRuta);
			request.setIdZona(idZona);
			request.setRegistro(registro);
			request.setIdUsuario(idUsuario);
			request.setTipoCU("CUADMIN003_02");

			response = customer.seleccionarRepresentantesConOrden(request);
			arrRepresentantes = response.get_return();

			if (arrRepresentantes != null) {
				for (int i = 0; i < arrRepresentantes.length; i++) {
					representante = new Representante();
					representante.setIdRepresentante(arrRepresentantes[i]
							.getIdRepresentante());
					representante.setNombre(arrRepresentantes[i].getNombre());
					representante.setDomicilio(arrRepresentantes[i]
							.getDomicilio());
					representante.setRegistro(arrRepresentantes[i]
							.getRegistro());
					representante.getRepresentantePorRuta().setDescRuta(
							arrRepresentantes[i].getRepresentantePorRuta()
									.getDescRuta());
					representante.getRepresentantePorRuta()
							.setSeqEntregaAnterior(
									arrRepresentantes[i]
											.getRepresentantePorRuta()
											.getSeqEntregaAnterior());
					representante.getRepresentantePorRuta()
							.setSeqEntregaReciente(
									arrRepresentantes[i]
											.getRepresentantePorRuta()
											.getSeqEntregaReciente());
					representantes.add(representante);
				}
			}
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD("CUADMIN003_02", "M6",
					"Surgió un error al seleccionar representantes",
					e.getMessage(), idUsuario);
			e.printStackTrace();
		}
	}

	public String actualizarPreenrutadoRepresentantes(Integer idLDC,
			int idRepresentante, Integer idZona, Integer idRuta,
			Integer secuenciaRepresentante, Integer idUsuario, String tipoCU) {
		
		String mensaje = null;
		RepresentanteControllerStub customer = null;
		RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentante request = null;
		RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentanteResponse response = null;

		try {
			customer = new RepresentanteControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			request = new RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentante();
			request.setIdLDC(idLDC);
			request.setIdRepresentante(idRepresentante);
			request.setIdZona(idZona);
			request.setIdRuta(idRuta);
			request.setSecuenciaActual(secuenciaRepresentante);
			request.setSecuenciaAnterior(secuenciaRepresentante);
			request.setIdUsuario(idUsuario);
			request.setTipoCU(tipoCU);
			response = customer.actualizarPreenrutadoDeRepresentante(request);
			mensaje = response.get_return();

		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD("CUADMIN003_02", "M7",
					"Surgió un error al actualizar la ruta de la representante",
					e.getMessage(), idUsuario);
			e.printStackTrace();
		}
		return mensaje;
	}
}
