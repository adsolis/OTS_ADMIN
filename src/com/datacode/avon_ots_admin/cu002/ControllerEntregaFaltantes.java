package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.EntregaFaltantesStub;
import com.datacode.avon_ots_ws.EntregaFaltantesStub.EntregaFaltantes;

public class ControllerEntregaFaltantes {

	private static final String M1 = "El Registro <REGISTRO> ingresado no tiene ítems por entregar.";

	private static final String M2 = "No existen coincidencias para la representante indicada";

	private static final String M4 = "Se ha realizado correctamente la entrega de los ítems faltantes";

	private static final String M5 = "No se registraron ítems a entregar";

	private static final String M6 = "La orden seleccionada no tiene ítems faltantes por entregar";

	Configuracion configuracion;

	Utils utils;

	private Boolean mostrarGridConsulta = false;

	private String mensaje = "";

	private String registro = null;

	private String domicilio = null;

	private String nombre = null;

	private Integer idRepresentante = null;

	private Boolean panelFormularioActivo = false;

	private Boolean panelRepresentantesActivo = false;

	private Boolean panelOrdenesActivo = false;

	private Boolean panelDetalleActivo = false;

	private Boolean panelCajasActivo = false;

	private Boolean panelPremiosUnitariosActivo = false;

	private List<EntregaFaltantes> listaRepresentantes = new ArrayList<EntregaFaltantes>();

	private EntregaFaltantes representanteSeleccionado = new EntregaFaltantes();

	private List<EntregaFaltantes> listaOrdenes = new ArrayList<EntregaFaltantes>();

	private EntregaFaltantes ordenSeleccionada = new EntregaFaltantes();

	private List<EntregaFaltantes> listaCajas = new ArrayList<EntregaFaltantes>();

	private List<EntregaFaltantes> listaPremiosUnitarios = new ArrayList<EntregaFaltantes>();

	private List<EntregaFaltantes> listaPremiosUnitariosNoAgrupados = new ArrayList<EntregaFaltantes>();

	private List<EntregaFaltantes> listaItemsEntregar = new ArrayList<EntregaFaltantes>();

	private Integer cantidadPremios;

	private Integer cantidadCajas;

	private UIForm frmModal;

	private String codigoDeBarras = "";

	/**
	 * Constructor de la clase ControllerEntregaFaltantes.
	 */
	public ControllerEntregaFaltantes() {
		setConfiguracion((Configuracion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion"));
		setUtils(new Utils());
		setPanelFormularioActivo(true);
		setPanelRepresentantesActivo(false);
		setPanelOrdenesActivo(false);
		setPanelDetalleActivo(false);
		setListaRepresentantes(new ArrayList<EntregaFaltantes>());
		setListaOrdenes(new ArrayList<EntregaFaltantes>());
		setListaCajas(new ArrayList<EntregaFaltantes>());
		setListaPremiosUnitarios(new ArrayList<EntregaFaltantes>());
		setPanelCajasActivo(false);
		setPanelPremiosUnitariosActivo(false);
	}

	/**
	 * Método que consulta a los representantes.
	 */
	public void consultar() {
		setMensaje("");
		if (getRegistro() != null && getRegistro().length() > 0 && getRegistro().trim().length() > 0) {
			setNombre("");
			consultarRepresentantes();
			setPanelRepresentantesActivo(false);
			if (getListaRepresentantes() != null && getListaRepresentantes().size() == 1) {
				setRepresentanteSeleccionado(getListaRepresentantes().get(0));
				consultarOrdenes();
			} else {
				setListaRepresentantes(new ArrayList<EntregaFaltantes>());
				//setMensaje(M3);
				setMensaje(M1.replace("<REGISTRO>", getRegistro()));
			}
		} else if (getNombre() != null && getNombre().length() > 0 && getNombre().trim().length() > 0) {
			consultarRepresentantes();
		} else {
			setMensaje("Debe de ingresar al menos un criterio de búsqueda");
		}
	}

	/**
	 * Método que consulta las órdenes.
	 */
	private void consultarOrdenes() {
		setMensaje("");
		setListaOrdenes(new ArrayList<EntregaFaltantes>());
		List<EntregaFaltantes> v_ordenes = new ArrayList<EntregaFaltantes>();
		try {
			EntregaFaltantesStub stub = new EntregaFaltantesStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			EntregaFaltantesStub.ObtenerOrdenes request = new EntregaFaltantesStub.ObtenerOrdenes();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			request.setP_idRepresentante(getRepresentanteSeleccionado().getIdRepresentante());
			EntregaFaltantesStub.ObtenerOrdenesResponse response = stub.obtenerOrdenes(request);
			if (response.get_return() != null) {
				EntregaFaltantes[] lista = new EntregaFaltantes[response.get_return().length];
				lista = response.get_return();
				for (int i = 0; i < lista.length; i++){
					v_ordenes.add(lista[i]);
				}
			}
			setListaOrdenes(v_ordenes);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			setListaOrdenes(new ArrayList<EntregaFaltantes>());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			setListaOrdenes(new ArrayList<EntregaFaltantes>());
		}
		if (getListaOrdenes().size() < 1) {
			setMensaje(M1);
			setMensaje(M1.replace("<REGISTRO>", getRegistro()));
			setPanelOrdenesActivo(false);
			setPanelFormularioActivo(true);
		} else {
			setPanelOrdenesActivo(true);
			setPanelFormularioActivo(false);
		}
	}

	/**
	 * Método que asigna al representante seleccionada del pop-up.
	 */
	public void asignarRepresentanteSeleccionado() {
		try {
			setNombre(String.valueOf(getRepresentanteSeleccionado().getNombreRepresentante()));
			setRegistro(String.valueOf(getRepresentanteSeleccionado().getRegistroRepresentante()));
			setDomicilio(String.valueOf(getRepresentanteSeleccionado().getDomicilio()));
			setIdRepresentante(getRepresentanteSeleccionado().getIdRepresentante());
			setPanelRepresentantesActivo(false);
			consultarOrdenes();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método que devuelve la lista de representantes para cargarlos en el pop-up.
	 */
	private void consultarRepresentantes() {
		try {
			setMensaje("");
			setListaRepresentantes(new ArrayList<EntregaFaltantes>());
			if (getNombre() != null && getNombre().length() > 0 && getNombre().trim().length() > 0) {
				setNombre(getNombre().trim());
				setPanelRepresentantesActivo(true);
			} else if (getRegistro() != null && getRegistro().length() > 0 && getRegistro().trim().length() > 0) {
				setRegistro(getRegistro().trim());
				setPanelRepresentantesActivo(false);
			}
			EntregaFaltantesStub stub = new EntregaFaltantesStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			EntregaFaltantesStub.ObtenerRepresentantes request = new EntregaFaltantesStub.ObtenerRepresentantes();
			request.setP_idUsuario(getConfiguracion().getIdUsuario());
			request.setP_registro(getRegistro());
			request.setP_nombre(getNombre());
			EntregaFaltantesStub.ObtenerRepresentantesResponse response = stub.obtenerRepresentantes(request);
			if (response.get_return() != null) {
				EntregaFaltantes[] lista = new EntregaFaltantes[response.get_return().length];
				lista = response.get_return();
				for (int i = 0; i < lista.length; i++){
					getListaRepresentantes().add(lista[i]);
				}
			}
			if (getPanelRepresentantesActivo() == false) {
				if (getListaRepresentantes().size() == 1) {
					setMensaje("");
					setNombre(getListaRepresentantes().get(0).getNombreRepresentante());
					setRegistro(getListaRepresentantes().get(0).getRegistroRepresentante());
				} else {
					setMensaje(M2);
					setPanelRepresentantesActivo(false);
				}
			} else if (listaRepresentantes.size() < 1) {
				setMensaje(M2);
				setPanelRepresentantesActivo(false);
			} else {
				setMensaje("");
				setPanelRepresentantesActivo(true);
			}
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método que muestra los ítems de una orden.
	 */
	public void mostrarDetalleOrden() {
		setMensaje("");
		setCodigoDeBarras("");
		setListaCajas(new ArrayList<EntregaFaltantes>());
		setListaPremiosUnitarios(new ArrayList<EntregaFaltantes>());
		setListaPremiosUnitariosNoAgrupados(new ArrayList<EntregaFaltantes>());
		try {
			List<EntregaFaltantes> v_listaCajas = new ArrayList<EntregaFaltantes>();
			EntregaFaltantesStub stub = new EntregaFaltantesStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			EntregaFaltantesStub.ObtenerCajas requestCajas = new EntregaFaltantesStub.ObtenerCajas();
			requestCajas.setP_idUsuario(getConfiguracion().getIdUsuario());
			requestCajas.setP_idOrden(getOrdenSeleccionada().getIdOrden());
			EntregaFaltantesStub.ObtenerCajasResponse responseCajas = stub.obtenerCajas(requestCajas);
			if (responseCajas.get_return() != null) {
				EntregaFaltantes[] lista = new EntregaFaltantes[responseCajas.get_return().length];
				lista = responseCajas.get_return();
				for (int i = 0; i < lista.length; i++){
					v_listaCajas.add(lista[i]);
				}
			}
			setListaCajas(v_listaCajas);
			List<EntregaFaltantes> v_listaPremiosUnitarios = new ArrayList<EntregaFaltantes>();
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			EntregaFaltantesStub.ObtenerPremiosUnitarios requestPremiosUnitarios = new EntregaFaltantesStub.ObtenerPremiosUnitarios();
			requestPremiosUnitarios.setP_idUsuario(getConfiguracion().getIdUsuario());
			requestPremiosUnitarios.setP_idOrden(getOrdenSeleccionada().getIdOrden());
			EntregaFaltantesStub.ObtenerPremiosUnitariosResponse responsePremiosUnitarios = stub.obtenerPremiosUnitarios(requestPremiosUnitarios);
			if (responsePremiosUnitarios.get_return() != null) {
				EntregaFaltantes[] lista = new EntregaFaltantes[responsePremiosUnitarios.get_return().length];
				lista = responsePremiosUnitarios.get_return();
				for (int i = 0; i < lista.length; i++){
					v_listaPremiosUnitarios.add(lista[i]);
				}
			}
			setListaPremiosUnitariosNoAgrupados(v_listaPremiosUnitarios);
			setListaPremiosUnitarios(agruparPremiosUnitarios(getListaPremiosUnitariosNoAgrupados()));
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			setListaCajas(new ArrayList<EntregaFaltantes>());
			setListaPremiosUnitarios(new ArrayList<EntregaFaltantes>());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			setListaCajas(new ArrayList<EntregaFaltantes>());
			setListaPremiosUnitarios(new ArrayList<EntregaFaltantes>());
		}
		if ((getListaCajas() != null && getListaCajas().size() > 0) || (getListaPremiosUnitarios() != null && getListaPremiosUnitarios().size() > 0)) {
			setPanelDetalleActivo(true);
			setPanelOrdenesActivo(true);
		} else {
			setPanelDetalleActivo(false);
			setPanelOrdenesActivo(true);
			setMensaje(M6);
		}
	}

	/**
	 * Método que limpia el detalle de las órdenes.
	 */
	public void limpiarPanelOrdenes() {
		setRegistro("");
		setNombre("");
		setMensaje("");
		setListaRepresentantes(new ArrayList<EntregaFaltantes>());
		setListaOrdenes(new ArrayList<EntregaFaltantes>());
		setPanelFormularioActivo(true);
		setPanelOrdenesActivo(false);
		setPanelRepresentantesActivo(false);
	}

	/**
	 * Método que cancela la entrega de ítems.
	 */
	public void cancelar() {
		setCodigoDeBarras("");
		setListaCajas(new ArrayList<EntregaFaltantes>());
		setListaPremiosUnitarios(new ArrayList<EntregaFaltantes>());
		setPanelDetalleActivo(false);
		setPanelCajasActivo(false);
		setPanelPremiosUnitariosActivo(false);
		setPanelOrdenesActivo(true);
	}

	/**
	 * Método que realiza la entrega de ítems.
	 */
	public void entregar() {
		setMensaje("");
		setListaItemsEntregar(new ArrayList<EntregaFaltantes>());
		agregarCajas();
		agregarPremiosUnitarios();
		if (getListaItemsEntregar().size() > 0) {
			entregarItems();
			setMensaje(M4);
			cancelar();
		} else {
			setMensaje(M5);
		}	
	}

	/**
	 * Método que agrega la lista de cajas seleccionadas para entregar.
	 */
	private void agregarCajas() {
		for (EntregaFaltantes caja : getListaCajas()) {
			if (caja.getSeleccionado()) {
				getListaItemsEntregar().add(caja);
			}
		}
	}

	/**
	 * Método que realiza la entrega de ítems.
	 */
	private int entregarItems() {
		try {
			EntregaFaltantes[] v_itemsEntregar = new EntregaFaltantes[getListaItemsEntregar().size()];
			int v_contador = 0;
			for (EntregaFaltantes v_item : getListaItemsEntregar()) {
				v_itemsEntregar[v_contador] = v_item;
				v_contador++;
			}
			EntregaFaltantesStub stub = new EntregaFaltantesStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			EntregaFaltantesStub.EntregarItems requestItems = new EntregaFaltantesStub.EntregarItems();
			requestItems.setP_items(v_itemsEntregar);
			requestItems.setP_idUsuario(getConfiguracion().getIdUsuario());
			requestItems.setP_usuario(getConfiguracion().getUsuario());
			EntregaFaltantesStub.EntregarItemsResponse responseCajas = stub.entregarItems(requestItems);
			return responseCajas.get_return();
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Método que agrega los premios y unitarios seleccionados para su entrega.
	 */
	private void agregarPremiosUnitarios() {
		for (EntregaFaltantes v_premio : getListaPremiosUnitarios()) {
			if (v_premio.getEntrega() > 0) {
				for (int i = 0; i < v_premio.getEntrega(); i++) {
					for (EntregaFaltantes v_iter : getListaPremiosUnitariosNoAgrupados()) {
						if (v_premio.getFSC() != null && v_iter.getFSC() != null && v_premio.getFSC().equals(v_iter.getFSC())) {
							getListaItemsEntregar().add(v_iter);
							boolean loSaque = false;
							int iguales = 0;
							int contador = 0;
							for (EntregaFaltantes v_entregar : getListaItemsEntregar()) {
								if (v_entregar.getIdItem() == v_iter.getIdItem()) {
									iguales++;
									if (iguales > 1) {
										getListaItemsEntregar().remove(contador);
										loSaque = true;
										break;
									}
								}
								contador++;
							}
							if (!loSaque) {
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Método que agrupa los premios y unitarios por el código FSC.
	 * @param p_premiosUnitarios
	 * @return
	 */
	private List<EntregaFaltantes> agruparPremiosUnitarios(List<EntregaFaltantes> p_premiosUnitarios) {
		List<EntregaFaltantes> v_premiosUnitarios = new ArrayList<EntregaFaltantes>();
		for (EntregaFaltantes v_iter : p_premiosUnitarios) {
			boolean yaEsta = false;
			for (EntregaFaltantes v_iter2 : v_premiosUnitarios) {
				if (v_iter.getFSC() != null && v_iter2.getFSC() != null && v_iter.getFSC().equals(v_iter2.getFSC())) {
					yaEsta = true;
					v_iter2.setFacturados(v_iter2.getFacturados() + 1);
					break;
				}
			}
			if (!yaEsta) {
				v_iter.setFacturados(1);
				v_iter.setEntrega(0);
				v_premiosUnitarios.add(v_iter);
			}
		}
		return v_premiosUnitarios;
	}

	/* COMIENZA SECCION AUTOGENERADA DE GETTERS Y SETTERS */

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getMostrarGridConsulta() {
		return mostrarGridConsulta;
	}

	public void setMostrarGridConsulta(Boolean mostrarGridConsulta) {
		this.mostrarGridConsulta = mostrarGridConsulta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setListaRepresentantes(
			List<EntregaFaltantes> listaRepresentantes) {
		this.listaRepresentantes = listaRepresentantes;
	}

	public List<EntregaFaltantes> getListaRepresentantes() {
		return listaRepresentantes;
	}

	public EntregaFaltantes getRepresentanteSeleccionado() {
		return representanteSeleccionado;
	}

	public void setRepresentanteSeleccionado(
			EntregaFaltantes representanteSeleccionado) {
		this.representanteSeleccionado = representanteSeleccionado;
	}

	public Boolean getPanelRepresentantesActivo() {
		return panelRepresentantesActivo;
	}

	public void setPanelRepresentantesActivo(Boolean panelRepresentantesActivo) {
		this.panelRepresentantesActivo = panelRepresentantesActivo;
	}

	public UIForm getFrmModal() {
		return frmModal;
	}

	public void setFrmModal(UIForm frmModal) {
		this.frmModal = frmModal;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Boolean getPanelFormularioActivo() {
		return panelFormularioActivo;
	}

	public void setPanelFormularioActivo(Boolean panelFormularioActivo) {
		this.panelFormularioActivo = panelFormularioActivo;
	}

	public List<EntregaFaltantes> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<EntregaFaltantes> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	public EntregaFaltantes getOrdenSeleccionada() {
		return ordenSeleccionada;
	}

	public void setOrdenSeleccionada(EntregaFaltantes ordenSeleccionada) {
		this.ordenSeleccionada = ordenSeleccionada;
	}

	public Boolean getPanelOrdenesActivo() {
		return panelOrdenesActivo;
	}

	public void setPanelOrdenesActivo(Boolean panelOrdenesActivo) {
		this.panelOrdenesActivo = panelOrdenesActivo;
	}

	public List<EntregaFaltantes> getListaCajas() {
		return listaCajas;
	}

	public void setListaCajas(List<EntregaFaltantes> listaCajas) {
		this.listaCajas = listaCajas;
		setPanelCajasActivo(getListaCajas() != null && getListaCajas().size() > 0);
		if (getListaCajas() != null) {
			setCantidadCajas(getListaCajas().size());
		} else {
			setCantidadCajas(0);
		}
	}

	public List<EntregaFaltantes> getListaPremiosUnitarios() {
		return listaPremiosUnitarios;
	}

	public void setListaPremiosUnitarios(
			List<EntregaFaltantes> listaPremiosUnitarios) {
		this.listaPremiosUnitarios = listaPremiosUnitarios;
		setPanelPremiosUnitariosActivo(getListaPremiosUnitarios() != null && getListaPremiosUnitarios().size() > 0);
		if (getListaPremiosUnitarios() != null) {
			setCantidadPremios(getListaPremiosUnitarios().size());
		} else {
			setCantidadPremios(0);
		}
	}

	public Boolean getPanelDetalleActivo() {
		return panelDetalleActivo;
	}

	public void setPanelDetalleActivo(Boolean panelDetalleActivo) {
		this.panelDetalleActivo = panelDetalleActivo;
	}

	public Boolean getPanelCajasActivo() {
		return panelCajasActivo;
	}

	public void setPanelCajasActivo(Boolean panelCajasActivo) {
		this.panelCajasActivo = panelCajasActivo;
	}

	public Boolean getPanelPremiosUnitariosActivo() {
		return panelPremiosUnitariosActivo;
	}

	public void setPanelPremiosUnitariosActivo(Boolean panelPremiosUnitariosActivo) {
		this.panelPremiosUnitariosActivo = panelPremiosUnitariosActivo;
	}

	public Integer getCantidadPremios() {
		return cantidadPremios;
	}

	public void setCantidadPremios(Integer cantidadPremios) {
		this.cantidadPremios = cantidadPremios;
	}

	public Integer getCantidadCajas() {
		return cantidadCajas;
	}

	public void setCantidadCajas(Integer cantidadCajas) {
		this.cantidadCajas = cantidadCajas;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public List<EntregaFaltantes> getListaPremiosUnitariosNoAgrupados() {
		return listaPremiosUnitariosNoAgrupados;
	}

	public void setListaPremiosUnitariosNoAgrupados(
			List<EntregaFaltantes> listaPremiosUnitariosNoAgrupados) {
		this.listaPremiosUnitariosNoAgrupados = listaPremiosUnitariosNoAgrupados;
	}

	public List<EntregaFaltantes> getListaItemsEntregar() {
		return listaItemsEntregar;
	}

	public void setListaItemsEntregar(List<EntregaFaltantes> listaItemsEntregar) {
		this.listaItemsEntregar = listaItemsEntregar;
	}

}
