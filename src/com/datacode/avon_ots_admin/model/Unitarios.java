/**
 * Mapeo de las propiedades del Objeto de la BD - // VIEW_UNITARIOS //
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @since 18/01/2012
 * @category Map
 */
public class Unitarios {
	//Atributos del Objeto
	private Integer idUnitario = 0;
	private String  clave = "",  		//TIPO-expr	?
					descripcion = "", 	//Descripcion
					zona = "",
					codigo = "",		//Codigo Barras
					cantidad = "",	  	
					orden = "",  		//Clave De Orden
					noCaja = "",	registro = "",		nombre = "",
					direccion = "", ruta ="",			
					campania = "", 		//Campania
					anio = "",			//AnioCampania
					fechaActualizado = "", usuarioActualiza = "";
	
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @param idUnitario
	 * @param clave
	 * @param descripcion
	 * @param zona
	 * @param codigo
	 * @param cantidad
	 * @param orden
	 * @param noCaja
	 * @param registro
	 * @param nombre
	 * @param direccion
	 * @param ruta
	 * @param campania
	 * @param anio
	 */
	public Unitarios(Integer idUnitario, String clave, String descripcion, String zona,
			String codigo, String cantidad, String orden, String noCaja, String registro,
			String nombre, String direccion, String ruta, String campania, String anio,
			String fechaActualizado, String usuarioActualiza) {
		this.idUnitario = idUnitario;	this.clave = clave;		this.descripcion = descripcion;
		this.zona = zona;				this.codigo = codigo;	this.cantidad = cantidad;
		this.orden = orden;				this.noCaja = noCaja;	this.registro = registro;
		this.nombre = nombre;			this.direccion = direccion;	this.ruta = ruta;
		this.campania = campania;		this.anio = anio;
		this.fechaActualizado = fechaActualizado;	this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the idUnitario
	 */
	public Integer getIdUnitario() {
		return idUnitario;
	}

	/**
	 * @param idUnitario the idUnitario to set
	 */
	public void setIdUnitario(Integer idUnitario) {
		this.idUnitario = idUnitario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * @return the noCaja
	 */
	public String getNoCaja() {
		return noCaja;
	}

	/**
	 * @param noCaja the noCaja to set
	 */
	public void setNoCaja(String noCaja) {
		this.noCaja = noCaja;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @return the campania
	 */
	public String getCampania() {
		return campania;
	}

	/**
	 * @param campania the campania to set
	 */
	public void setCampania(String campania) {
		this.campania = campania;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}

	/**
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}

	/**
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	/**
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}

}
