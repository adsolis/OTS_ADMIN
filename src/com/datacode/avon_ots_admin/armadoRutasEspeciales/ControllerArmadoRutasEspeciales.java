package com.datacode.avon_ots_admin.armadoRutasEspeciales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ModelRemito;
import com.datacode.avon_ots_admin.model.RutaEspecial;
import com.datacode.avon_ots_admin.reports.model.RutaEspecialItems;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.Campania;
import com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ModelRutasEspecialesItems;

public class ControllerArmadoRutasEspeciales {

	private String mensajeError;
	Configuracion configuracion;
	private String idCampania;
	private String rutaEspecialB;
	private String rutaespecialN;
	private String campaniaMaxima;
	private List<SelectItem> campanias;
	private List<RutaEspecial> registros;
	private List<ModelRemito> remitos;
	private List<RutaEspecial> ordenes;
	private List<RutaEspecialItems> cajas;
	private List<RutaEspecialItems> unitarios;
	private Boolean mostrarPanelNueva = false;
	private Boolean mostrarPanelConsulta = false;
	private String registroRep;
	private String codigoBarras;
	private String idRutaEspecial;
	private String rutaespecialNID;
	private String codigoUnitario;
	private String valorNR;
	Utils utils;
	private List<RutaEspecial> rutas;
	private Boolean mostrarPanelNuevaDeshabilitado = false;

	public String getValorNR() {
		return valorNR;
	}

	public void setValorNR(String valorNR) {
		this.valorNR = valorNR;
	}

	public Boolean getMostrarPanelNuevaDeshabilitado() {
		return mostrarPanelNuevaDeshabilitado;
	}

	public void setMostrarPanelNuevaDeshabilitado(
			Boolean mostrarPanelNuevaDeshabilitado) {
		this.mostrarPanelNuevaDeshabilitado = mostrarPanelNuevaDeshabilitado;
	}

	public String getCodigoUnitario() {
		return codigoUnitario;
	}

	public void setCodigoUnitario(String codigoUnitario) {
		this.codigoUnitario = codigoUnitario;
	}

	public List<RutaEspecial> getRutas() {
		return rutas;
	}

	public void setRutas(List<RutaEspecial> rutas) {
		this.rutas = rutas;
	}

	public Boolean getMostrarPanelConsulta() {
		return mostrarPanelConsulta;
	}

	public void setMostrarPanelConsulta(Boolean mostrarPanelConsulta) {
		this.mostrarPanelConsulta = mostrarPanelConsulta;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public List<RutaEspecialItems> getUnitarios() {
		return unitarios;
	}

	public void setUnitarios(List<RutaEspecialItems> unitarios) {
		this.unitarios = unitarios;
	}

	public String getRegistroRep() {
		return registroRep;
	}

	public void setRegistroRep(String registroRep) {
		this.registroRep = registroRep;
	}

	public Boolean getMostrarPanelNueva() {
		return mostrarPanelNueva;
	}

	public void setMostrarPanelNueva(Boolean mostrarPanelNueva) {
		this.mostrarPanelNueva = mostrarPanelNueva;
	}

	public String getRutaespecialN() {
		return rutaespecialN;
	}

	public void setRutaespecialN(String rutaespecialN) {
		this.rutaespecialN = rutaespecialN;
	}

	public String getCampaniaMaxima() {
		return campaniaMaxima;
	}

	public void setCampaniaMaxima(String campaniaMaxima) {
		this.campaniaMaxima = campaniaMaxima;
	}

	public List<RutaEspecial> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RutaEspecial> registros) {
		this.registros = registros;
	}

	public List<RutaEspecialItems> getCajas() {
		return cajas;
	}

	public void setCajas(List<RutaEspecialItems> cajas) {
		this.cajas = cajas;
	}

	public List<SelectItem> getCampanias() {

		if (campanias == null) {

			List<SelectItem> list = new ArrayList<SelectItem>();
			ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
			// Tipo CU --se agrega y modifica en stored procedure
			Campania[] camps = dat.consultaCampanias("",
					configuracion.getIdUsuario());
			list.add(new SelectItem("0", "Selecciona una opción"));

			try {
				if (camps != null) {
					for (int o = 0; o < camps.length; o++) {
						list.add(new SelectItem("" + camps[o].getDescZona(), ""
								+ camps[o].getDescZona()));
					}
				}
			} catch (NullPointerException e) {
				mensajeError = Utils.ObtenerMensajeBD("General Admin", "M1",
						true, "No se pudieron cargar las Campañas",
						e.getMessage(), configuracion.getIdUsuario())[0];
			}
			campanias = list;

		}

		return campanias;
	}

	public void setCampanias(List<SelectItem> campanias) {
		this.campanias = campanias;
	}

	public String getRutaEspecialB() {
		return rutaEspecialB;
	}

	public void setRutaEspecialB(String rutaEspecialB) {
		this.rutaEspecialB = rutaEspecialB;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	public ControllerArmadoRutasEspeciales() {
		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");

		utils = new Utils();
	}

	public void buscar() {
		mensajeError = "";
		mostrarPanelConsulta = true;
		mostrarPanelNueva = false;
		mostrarPanelNuevaDeshabilitado = true;
		ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
		if (rutaEspecialB == null) {
			rutaEspecialB = "";
		}
		String campaniaS = "";
		if (idCampania == null || "0".equals(idCampania)) {
			campaniaS = "";
		} else {
			for (SelectItem c : campanias) {
				if (c.getValue().equals("" + idCampania)) {
					campaniaS = c.getLabel();
				}
			}
		}
		rutas = dat.consultarRutasEspeciales(configuracion.getIdUsuario(),
				rutaEspecialB, campaniaS);

	}

	public void consultarRuta(String idRutaEspecial) {
		mensajeError = "";
		ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();

		if (idRutaEspecial != null && idRutaEspecial.length() > 0) {
			ModelRutasEspecialesItems[] datos = dat
					.consultarRutasEspecialesDetalleCajas(
							configuracion.getIdUsuario(),
							Integer.parseInt(idRutaEspecial));
			registros = new ArrayList<RutaEspecial>();
			cajas = new ArrayList<RutaEspecialItems>();
			unitarios = new ArrayList<RutaEspecialItems>();

			rutaespecialN = "";
			for (RutaEspecial r : rutas) {
				if (r.getIdRutaEspecial() == Integer.parseInt(idRutaEspecial)) {
					rutaespecialN = r.getClaveOrden();
				}
			}

			if (datos != null && datos.length > 0) {
				mostrarPanelConsulta = false;
				mostrarPanelNueva = true;
				mostrarPanelNuevaDeshabilitado = true;
				String registro = "0";
				for (ModelRutasEspecialesItems r : datos) {
					RutaEspecial reg = new RutaEspecial();
					if ("0".equals(registro)) {
						// es la primera vez
						// agregamos el registro
						reg.setRegistro(r.getRegistro());
						registro = r.getRegistro();
						reg.setNombre(r.getNombre());
						reg.setBlocked(r.getTipo());
						reg.setClaveOrden(r.getClaveOrden());
						registros.add(reg);

					} else {
						// verificamos si es el mismo
						if (!registro.equals(r.getRegistro())) {
							// si no es el mismo
							reg.setRegistro(r.getRegistro());
							reg.setBlocked(r.getTipo());
							registro = r.getRegistro();
							reg.setNombre(r.getNombre());
							reg.setClaveOrden(r.getClaveOrden());
							registros.add(reg);
						}
					}
					RutaEspecialItems i = new RutaEspecialItems();
					if (r.getIdTipoOrigen() == 1) {
						// es caja
						i.setCodigoBarras(r.getCodigoBarras());
						i.setDescripcion(r.getDescripcion());
						i.setRegistro(r.getRegistro());
						i.setClaveOrden(r.getClaveOrden());
						i.setMarcado(true);
						cajas.add(i);
					}

				}

			}

			ModelRutasEspecialesItems[] datosUnitarios = dat
					.consultarRutasEspecialesDetalleUnitarios(
							configuracion.getIdUsuario(),
							Integer.parseInt(idRutaEspecial));

			if (datosUnitarios != null && datosUnitarios.length > 0) {
				for (ModelRutasEspecialesItems r : datosUnitarios) {
					RutaEspecialItems l = new RutaEspecialItems();
					l.setCodigoFSC(r.getCodigo_FSC());
					l.setCodigoEAN13(r.getCodigo_FSC_EAN13());
					l.setDescripcion(r.getDescripcion());
					l.setCantidadSolicitada(r.getCantidadFacturada());
					l.setCantidadIngresada(r.getCantidadAsignada());
					l.setRegistro(r.getRegistro());
					l.setClaveOrden(r.getClaveOrden());
					unitarios.add(l);
				}
			}
		}
	}

	public void eliminarRutaEspecial(String idRutaEspecial) {
		mensajeError = "";
		ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();

		if (idRutaEspecial != null && idRutaEspecial.length() > 0) {

			String res = dat.eliminarRuta(configuracion.getIdUsuario(),
					Integer.parseInt(idRutaEspecial));

			String rutaEspecial = "";
			for (RutaEspecial l : rutas) {
				if (l.getIdRutaEspecial() == Integer.parseInt(idRutaEspecial)) {
					rutaEspecial = l.getClaveOrden();
				}
			}

			if ("RUTA ESPECIAL CON ITEMS EN ESTATUS NO ADECUADO".equals(res)) {
				mensajeError = "La Ruta Especial: " + rutaEspecial
						+ " ya tiene ítems Acusados, no puede ser eliminada";
			} else if ("RUTA ELIMINADA EXITOSAMENTE".equals(res)) {
				mensajeError = "La Ruta Especial: " + rutaEspecial
						+ " ha sido eliminada.";
				String campaniaS = "";
				if (idCampania == null || "0".equals(idCampania)) {
					campaniaS = "";
				} else {
					for (SelectItem c : campanias) {
						if (c.getValue().equals("" + idCampania)) {
							campaniaS = c.getLabel();
						}
					}
				}
				rutas = dat.consultarRutasEspeciales(
						configuracion.getIdUsuario(), rutaEspecialB, campaniaS);
			} else {
				System.out.println("ocurrio un problema:" + res);
			}
		}
	}

	private void limpiarArreglos() {
		cajas = new ArrayList<RutaEspecialItems>();
		ordenes = new ArrayList<RutaEspecial>();
		registros = new ArrayList<RutaEspecial>();
		unitarios = new ArrayList<RutaEspecialItems>();
	}

	public void nueva() {
		mensajeError = "";
		limpiarArreglos();
		mostrarPanelConsulta = false;
		mostrarPanelNueva = true;
		mostrarPanelNuevaDeshabilitado = false;
		registroRep = "";
		codigoBarras = "";
		codigoUnitario = "";
		// cuando entran aqui se tiene que generar el folio y concatenarselo a
		// la campania maxima
		ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
		RutaEspecial folio = dat.generarFolio("", 0,
				configuracion.getIdUsuario());
		idRutaEspecial = "" + folio.getIdRutaEspecial();
		rutaespecialNID = folio.getClaveRutaEspecial() + idRutaEspecial;
		if (rutaespecialNID.length() > 0) {
			rutaespecialN = rutaespecialNID.substring(0,
					rutaespecialNID.length() - idRutaEspecial.length());
		}
		campaniaMaxima = campanias.get(1).getLabel();
	}

	public void validaNR() {
		if (registros == null) {
			registros = new ArrayList<RutaEspecial>();
		}
		boolean consultarItems = true;
		if (registros.size() > 0) {
			// se tiene que validar si no esta ya dentro
			for (RutaEspecial r : registros) {
				if (registroRep.equals(r.getRegistro())) {
					consultarItems = false;
					mensajeError = "";
				}
			}
		}
		if (consultarItems) {
			ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
			List<RutaEspecial> resReg = dat.obtieneRegistros(
					configuracion.getIdUsuario(), registroRep);
			if (resReg != null && resReg.size() > 0) {
				if ("N".equals(resReg.get(0).getBlocked())) {
					valorNR = "EVALUAR";
				} else {
					valorNR = "NO EVALUAR";
				}
			}

		} else {
			// ya estaba dentro ese registro y nos quieren chamaquear
			mensajeError = "El registro " + registroRep + " ya fue leído";
		}
	}

	public void consultarRegistro() {
		mensajeError = "";
		boolean consultarItems = true;
		mensajeError = "";
		if (cajas == null) {
			cajas = new ArrayList<RutaEspecialItems>();
		}
		if (unitarios == null) {
			unitarios = new ArrayList<RutaEspecialItems>();
		}
		if (registros == null) {
			registros = new ArrayList<RutaEspecial>();
		}
		if (ordenes == null) {
			ordenes = new ArrayList<RutaEspecial>();
		}
		if(remitos == null) {
			remitos = new ArrayList<ModelRemito>();
		}
		if (registros.size() > 0) {
			// se tiene que validar si no esta ya dentro
			for (RutaEspecial r : registros) {
				if (registroRep.equals(r.getRegistro())) {
					consultarItems = false;
					mensajeError = "";
				}
			}
		}

		if (consultarItems) {
			// aqui vamos y consultamos el registro y nos regresa la litsta de
			// ordenes que debemos de tomar
			if (!"".equals(valorNR)) {
				if ("MANDAR".equals(valorNR)) {

					ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
					List<RutaEspecial> resReg = dat.obtieneRegistros(
							configuracion.getIdUsuario(), registroRep);

					if (resReg != null && resReg.size() > 0) {
						// quiere decir que si se encontraron ordenes para ese
						// registro

						List<RutaEspecial> agregar = new ArrayList<RutaEspecial>();
						for (RutaEspecial r : resReg) {
							boolean insertar = true;
							for (RutaEspecial a : agregar) {
								if (a.getRegistro().equals(r.getRegistro())) {
									insertar = false;
								}
							}
							if (insertar) {
								// lo agregamos a agregar
								agregar.add(r);
							}
						}

						if (agregar.size() > 0) {

							ordenes.addAll(resReg);
							registros.addAll(agregar);
							/*try {
								remitos = dat.consultaRemitos(agregar.get(0).getClaveOrden(), 
										configuracion.getIdUsuario(), registroRep);
							} catch (AxisFault e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/

							// de cada registro de agregar consultamos sus items
							// y
							// cajas
							StringBuilder r = new StringBuilder();
							int cont = 0;
							for (RutaEspecial l : agregar) {
								if (cont == 0) {
									r.append(l.getRegistro());
								} else {
									r.append(",");
									r.append(l.getRegistro());
								}
								cont++;
							}

							List<RutaEspecialItems> itemsCajas = dat
									.obtieneItems(configuracion.getIdUsuario(),
											r.toString(), 1);

							if (itemsCajas != null && itemsCajas.size() > 0) {
								for (RutaEspecialItems i : itemsCajas) {
									i.setRegistro(registroRep);
								}
								cajas.addAll(itemsCajas);
							}

							List<RutaEspecialItems> itemsUnitarios = dat
									.obtieneItems(configuracion.getIdUsuario(),
											r.toString(), 2);

							if (itemsUnitarios != null
									&& itemsUnitarios.size() > 0) {

								List<RutaEspecialItems> aBorrar = new ArrayList<RutaEspecialItems>();
								// aqui hay que recorrer los nuevos que se traen
								for (RutaEspecialItems i : itemsUnitarios) {

									i.setRegistro(registroRep);
									// por cada nuevo verificamos si ya esta en
									// los
									// anteriores y si es el mismo sumamos las
									// facturadas
									for (RutaEspecialItems u : unitarios) {
										if (i.getCodigoFSC().equals(
												u.getCodigoFSC())) {
											// esto quiere decir que tenemos que
											// agruparlos y borrarlo de
											// itemsUnitarios
											aBorrar.add(i);
											u.setCantidadSolicitada(u
													.getCantidadSolicitada()
													+ i.getCantidadSolicitada());
										}
									}
								}
								if (aBorrar.size() > 0) {
									itemsUnitarios.removeAll(aBorrar);
								}
								unitarios.addAll(itemsUnitarios);
							}
							registroRep = "";

						}

					} else {
						// no hay ordenes validas el registro no es valido,
						// checar
						// que
						// mensaje mandar
						mensajeError = "El registro "
								+ registroRep
								+ " no tiene un pedido disponible para ingresar en Reparto Especial";
					}
				}
			} else {

				mensajeError = "El registro "
						+ registroRep
						+ " no tiene un pedido disponible para ingresar en Reparto Especial";

			}

		} else {
			// ya estaba dentro ese registro y nos quieren chamaquear
			mensajeError = "El registro " + registroRep + " ya fue leído";
		}

	}

	public void escanearCaja() {
		mensajeError = "";
		// aqui se entra para marcar la caja si existe
		if (cajas != null && cajas.size() > 0) {

			if (codigoBarras.trim().length() > 0) {
				boolean seEncuentra = false;
				for (RutaEspecialItems l : cajas) {
					if (l.getCodigoBarras().equals(codigoBarras)) {
						if (!l.isMarcado()) {
							l.setMarcado(true);
						} else {
							mensajeError = "El código " + codigoBarras
									+ " ya fue validado";
						}
						seEncuentra = true;
					}
				}
				if (!seEncuentra) {
					mensajeError = "El código de caja "
							+ codigoBarras
							+ " no se encuentra en la lista de cajas relacionadas a los CODs ingresados";
				}
			} else {
				mensajeError = "El valor es requerido para escanear una caja";
			}
		} else {
			mensajeError = "No hay cajas que escanear, por favor verifique";
		}
	}

	/**
	 * metodo que se utiliza cuando el usuario escanea un unitario
	 */
	public void escanearUnitario() {
		mensajeError = "";
		// aqui se entra para marcar la caja si existe
		if (unitarios != null && unitarios.size() > 0) {

			if (codigoUnitario.trim().length() > 0) {
				boolean seEncuentra = false;
				for (RutaEspecialItems l : unitarios) {
					if (codigoUnitario.trim().equals(l.getCodigoEAN13())
							|| codigoUnitario.trim().equals(l.getCodigoFSC())) {
						/*
						 * si entra aqui es porque se le tiene que sumar
						 */
						l.setCantidadIngresada(l.getCantidadIngresada() + 1);
						seEncuentra = true;
						mensajeError = validaListaUnitarios();
					}
				}
				if (!seEncuentra) {
					mensajeError = "El código de unitario/premio "
							+ codigoUnitario
							+ " no se encuentra en la lista de unitarios/"
							+ "premios relacionados a los CODs ingresados";
				}
			} else {
				mensajeError = "El código de unitario/premio es requerido para validar";
			}
		} else {
			mensajeError = "No hay unitarios que validar, por favor verifique";
		}
	}

	private String validaListaUnitarios() {
		String mensaje = "";
		if (unitarios != null && unitarios.size() > 0) {
			for (RutaEspecialItems i : unitarios) {
				if (i.getCantidadSolicitada() < i.getCantidadIngresada()) {
					// regresamo al valor de solicitada
					// mandamos mensaje
					i.setCantidadIngresada(i.getCantidadSolicitada());
					mensaje = "La cantidad capturada no puede ser mayor a la cantidad facturada";
				}
			}
		}
		return mensaje;
	}

	/**
	 * metodo para guardar la ruta armada
	 */
	public void guardarRuta() {
		mensajeError = "";
		ConsultaDatosRutasEspeciales dat = new ConsultaDatosRutasEspeciales();
		if (!"0".equals(idCampania)) {
			if (ordenes != null && cajas != null && cajas.size() > 0
					&& ordenes.size() > 0) {

				boolean cajasCorrectas = true;
				for (RutaEspecialItems c : cajas) {
					if (!c.isMarcado()) {
						cajasCorrectas = false;
					}
				}

				if (cajasCorrectas) {

					boolean unitariosCorrectos = true;

					for (RutaEspecialItems c : unitarios) {
						if (c.getCantidadIngresada() <= 0) {
							unitariosCorrectos = false;
						}
					}
					if (unitariosCorrectos) {

						StringBuffer ordenesComa = new StringBuffer();
						StringBuffer cajasComa = new StringBuffer();
						int cont = 0;
						for (RutaEspecial r : ordenes) {
							if (cont == 0) {
								ordenesComa.append(r.getIdOrden());
							} else {
								ordenesComa.append(",");
								ordenesComa.append(r.getIdOrden());
							}
							cont++;
						}
						cont = 0;
						for (RutaEspecialItems r : cajas) {
							if (cont == 0) {
								cajasComa.append(r.getIdItem());
							} else {
								cajasComa.append(",");
								cajasComa.append(r.getIdItem());
							}
							cont++;
						}
						String idCampaniaS = "";

						for (SelectItem c : campanias) {
							if (c.getValue().equals("" + idCampania)) {
								idCampaniaS = c.getLabel();
							}
						}
						Boolean exito = dat.guardarRuta(
								configuracion.getIdUsuario(),
								Integer.parseInt(idRutaEspecial),
								rutaespecialNID, idCampaniaS,
								ordenesComa.toString(), cajasComa.toString());

						if (exito) {
							if (unitarios != null && unitarios.size() > 0) {
								for (RutaEspecialItems l : unitarios) {
									if (exito) {
										exito = dat
												.guardarRutaUnitarios(
														configuracion
																.getIdUsuario(),
														Integer.parseInt(idRutaEspecial),
														l.getCodigoFSC(),
														l.getCantidadSolicitada(),
														l.getCodigoEAN13());
									}
								}
							}
							if (exito) {
								dejarEnBlanco();
								mensajeError = "¡Reparto Especial Creado!";
							} else {
								mensajeError = "Ocurrió un error al guardar"
										+ " los unitarios, por favor verifique";
							}
						} else {
							mensajeError = "Ocurrió un error al guardar"
									+ " la ruta, por favor verifique";
						}
					} else {
						mensajeError = "Para continuar debe validar todos los unitarios";
					}
				} else {
					mensajeError = "Para continuar debe validar todas las cajas";
				}

			} else {
				mensajeError = "Debe agregar cuando menos un registro válido"
						+ " para poder guardar el Reparto Especial";
			}
		} else {
			mensajeError = "Seleccione una campaña por favor";
		}
	}

	public void ingresarManual() {
		mensajeError = "";
		mensajeError = validaListaUnitarios();
	}

	public void regresaAPrincipal() {
		Map<String, Object> mapa = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();

		mapa.remove("armRE");

		mensajeError = "¡Ruta Especial Creada!";
	}

	public void dejarEnBlanco() {
		mostrarPanelConsulta = false;
		mostrarPanelNueva = false;
		mostrarPanelNuevaDeshabilitado = false;

		unitarios = new ArrayList<RutaEspecialItems>();
		cajas = new ArrayList<RutaEspecialItems>();
		rutas = new ArrayList<RutaEspecial>();
		registroRep = "";
		rutaespecialN = "";
		campaniaMaxima = "";
		codigoBarras = "";
		codigoUnitario = "";

	}

	public List<ModelRemito> getRemitos() {
		return remitos;
	}

	public void setRemitos(List<ModelRemito> remitos) {
		this.remitos = remitos;
	}
}
