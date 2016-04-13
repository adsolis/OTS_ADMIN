package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.axis2.AxisFault;
import org.richfaces.component.UIScrollableDataTable;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub;
import com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.RecolectarRemitosVentanilla;

public class ControllerRecolectarRemitosVentanilla {

	Configuracion configuracion;

	Utils utils;

//	private static final String M1 = "Favor de registrar la recolección o causa de no recolección";

	private static final String M_VALIDACION_CANTIDAD = "Si no se recolectan todos los remitos, es necesario que ingrese un comentario";

	private static final String M4 = "No se encontraron representantes con remitos a recolectar para la zona y campaña seleccionadas";

	private static final String M5 = "El campo campaña es requerido";

	private static final String M6 = "El campo zona es requerido";

	private String mensaje = "";

	private Boolean habilitarFormularioRepresentantes = true;

	private Boolean habilitarListaRepresentantes = false;

	private Boolean habilitarDetalleRemito = false;

	private Boolean habilitarControlesRepresentantes = false;

	private Integer idCampania = 0;

	private Integer idZona = 0;

	private Integer idCausaNoRecoleccion = 0;

	private Integer cantidadRepresentantes = 0;

	private List<SelectItem> listaCampanias = new ArrayList<SelectItem>();

	private List<SelectItem> listaZonas = new ArrayList<SelectItem>();

	private List<SelectItem> listaCausasNoRecoleccion = new ArrayList<SelectItem>();

	private List<RecolectarRemitosVentanilla> listaRepresentantes = new ArrayList<RecolectarRemitosVentanilla>();

	private RecolectarRemitosVentanilla representanteSeleccionado = null;

	private String cantidadRecolectada = "";

	private String comentarios = "";

	private Integer cantidadARecolectar = 0;

	private UIScrollableDataTable tblRemitos;

	private String idRowSeleccionado = "";

	/**
	 * Constructor de la clase ControllerRecolectarRemitosVentanilla.
	 * 
	 */
	public ControllerRecolectarRemitosVentanilla() {
		setConfiguracion((Configuracion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion"));
		setUtils(new Utils());
		setMensaje("");
		setHabilitarFormularioRepresentantes(true);
		setHabilitarListaRepresentantes(false);
		setHabilitarDetalleRemito(false);
		setListaRepresentantes(new ArrayList<RecolectarRemitosVentanilla>());
		obtenerListaCampanias();
		obtenerListaZonas();
		obtenerCausasNoRecoleccion();
		setHabilitarControlesRepresentantes(false);
	}

	public void consultarRepresentantes() {
		consultarRepresentantes2(true);
	}

	/**
	 * Método para consultar los representantes con remitos.
	 */
	public void consultarRepresentantes2(boolean cambiarMensaje) {
		if (cambiarMensaje) {
			setMensaje("");			
		}
		if (!validarCamposConsultaRepresentantes()) {
			return;
		}
		try {
			setListaRepresentantes(new ArrayList<RecolectarRemitosVentanilla>());
			if (verificarCierreZona()) {
				setHabilitarFormularioRepresentantes(true);
				setHabilitarListaRepresentantes(false);
				limpiarRepresentantes();
				setHabilitarControlesRepresentantes(false);
				setMensaje("La zona fue cerrada, no hay informaci\u00F3n por procesar");
			} else {
				RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				RecolectarRemitosVentanillaStub.ObtenerRepresentantes request = new RecolectarRemitosVentanillaStub.ObtenerRepresentantes();
				request.setP_idUsuario(getConfiguracion().getIdUsuario());
				request.setP_campania(getIdCampania());
				request.setP_idZona(getIdZona());
				RecolectarRemitosVentanillaStub.ObtenerRepresentantesResponse response = stub.obtenerRepresentantes(request);
				if (response.get_return() != null) {
					RecolectarRemitosVentanilla[] respuesta = new RecolectarRemitosVentanilla[response.get_return().length];
					respuesta = response.get_return();
					List<RecolectarRemitosVentanilla> v_temporal = new ArrayList<RecolectarRemitosVentanilla>();
					for (RecolectarRemitosVentanilla v_iter : respuesta) {
						v_temporal.add(v_iter);
					}
					setListaRepresentantes(v_temporal);
				}
				if (getListaRepresentantes().size() < 1) {
					if (cambiarMensaje) {
						setMensaje(M4);
					}
					setHabilitarFormularioRepresentantes(true);
					setHabilitarListaRepresentantes(false);
					limpiarRepresentantes();
					setHabilitarControlesRepresentantes(false);
				} else {
					setHabilitarFormularioRepresentantes(false);
					setHabilitarListaRepresentantes(true);
					setHabilitarControlesRepresentantes(true);
				}
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean verificarCierreZona() {
		try {
			RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			RecolectarRemitosVentanillaStub.VerificarCierreDeZona request = new RecolectarRemitosVentanillaStub.VerificarCierreDeZona();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			request.setP_campania(getIdCampania());
			request.setP_idZona(getIdZona());
			RecolectarRemitosVentanillaStub.VerificarCierreDeZonaResponse response = stub.verificarCierreDeZona(request);
			return response.get_return();
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
			return false;
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Método para limpiar el grid de representantes.
	 */
	public void cancelarRepresentantes() {
		setMensaje("");
		limpiarRepresentantes();
	}

	private boolean asignarRepresentanteSeleccionado() {
		Iterator<Object> iterator = getTblRemitos().getSelection().getKeys();
		try {
			if (getTblRemitos() == null
					|| getTblRemitos().getSelection() == null
					|| getTblRemitos().getSelection().getKeys() == null
					|| getTblRemitos().getSelection().getKeys().next() == null) {
				setMensaje("Debe seleccionar un representante");
				setRepresentanteSeleccionado(null);
				return false;
			}
			setIdRowSeleccionado(getTblRemitos().getSelection().getKeys().next().toString());
		} catch (NoSuchElementException e) {
			setMensaje("Debe seleccionar un representante");
			setRepresentanteSeleccionado(null);
			return false;
		} catch (Exception e) {
			setMensaje("Debe seleccionar un representante");
			setRepresentanteSeleccionado(null);
			return false;
		}
		int cantidad = 0;
		Integer indice = -1;
		while (iterator.hasNext()) {
			cantidad++;
			Object key = iterator.next();
			if (key instanceof Integer) {
				indice = (Integer) key;
			}
		}
		if (cantidad < 1) {
			setMensaje("Debe seleccionar un representante");
			setRepresentanteSeleccionado(null);
			return false;
		} else if (cantidad > 1) {
			setMensaje("Debe seleccionar s\u00F3lamente un representante");
			setRepresentanteSeleccionado(null);
			return false;
		}
		setRepresentanteSeleccionado(getListaRepresentantes().get(indice));
		return true;
	}

	/**
	 * Método para mostrar el detalle de un remito.
	 */
	public void mostrarDetalleRemito() {
		setMensaje("");
		if (asignarRepresentanteSeleccionado() && getRepresentanteSeleccionado() != null) {
			//setCantidadRecolectada(String.valueOf(getRepresentanteSeleccionado().getCantidadRecolectada()));
			setCantidadRecolectada("0");
			setCantidadARecolectar(getRepresentanteSeleccionado().getCantidadRecolectar());
			setIdCausaNoRecoleccion(0);
			setComentarios("");
			setHabilitarDetalleRemito(true);
			setHabilitarListaRepresentantes(true);
			setHabilitarControlesRepresentantes(false);
		}
	}

	/**
	 * Método para cancelar la recolección de un remito.
	 */
	public void cancelarRecoleccion() {
		setMensaje("");
		limpiarDetalleRemito();
	}

	/**
	 * Método para ejecutar la recolección de remitos.
	 */
	public void recolectarRemito() {
		if (validarRecoleccion()) {
			try {
				RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				RecolectarRemitosVentanillaStub.RecolectarRemitos request = new RecolectarRemitosVentanillaStub.RecolectarRemitos();
				request.setP_idUsuario(getConfiguracion().getIdUsuario());
				request.setP_cantidadRecolectados(Integer.valueOf(getCantidadRecolectada()));
				request.setP_causaNoRecoleccion(getIdCausaNoRecoleccion());
				request.setP_comentarios(getComentarios());
				request.setP_remito(getRepresentanteSeleccionado());
				request.setP_usuario(getConfiguracion().getUsuario());
				RecolectarRemitosVentanillaStub.RecolectarRemitosResponse response = stub.recolectarRemitos(request);
				response.get_return();
				setMensaje("Se ha realizado la recolección exitosamente");
				limpiarDetalleRemito();
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Método que valida los campos de la recolección de remitos.
	 *
	 * @return true, if successful
	 */
	private boolean validarRecoleccion() {
		setMensaje("");
		if (getComentarios() != null && getComentarios().trim() != null) {
			setComentarios(getComentarios().trim());
		}
		try {
			if (getIdCausaNoRecoleccion() == 0) {
				int cantidadRecolectada = Integer.valueOf(getCantidadRecolectada());
				if (cantidadRecolectada <= 0 || cantidadRecolectada > getRepresentanteSeleccionado().getCantidadRecolectar()) {
					setMensaje("Por favor captura la cantidad a recibir de manera correcta (mayor a cero o menor igual a cantidad a recolectar " + getRepresentanteSeleccionado().getCantidadRecolectar() + ")");
					return false;
				}
				if (cantidadRecolectada > 0 && cantidadRecolectada < getRepresentanteSeleccionado().getCantidadRecolectar()) {
					if (getComentarios() != null && getComentarios() != null && getComentarios().length() < 1) {
						setMensaje(M_VALIDACION_CANTIDAD);
						return false;
					}
				}
				if (getComentarios() != null && getComentarios().length() > 150) {
					setComentarios(getComentarios().substring(0, 150));
				}
			}
		} catch (NumberFormatException e) {
			setMensaje("La cantidad ingresada no es válida");
			return false;
		}
		return true;
	}

	/**
	 * Método que limpia el detalle de los remitos.
	 */
	private void limpiarDetalleRemito() {
		setHabilitarDetalleRemito(false);
		setCantidadRecolectada("");
		setCantidadARecolectar(0);
		setComentarios("");
		setHabilitarControlesRepresentantes(true);
		String mensaje = "";
		if (getMensaje() != null) {
			mensaje = "" + getMensaje();
		}
		consultarRepresentantes2(false);
		setMensaje(mensaje);
		getTblRemitos().setActiveRowKey(null);
		getTblRemitos().setSelection(null);
	}

	/**
	 * Método que limpia el grid de representantes.
	 */
	private void limpiarRepresentantes() {
		setIdCampania(0);
		setIdZona(0);
		setListaRepresentantes(new ArrayList<RecolectarRemitosVentanilla>());
		setHabilitarFormularioRepresentantes(true);
		setHabilitarDetalleRemito(false);
		setHabilitarListaRepresentantes(false);
		setHabilitarControlesRepresentantes(false);
	}

	/**
	 * Método que obtiene la lista de campañas.
	 */
	private void obtenerListaCampanias() {
		try {
			setListaCampanias(new ArrayList<SelectItem>());
			RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			RecolectarRemitosVentanillaStub.ObtenerListaCampanias request = new RecolectarRemitosVentanillaStub.ObtenerListaCampanias();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			RecolectarRemitosVentanillaStub.ObtenerListaCampaniasResponse response = stub.obtenerListaCampanias(request);
			if (response.get_return() != null) {
				String[] lista = new String[response.get_return().length];
				lista = response.get_return();
				getListaCampanias().add(new SelectItem(0, "Seleccione una campa\u00F1a"));
				setIdCampania(0);
				for(int i=0;i<lista.length;i++){
					getListaCampanias().add(new SelectItem(lista[i], lista[i]));
				}
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene la lista de zonas.
	 */
	private void obtenerListaZonas() {
		try {
			setListaZonas(new ArrayList<SelectItem>());
			RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			RecolectarRemitosVentanillaStub.ObtenerListaZonas request = new RecolectarRemitosVentanillaStub.ObtenerListaZonas();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			RecolectarRemitosVentanillaStub.ObtenerListaZonasResponse response = stub.obtenerListaZonas(request);
			if (response.get_return() != null) {
				RecolectarRemitosVentanilla[] lista = new RecolectarRemitosVentanilla[response.get_return().length];
				lista = response.get_return();
				getListaZonas().add(new SelectItem(0, "Seleccione una zona"));
				setIdZona(0);
				for(int i=0;i<lista.length;i++){
					getListaZonas().add(new SelectItem(lista[i].getIdZona(), lista[i].getDescripcionZona()));
				}
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene las causas de no recolección de remitos.
	 */
	private void obtenerCausasNoRecoleccion() {
		try {
			setListaCausasNoRecoleccion(new ArrayList<SelectItem>());
			RecolectarRemitosVentanillaStub stub = new RecolectarRemitosVentanillaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			RecolectarRemitosVentanillaStub.ObtenerCausasNoRecoleccion request = new RecolectarRemitosVentanillaStub.ObtenerCausasNoRecoleccion();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			RecolectarRemitosVentanillaStub.ObtenerCausasNoRecoleccionResponse response = stub.obtenerCausasNoRecoleccion(request);
			if (response.get_return() != null) {
				RecolectarRemitosVentanilla[] lista = new RecolectarRemitosVentanilla[response.get_return().length];
				lista = response.get_return();
				getListaCausasNoRecoleccion().add(new SelectItem(0, "Seleccione una causa"));
				setIdCausaNoRecoleccion(0);
				for(int i=0;i<lista.length;i++){
					getListaCausasNoRecoleccion().add(new SelectItem(lista[i].getIdCausaNoRecoleccion(), lista[i].getDescripcionCausaNoRecoleccion()));
				}
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método que valida los campos de consulta de representantes con remitos.
	 */
	private boolean validarCamposConsultaRepresentantes() {
		setMensaje("");
		if (getIdCampania() == 0) {
			setMensaje(M5);
			return false;
		} else if (getIdZona() == 0) {
			setMensaje(M6);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the configuracion.
	 *
	 * @return the configuracion
	 */
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	/**
	 * Sets the configuracion.
	 *
	 * @param configuracion the new configuracion
	 */
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	/**
	 * Gets the utils.
	 *
	 * @return the utils
	 */
	public Utils getUtils() {
		return utils;
	}

	/**
	 * Sets the utils.
	 *
	 * @param utils the new utils
	 */
	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	/**
	 * Gets the habilitar formulario representantes.
	 *
	 * @return the habilitar formulario representantes
	 */
	public Boolean getHabilitarFormularioRepresentantes() {
		return habilitarFormularioRepresentantes;
	}

	/**
	 * Sets the habilitar formulario representantes.
	 *
	 * @param habilitarFormularioRepresentantes the new habilitar formulario representantes
	 */
	public void setHabilitarFormularioRepresentantes(
			Boolean habilitarFormularioRepresentantes) {
		this.habilitarFormularioRepresentantes = habilitarFormularioRepresentantes;
	}

	/**
	 * Gets the lista campanias.
	 *
	 * @return the lista campanias
	 */
	public List<SelectItem> getListaCampanias() {
		return listaCampanias;
	}

	/**
	 * Sets the lista campanias.
	 *
	 * @param listaCampanias the new lista campanias
	 */
	public void setListaCampanias(List<SelectItem> listaCampanias) {
		this.listaCampanias = listaCampanias;
	}

	/**
	 * Gets the lista zonas.
	 *
	 * @return the lista zonas
	 */
	public List<SelectItem> getListaZonas() {
		return listaZonas;
	}

	/**
	 * Sets the lista zonas.
	 *
	 * @param listaZonas the new lista zonas
	 */
	public void setListaZonas(List<SelectItem> listaZonas) {
		this.listaZonas = listaZonas;
	}

	/**
	 * Gets the id campania.
	 *
	 * @return the id campania
	 */
	public Integer getIdCampania() {
		return idCampania;
	}

	/**
	 * Sets the id campania.
	 *
	 * @param idCampania the new id campania
	 */
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * Gets the id zona.
	 *
	 * @return the id zona
	 */
	public Integer getIdZona() {
		return idZona;
	}

	/**
	 * Sets the id zona.
	 *
	 * @param idZona the new id zona
	 */
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	/**
	 * Gets the lista representantes.
	 *
	 * @return the lista representantes
	 */
	public List<RecolectarRemitosVentanilla> getListaRepresentantes() {
		return listaRepresentantes;
	}

	/**
	 * Sets the lista representantes.
	 *
	 * @param listaRepresentantes the new lista representantes
	 */
	public void setListaRepresentantes(
			List<RecolectarRemitosVentanilla> listaRepresentantes) {
		this.listaRepresentantes = listaRepresentantes;
		if (getListaRepresentantes() != null) {
			setCantidadRepresentantes(getListaRepresentantes().size());
		} else {
			setCantidadRepresentantes(0);
		}
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the habilitar lista representantes.
	 *
	 * @return the habilitar lista representantes
	 */
	public Boolean getHabilitarListaRepresentantes() {
		return habilitarListaRepresentantes;
	}

	/**
	 * Sets the habilitar lista representantes.
	 *
	 * @param habilitarListaRepresentantes the new habilitar lista representantes
	 */
	public void setHabilitarListaRepresentantes(Boolean habilitarListaRepresentantes) {
		this.habilitarListaRepresentantes = habilitarListaRepresentantes;
	}

	/**
	 * Gets the habilitar detalle remito.
	 *
	 * @return the habilitar detalle remito
	 */
	public Boolean getHabilitarDetalleRemito() {
		return habilitarDetalleRemito;
	}

	/**
	 * Sets the habilitar detalle remito.
	 *
	 * @param habilitarDetalleRemito the new habilitar detalle remito
	 */
	public void setHabilitarDetalleRemito(Boolean habilitarDetalleRemito) {
		this.habilitarDetalleRemito = habilitarDetalleRemito;
	}

	/**
	 * Gets the representante seleccionado.
	 *
	 * @return the representante seleccionado
	 */
	public RecolectarRemitosVentanilla getRepresentanteSeleccionado() {
		return representanteSeleccionado;
	}

	/**
	 * Sets the representante seleccionado.
	 *
	 * @param representanteSeleccionado the new representante seleccionado
	 */
	public void setRepresentanteSeleccionado(
			RecolectarRemitosVentanilla representanteSeleccionado) {
		this.representanteSeleccionado = representanteSeleccionado;
	}

	/**
	 * Gets the lista causas no recoleccion.
	 *
	 * @return the lista causas no recoleccion
	 */
	public List<SelectItem> getListaCausasNoRecoleccion() {
		return listaCausasNoRecoleccion;
	}

	/**
	 * Sets the lista causas no recoleccion.
	 *
	 * @param listaCausasNoRecoleccion the new lista causas no recoleccion
	 */
	public void setListaCausasNoRecoleccion(
			List<SelectItem> listaCausasNoRecoleccion) {
		this.listaCausasNoRecoleccion = listaCausasNoRecoleccion;
	}

	/**
	 * Gets the id causa no recoleccion.
	 *
	 * @return the id causa no recoleccion
	 */
	public Integer getIdCausaNoRecoleccion() {
		return idCausaNoRecoleccion;
	}

	/**
	 * Sets the id causa no recoleccion.
	 *
	 * @param idCausaNoRecoleccion the new id causa no recoleccion
	 */
	public void setIdCausaNoRecoleccion(Integer idCausaNoRecoleccion) {
		this.idCausaNoRecoleccion = idCausaNoRecoleccion;
	}

	/**
	 * Gets the cantidad representantes.
	 *
	 * @return the cantidad representantes
	 */
	public Integer getCantidadRepresentantes() {
		return cantidadRepresentantes;
	}

	/**
	 * Sets the cantidad representantes.
	 *
	 * @param cantidadRepresentantes the new cantidad representantes
	 */
	public void setCantidadRepresentantes(Integer cantidadRepresentantes) {
		this.cantidadRepresentantes = cantidadRepresentantes;
	}

	/**
	 * Gets the cantidad recolectada.
	 *
	 * @return the cantidad recolectada
	 */
	public String getCantidadRecolectada() {
		return cantidadRecolectada;
	}

	/**
	 * Sets the cantidad recolectada.
	 *
	 * @param cantidadRecolectada the new cantidad recolectada
	 */
	public void setCantidadRecolectada(String cantidadRecolectada) {
		this.cantidadRecolectada = cantidadRecolectada;
	}

	/**
	 * Gets the cantidad a recolectar.
	 *
	 * @return the cantidad a recolectar
	 */
	public Integer getCantidadARecolectar() {
		return cantidadARecolectar;
	}

	/**
	 * Sets the cantidad a recolectar.
	 *
	 * @param cantidadARecolectar the new cantidad a recolectar
	 */
	public void setCantidadARecolectar(Integer cantidadARecolectar) {
		this.cantidadARecolectar = cantidadARecolectar;
	}

	/**
	 * Gets the comentarios.
	 *
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * Sets the comentarios.
	 *
	 * @param comentarios the new comentarios
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * Gets the habilitar controles representantes.
	 *
	 * @return the habilitar controles representantes
	 */
	public Boolean getHabilitarControlesRepresentantes() {
		return habilitarControlesRepresentantes;
	}

	/**
	 * Sets the habilitar controles representantes.
	 *
	 * @param habilitarControlesRepresentantes the new habilitar controles representantes
	 */
	public void setHabilitarControlesRepresentantes(
			Boolean habilitarControlesRepresentantes) {
		this.habilitarControlesRepresentantes = habilitarControlesRepresentantes;
	}

	public UIScrollableDataTable getTblRemitos() {
		return tblRemitos;
	}

	public void setTblRemitos(UIScrollableDataTable tblRemitos) {
		this.tblRemitos = tblRemitos;
	}

	public String getIdRowSeleccionado() {
		return idRowSeleccionado;
	}

	public void setIdRowSeleccionado(String idRowSeleccionado) {
		this.idRowSeleccionado = idRowSeleccionado;
	}

}
