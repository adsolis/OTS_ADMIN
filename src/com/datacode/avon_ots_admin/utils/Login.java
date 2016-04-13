/**
 * @author jorge.torner
 * @since 03/01/2012
 */
package com.datacode.avon_ots_admin.utils;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuItem;

import com.datacode.avon_ots_admin.model.ItemMenu;
import com.datacode.avon_ots_admin.model.MenuH;
import com.datacode.avon_ots_ws.MenuControllerStub.AccionesModulos;
import com.datacode.avon_ots_ws.MenuControllerStub.ModulosMenu;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.MenuControllerStub;

/**
 * Clase para inicio de sesión
 * 
 * @author jorge.torner
 * @since 03/01/2012
 */
public class Login {
	// Obtiene el objeto Configuración con los valores cargados al inicio de
	// sesión
	Configuracion config = (Configuracion) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("configuracion");

	private String nombreUsuario;
	private String contrasenia;
	private String mensajeError;
	private String paginaH;
	private String versionSistema;
	private String fechaCompilacion;

	private List<HtmlDropDownMenu> menuDinamic;
	private HashMap<Integer, HashMap<String, Boolean>> accionesMap;
	private List<MenuH> menuDinamicS;

	public String getPaginaH() {
		return paginaH;
	}

	public void setPaginaH(String paginaH) {
		this.paginaH = paginaH;
	}

	public HashMap<Integer, HashMap<String, Boolean>> getAccionesMap() {
		if (accionesMap == null) {
			accionesMap = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("accionesM");
		}
		return accionesMap;
	}

	public void setAccionesMap(
			HashMap<Integer, HashMap<String, Boolean>> accionesMap) {
		this.accionesMap = accionesMap;
	}

	public List<MenuH> getMenuDinamicS() {
		if (menuDinamicS == null) {
			menuDinamicS = (List<MenuH>) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("menuDinaS");
		}
		return menuDinamicS;
	}

	public void setMenuDinamicS(List<MenuH> menuDinamicS) {
		this.menuDinamicS = menuDinamicS;
	}

	public List<HtmlDropDownMenu> getMenuDinamic() {
		if (menuDinamic == null) {
			menuDinamic = (List<HtmlDropDownMenu>) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("menuDina");
		}
		return menuDinamic;
	}

	public void setMenuDinamic(List<HtmlDropDownMenu> menuDinamic) {
		this.menuDinamic = menuDinamic;
	}

	public String getName() {
		if (nombreUsuario == null && config != null)
			nombreUsuario = config.getUsuario();
		return nombreUsuario;
	}

	public void setName(String name) {
		this.nombreUsuario = name;
	}

	public String getPassword() {
		return contrasenia;
	}

	public void setPassword(String password) {
		this.contrasenia = password;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Método para iniciar sesión y cargar configuración a sesión
	 * 
	 * @author jorge.torner
	 * @since 04/01/2012
	 * @return
	 */
	public String login() {
		UtilsStub.Usuario usuario = null;
		mensajeError = "";
		try {
			UtilsStub stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.ValidarUsuario request = new UtilsStub.ValidarUsuario();

			// parámetros
			request.setTipo("LOGIN_ADMIN");
			request.setUsuario(nombreUsuario);
			request.setContrasenia(contrasenia);

			// invocamos
			UtilsStub.ValidarUsuarioResponse response = stub
					.validarUsuario(request);

			// obtenemos respuesta
			usuario = response.get_return();

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M4", true,
					"Error al validar el usuario", ex.getMessage(), 0)[0];
		}

		if (usuario != null) {
			// Cargamos datos de configuración
			Configuracion config = new Configuracion();
			config.setIdUsuario(usuario.getIdUsuario());
			config.setUsuario(usuario.getUsuario());
			config.CargarConfiguracion();
			// Cargamos el bean de configuracion en sesión
			subirSesionMenuUsuario(usuario.getIdUsuario());
			subirSesionAccionesModuloUsuario(usuario.getIdUsuario());
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("configuracion", config);
			return "exitoso";
		} else {
			mensajeError = "Usuario o contraseña inválidos";
			return "error";
		}
	}

	public static UtilsStub.Usuario validarUsuario(String p_tipo,
			String p_nombreUsuario, String p_contrasenia) {
		UtilsStub.Usuario usuario = null;
		String mensajeError = "";
		try {
			UtilsStub stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.ValidarUsuario request = new UtilsStub.ValidarUsuario();

			// parámetros
			request.setTipo(p_tipo);
			request.setUsuario(p_nombreUsuario);
			request.setContrasenia(p_contrasenia);

			// invocamos
			UtilsStub.ValidarUsuarioResponse response = stub
					.validarUsuario(request);

			// obtenemos respuesta
			usuario = response.get_return();

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M4", true,
					"Error al validar el usuario", ex.getMessage(), 0)[0];
		}

		return usuario;
	}

	/**
	 * Método para subir el menu del usuario a sesion
	 * 
	 * @author javier.gallegos
	 * @since 17/01/2012
	 * @return
	 */
	private void subirSesionMenuUsuario(int idUsuario) {
		// vamos a la base con el Id y nos traemos el menu del usuario y sus
		// permisos para subirlo
		List<HtmlDropDownMenu> menusDinamicos = null;
		List<MenuH> menusDinamicosS = null;
		try {
			MenuControllerStub menuStub = new MenuControllerStub();

			String url = Utils.modificarUrlServicioWeb(menuStub
					._getServiceClient().getOptions().getTo().getAddress());
			menuStub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			MenuControllerStub.ObtenerModulosMenuUsuario usuario = new MenuControllerStub.ObtenerModulosMenuUsuario();
			usuario.setIdUsuario(idUsuario);
			MenuControllerStub.ObtenerModulosMenuUsuarioResponse response = menuStub
					.obtenerModulosMenuUsuario(usuario);
			ModulosMenu[] modulos = response.get_return();
			menusDinamicos = generarMenuUsuario(modulos);
			menusDinamicosS = generarMenuUsuarioS(modulos);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("menuDina", menusDinamicos);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("menuDinaS", menusDinamicosS);
	}

	/**
	 * Método para subir las acciones por modulo del usuario a sesion
	 * 
	 * @author javier.gallegos
	 * @since 17/01/2012
	 * @return
	 */
	private void subirSesionAccionesModuloUsuario(int idUsuario) {
		// vamos a la base con el Id y nos traemos el menu del usuario y sus
		// permisos para subirlo
		List<HtmlDropDownMenu> menusDinamicos = null;
		try {
			MenuControllerStub menuStub = new MenuControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(menuStub
					._getServiceClient().getOptions().getTo().getAddress());
			menuStub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			MenuControllerStub.ObtenerAccionesModulosUsuario usuario = new MenuControllerStub.ObtenerAccionesModulosUsuario();
			usuario.setIdUsuario(idUsuario);
			MenuControllerStub.ObtenerAccionesModulosUsuarioResponse response = menuStub
					.obtenerAccionesModulosUsuario(usuario);
			AccionesModulos[] acciones = response.get_return();
			accionesMap = llenarMapaAcciones(acciones);

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("accionesM", accionesMap);

	}

	/**
	 * Método para terminar la sesión y redireccionar al login
	 * 
	 * @author jorge.torner
	 * @since 04/01/2012
	 * @return
	 */
	public String TerminarSesion() {
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().invalidate();
		String reqPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		try {
			// FacesContext.getCurrentInstance().getExternalContext().redirect(reqPath
			// + "/faces/Paginas/login.jsp");
			HttpServletResponse resp = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			resp.sendRedirect(reqPath + "/faces/Paginas/login.jsp");
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<HtmlDropDownMenu> generarMenuUsuario(ModulosMenu[] modulos) {
		int menusPosibles = 15;
		List<HtmlDropDownMenu> menusUsuario = new ArrayList<HtmlDropDownMenu>();
		String reqPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		if (modulos != null) {
			for (ModulosMenu modulo : modulos) {
				HtmlDropDownMenu menuT = new HtmlDropDownMenu();
				if (modulo.getIdPadre() == 0) {
					// menuT.setArchivo(modulo.getArchivo());
					// menuT.setIdModulo(modulo.getIdModulo());
					menuT.setValue(modulo.getNombreModulo());

					// menuT.setOrden(modulo.getOrden());

					// aqui agregamos los submodulos al menu
					for (ModulosMenu submod : modulos) {
						if (submod.getIdPadre() == modulo.getIdModulo()) {
							// si es igual es porque es su hijo
							HtmlMenuItem subMenu = new HtmlMenuItem();
							// HTMLLink link = new htmll
							HtmlOutputLink link = new HtmlOutputLink();
							link.setValue(reqPath + submod.getArchivo()
									+ "?pm=" + submod.getIdModulo());

							HtmlOutputText texto = new HtmlOutputText();
							texto.setValue("link");
							HtmlCommandButton but = new HtmlCommandButton();

							// subMenu.setArchivo(submod.getArchivo());
							// subMenu.setIdModulo(submod.getIdModulo());
							// subMenu.setValue(submod.getNombreModulo());
							// subMenu.setOnclick("document.location.href='"+submod.getArchivo()+"'");
							String action = "#{login.destroyBeans}";
							MethodExpression methodExpression = FacesContext
									.getCurrentInstance()
									.getApplication()
									.getExpressionFactory()
									.createMethodExpression(
											FacesContext.getCurrentInstance()
													.getELContext(), action,
											null, new Class<?>[0]);

							but.setActionExpression(methodExpression);
							but.setValue(submod.getNombreModulo());
							// but.getChildren().add(param);
							link.getChildren().add(texto);
							link.setId("b" + submod.getIdModulo());
							but.setOnmouseover("document.getElementById('j_id2:b"
									+ submod.getIdModulo() + "').click();");
							// but.getChildren().add(link);
							// but.setOnclick("parent.location='http://winfolinx.com'");
							// subMenu.setActionExpression(methodExpression);
							subMenu.getChildren().add(but);
							subMenu.getChildren().add(link);
							subMenu.setOnclick("Richfaces.showModalPanel('mp');");
							subMenu.setOncomplete("Richfaces.hideModalPanel('mp');");
							// subMenu.setOrden(submod.getOrden());
							menuT.getChildren().add(subMenu);
						}
					}
					menusUsuario.add(menuT);
				}

			}
		}
		// AGREGAMOS EL MENU DE TERMINAR SESION
		/*
		 * HtmlDropDownMenu menuCerrarseion= new HtmlDropDownMenu();
		 * menuCerrarseion.setValue("Sesion"); HtmlMenuItem menuCS = new
		 * HtmlMenuItem(); HtmlCommandLink linkC= new HtmlCommandLink(); String
		 * action = "#{login.TerminarSesion}"; MethodExpression methodExpression
		 * =
		 * FacesContext.getCurrentInstance().getApplication().getExpressionFactory
		 * ().
		 * createMethodExpression(FacesContext.getCurrentInstance().getELContext
		 * (), action, String.class, new Class<?>[0]);
		 * linkC.setActionExpression(methodExpression); HtmlOutputText texto =
		 * new HtmlOutputText(); texto.setValue("Cerrar");
		 * linkC.getChildren().add(texto); menuCS.getChildren().add(linkC);
		 * menusUsuario.add(menuCerrarseion);
		 */
		// se agregan los que no tiene para mandarlo completo con la opcion de
		// render = false
		if (menusUsuario.size() < menusPosibles) {
			for (int i = menusUsuario.size(); i < menusPosibles; i++) {
				HtmlDropDownMenu menuF = new HtmlDropDownMenu();
				menuF.setRendered(false);
				menusUsuario.add(menuF);
			}
		}

		return menusUsuario;
	}

	private List<MenuH> generarMenuUsuarioS(ModulosMenu[] modulos) {

		int subMenusPermitidos = 5;
		int menusPermitidos = 7;
		List<MenuH> menusUsuario = new ArrayList<MenuH>();
		String reqPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		if (modulos != null) {
			for (ModulosMenu modulo : modulos) {
				MenuH menuT = new MenuH();
				if (modulo.getIdPadre() == 0) {
					menuT.setNombreMenu(modulo.getNombreModulo());
					menuT.setMostrar(true);
					List<ItemMenu> submenus = new ArrayList<ItemMenu>();
					for (ModulosMenu submod : modulos) {
						if (submod.getIdPadre() == modulo.getIdModulo()) {
							// si es igual es porque es su hijo
							ItemMenu subMenu = new ItemMenu();
							subMenu.setLinkMenu(reqPath + submod.getArchivo()
									+ "?pm=" + submod.getIdModulo());
							subMenu.setMetodoMenu("#{login.destroyBeans}");
							subMenu.setNombreMenu(submod.getNombreModulo());
							subMenu.setMostrar(true);
							submenus.add(subMenu);
						}

						menuT.setSubmenus(submenus);
					}
					// aqui se tienen que contar los submenus para poner 5
					// por menu en caso de que haya
					int cant = menuT.getSubmenus().size();
					List<ItemMenu> listaNueva = new ArrayList<ItemMenu>();
					for (ItemMenu mn : menuT.getSubmenus()) {
						listaNueva.add(mn);
					}
					if (cant < subMenusPermitidos) {
						for (int i = cant; i <= subMenusPermitidos; i++) {
							ItemMenu subF = new ItemMenu();
							subF.setMostrar(false);
							listaNueva.add(subF);
						}
					}
					menuT.setSubmenus(listaNueva);
					menusUsuario.add(menuT);
				}

			}
		}
		if (menusUsuario.size() < menusPermitidos) {
			for (int i = menusUsuario.size(); i <= menusPermitidos; i++) {
				MenuH menuF = new MenuH();
				menuF.setMostrar(false);
				menusUsuario.add(menuF);
			}
		}
		return menusUsuario;
	}

	private HashMap<Integer, HashMap<String, Boolean>> llenarMapaAcciones(
			AccionesModulos[] acciones) {
		HashMap<Integer, HashMap<String, Boolean>> Modulos = new HashMap<Integer, HashMap<String, Boolean>>();
		int idModulo = 0;
		HashMap<String, Boolean> accionesMap = new HashMap<String, Boolean>();
		if (acciones != null) {
			for (AccionesModulos accion : acciones) {

				if (idModulo != accion.getIdModulo()) {

					if (idModulo != 0) {
						Modulos.put(new Integer(idModulo), accionesMap);
						accionesMap = new HashMap<String, Boolean>();
					}
					accionesMap.put(accion.getNombreAccion(), true);
					idModulo = accion.getIdModulo();
				} else {
					accionesMap.put(accion.getNombreAccion(), true);
				}
			}
		}
		Modulos.put(idModulo, accionesMap);
		return Modulos;

	}

	public String getVersionSistema() {
		versionSistema = Utils.obtenerPropiedadArchivoConfig("VersionSistema");
		return versionSistema;
	}

	public void setVersionSistema(String versionSistema) {
		this.versionSistema = versionSistema;
	}

	public String getFechaCompilacion() {
		// Obtiene la fecha de compilación (última actualización) del archivo
		// avon_ots_admin.war
		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());
		String rutaSist = context.getRealPath("");
		rutaSist = rutaSist.substring(0, rutaSist.lastIndexOf("\\"));
		java.io.File archivoWar = new java.io.File(rutaSist
				+ "\\avon_ots_admin.war");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (archivoWar.exists())
			fechaCompilacion = sdf.format(archivoWar.lastModified());
		else
			fechaCompilacion = "No se encuentra archivo: avon_ots_admin.war";
		return fechaCompilacion;
	}

	public void setFechaCompilacion(String fechaCompilacion) {
		this.fechaCompilacion = fechaCompilacion;
	}

	public String destroyBeans() {
		Map<String, Object> mapa = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		mapa.remove("report");
		mapa.remove("ruta");
		mapa.remove("mto");
		mapa.remove("modelo");
		mapa.remove("cu002RecAjustes");
		mapa.remove("cu002LiqSubBodega");
		mapa.remove("recepcion");
		mapa.remove("zona");
		mapa.remove("campania");
		mapa.remove("cierreZona");
		mapa.remove("preenrutado");
		mapa.remove("entregaVentanilla");
		mapa.remove("entregaVentanillaMasiva");
		mapa.remove("liquidacionVentanillaMasiva");
		mapa.remove("catAvon");
		mapa.remove("secuenciaEntregaEnRuta");
		mapa.remove("liquidacionRepartoWeb");
		mapa.remove("entregaFaltantes");
		mapa.remove("remitosVentanilla");
		mapa.remove("armRE");

		if (paginaH != null) {

			try {

				HttpServletResponse resp = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext()
						.getResponse();
				resp.sendRedirect(paginaH);
				FacesContext.getCurrentInstance().responseComplete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "";
	}
}
