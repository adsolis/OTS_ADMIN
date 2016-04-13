/**
 * Funciones utilizadas en las pantalla de Catalogos
 */
/**
 * Validaciones de Formulario
 * http://www.forosdelweb.com/f13/validar-rfc-162462/
 * http://javerosanonimos.blogspot.com/2009/02/expresiones-regulares.html
 * http://www.programacionweb.net/foros/mensaje/?num=10860
 * 
 */

/**
 * Solo acepta numeros y punto
 * Se ejecuta en el evento onkeyPress
 **/
function numerosFlotantes() {
	var key = window.event.keyCode;
	if ((key < 48 || key > 57) && (key < 46 || key > 46)) {
		window.event.keyCode = 0;
	}
}

function confirmaEliminar() {
	return confirm('¿Estás seguro de eliminar este registro?');
}

/**
 * función para imprimir pantalla
 */
function imprimir() {
	window.print();
}

function permite(elEvento, permitidos) {
	// Variables que definen los caracteres permitidos
	var numeros = "0123456789";
	var punto = ".";
	var dospuntos = ":";
	var caracteres = " abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	var numeros_caracteres = numeros + caracteres;
	var numero_punto = numeros + punto;
	var numero_dospunto = numeros + dospuntos;
	var teclas_especiales = [ 8 ];//[8, 37, 39, 46];
	// 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha

	// Seleccionar los caracteres a partir del parámetro de la función
	switch (permitidos) {
	case 'num':
		permitidos = numeros;
		break;
	case 'car':
		permitidos = caracteres;
		break;
	case 'num_car':
		permitidos = numeros_caracteres;
		break;
	case 'num_pto':
		permitidos = numero_punto;
		break;
	case 'num2pto':
		permitidos = numero_dospunto;
		break;
	}

	// Obtener la tecla pulsada 
	var evento = elEvento || window.event;
	var codigoCaracter = evento.charCode || evento.keyCode;
	var caracter = String.fromCharCode(codigoCaracter);

	// Comprobar si la tecla pulsada es alguna de las teclas especiales
	// (teclas de borrado y flechas horizontales)
	var tecla_especial = false;
	for ( var i in teclas_especiales) {
		if (codigoCaracter == teclas_especiales[i]) {
			tecla_especial = true;
			break;
		}
	}

	// Comprobar si la tecla pulsada se encuentra en los caracteres permitidos
	// o si es una tecla especial
	return permitidos.indexOf(caracter) != -1 || tecla_especial;
}

/* Valida mascara y valores permitidos en tiempo*/
function validaTiempo(valor) {
	var tiempo = valor;
	var pts1 = tiempo.substring(2, 3);
	var pts2 = tiempo.substring(5, 6);
	var hr = tiempo.substring(0, 2);
	var mm = tiempo.substring(3, 5);
	var ss = tiempo.substring(6, 8);

	if (tiempo.length > 8) {
		alert("Excede el valor permitido. El formato corresto es HH:MM:SS.");
		return false;
	} else {
		if (isNaN(hr) || isNaN(mm) || isNaN(ss)) {
			alert("El número de horas, minutos o segundos deben ser numéricos.");
			return false;
		}
		if (pts1 != ":" || pts2 != ":") {
			alert("El formato corresto es HH:MM:SS");
			return false;
		}
		if (hr > 99 || mm > 59 || ss > 59) {
			alert("Se debe ingresar horas, minutos o segundos correctas. El rango máximo son 99 horas - 59 minutos - 59 segundos.");
			return false;
		}
	}
}

/*  ******************************************************************************************************************************/
/* ********************************Validaciones de Mantenimiento de Elemento - Inicio ***************************************** */
/*  ******************************************************************************************************************************/

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};

function onchangeEntregandoC1() {
	if (document.getElementById("frmGeneral:cbEntregandoC1").checked == true) {
		document.getElementById("frmGeneral:txtKilometrajeC1").disabled = "disabled";
	} else {
		document.getElementById("frmGeneral:txtKilometrajeC1").disabled = "";
	}
}

/** Validar Campos Requeridos y mascaras de Catalog 1 - Unidades de Reparto */
function requeridosC1() {
	var pai = document.getElementById("frmGeneral:cmbxPaisC1").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC1").value;
	var mar = document.getElementById("frmGeneral:cmbxMarcaC1").value;
	var anio = document.getElementById("frmGeneral:txtAnioC1").value;
	var ser = document.getElementById("frmGeneral:txtNoSerieC1").value;
	var eco = document.getElementById("frmGeneral:txtNoEconC1").value;
	var pla = document.getElementById("frmGeneral:txtPlacasC1").value;
	var col = document.getElementById("frmGeneral:txtColorC1").value;
	var cap = document.getElementById("frmGeneral:txtCapacidadC1").value;
	var ren = document.getElementById("frmGeneral:txtRendimientoC1").value;
	var cod = document.getElementById("frmGeneral:txtCodBarrasC1").value;
	if (pai == 0 || ldc == 0 || mar == "0" || ser.trim().length == 0
			|| eco.trim().length == 0 || pla.trim().length == 0
			|| col.trim().length == 0 || cap.trim().length == 0
			|| ren.trim().length == 0 || cod.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
	if (anio.length > 4 || anio.length == 0) {
		alert("Ingresa un Año valido. Longitud máxima de 4 digitos.");
		return false;
	}
	if (col.length > 50 || col.length < 1) {
		alert("La descripción del Color excede la longitud máxima de 50 caracteres.");
		return false;
	}
	if (ser.length > 100 || eco.length > 100 || pla.length > 100
			|| ren.length > 100 || cod.length > 100 || cap.length > 100) {
		alert("Los campos no deben exceder la longitud máxima de 100 caracteres.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 2 - Modelo */
function requeridosC2() {
	var mod = document.getElementById("frmGeneral:txtDescC2").value;
	var mar = document.getElementById("frmGeneral:cmbxMarcaC2").value;
	var fre = document.getElementById("frmGeneral:txtFrecuenciaC2").value;

	if (mod.trim().length == 0 || mar == 0 || fre.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
	if (mod.length > 100 || fre.length > 100) {
		alert("Los campos no deben exceder la longitud máxima de 100 caracteres.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 3 - Marca */
function requeridosC3() {
	var des = document.getElementById("frmGeneral:txtDescC3").value;
	if (des.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 4 - Asignacion de Unidad de Reparto */
function requeridosC4() {
	var fas = document.getElementById("frmGeneral:txtFechaAsignadaC4").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC4").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC4").value;
	var uni = document.getElementById("frmGeneral:cmbxUniRepartoC4").value;
	var emp = document.getElementById("frmGeneral:cmbxEmpleadoC4").value;
	var edo = document.getElementById("frmGeneral:cmbxEstatusC4").value;
	var f1 = document
			.getElementById("frmGeneral:tblAsignacion:txtFilterNoSerieC4").value;
	var f2 = document
			.getElementById("frmGeneral:tblAsignacion:txtFilterFAsignadaC4").value;
	var f3 = document
			.getElementById("frmGeneral:tblAsignacion:txtFilterFDenegadaC4").value;
	var f4 = document
			.getElementById("frmGeneral:tblAsignacion:txtFilterEmpleadoC4").value;
	if (f1 == "%" && f2 == "%" && f3 == "%" && f4 == "%") {
		if (fas.length == 0 || pai == "0" || ldc == "0" || uni == "0"
				|| emp == "0" || edo == "-1") {
			alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
			return false;
		}
	} else {
		if (f1.length > 0 || f2.length > 0 || f3.length > 0 || f4.length > 0) {
			return true;
		}
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 5 - Rutas */
function requeridosC5() {
	var cve = document.getElementById("frmGeneral:txtCveRutaC5").value;
	var des = document.getElementById("frmGeneral:txtDescRutaC5").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC5").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC5").value;
	var tip = document.getElementById("frmGeneral:cmbxTipoRutaC5").value;
	var zon = document.getElementById("frmGeneral:cmbxZonaC5").value;
	var pob = document.getElementById("frmGeneral:txtDomicilioC5").value;
	var kmm = document.getElementById("frmGeneral:txtKmC5").value;
	var noo = document.getElementById("frmGeneral:txtNoPromOrdenesC5").value;
	var tie = document.getElementById("frmGeneral:txtPromEfectivoC5").value;
	var tit = document.getElementById("frmGeneral:txtPromTotalC5").value;
	var tri = document.getElementById("frmGeneral:cmbxTipoRiesgC5").value;
	var dRep = document.getElementById("frmGeneral:cmbxDiaReparto").value;

	if (cve.trim().length == 0 || des.trim().length == 0 || pai == "0"
			|| ldc == "0" || tip == "0" || zon == "0" || pob.trim().length == 0
			|| kmm.trim().length == 0 || noo.trim().length == 0
			|| tie.trim().length == 0 || tit.trim().length == 0 || tri == "0"
			|| dRep == "0") {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}

/** Validar Campos Requeridos y mascaras de Catalog 6 - Tipo Rutas */
function requeridosC6() {
	var cve = document.getElementById("frmGeneral:txtCveC6").value;
	var des = document.getElementById("frmGeneral:txtDescC6").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC6").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC6").value;
	if (cve.trim().length == 0 || des.trim().length == 0 || pai == "0"
			|| ldc == "0") {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 7 - Representante */
function requeridosC7() {
	var rut = document.getElementById("frmGeneral:cmbxRutaC7").value;
	var rep = document.getElementById("frmGeneral:cmbxRepresentanteC7").value;
	var alt = document.getElementById("frmGeneral:txtfAltaC7").value;
	var ant = document.getElementById("frmGeneral:txtSeqEntregaAntC7").value;
	var rec = document.getElementById("frmGeneral:txtSeqEntregaRecC7").value;
	if (rut == 0 || rep == 0 || alt.length == 0 || ant.trim().length == 0
			|| rec.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}

/** Validar Campos Requeridos y mascaras de Catalog 8 - Asignacion de Ruta  */
function requeridosC8() {
	var rut = document.getElementById("frmGeneral:cmbxRutaC8").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC8").value;
	var zon = document.getElementById("frmGeneral:cmbxZonaC8").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC8").value;
	var emp = document.getElementById("frmGeneral:cmbxEmpleadoC8").value;
	var asi = document.getElementById("frmGeneral:txtfAsignadaC8").value;
	var tip = document.getElementById("frmGeneral:cmbxTipoAsigC8").value;

	if (rut == "0" || pai == "0" || zon == "0" || ldc == "0" || emp == "0"
			|| asi.length == 0 || tip == "0") {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 9 - HandHeld  */
function requeridosC9() {
	var mac = document.getElementById("frmGeneral:txtMacC9").value;
	var dip = document.getElementById("frmGeneral:txtDirIPC9").value;
	var edo = document.getElementById("frmGeneral:cmbxEstatusC9").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC9").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC9").value;
	var mar = document.getElementById("frmGeneral:txtMarcaC9").value;
	var mod = document.getElementById("frmGeneral:txtModelC9").value;
	var ser = document.getElementById("frmGeneral:txtNoSerieC9").value;

	if (mac.trim().length == 0 || dip.trim().length == 0 || edo == "-1"
			|| ldc == 0 || pai == 0 || mar.trim().length == 0
			|| mod.trim().length == 0 || ser.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 10 - Empleado  */
function requeridosC10() {
	var nom = document.getElementById("frmGeneral:txtNombreC10").value;
	var app = document.getElementById("frmGeneral:txtApPaternoC10").value;
	var apm = document.getElementById("frmGeneral:txtApMaternoC10").value;
	var sex = document.getElementById("frmGeneral:cmbxSexoC10").value;
	var nac = document.getElementById("frmGeneral:txtfNacimientoC10").value;
	var rfc = document.getElementById("frmGeneral:txtRFCC10").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC10").value;
	var ing = document.getElementById("frmGeneral:txtFIngresoC10").value;
	var pue = document.getElementById("frmGeneral:cmbxPuestoC10").value;
	var civ = document.getElementById("frmGeneral:cmbxEdoCivilC10").value;
	var dom = document.getElementById("frmGeneral:txtDomicilioC10").value;
	var edo = document.getElementById("frmGeneral:cmbxEstatusC10").value;

	if (nom.trim().length == 0 || app.trim().length == 0
			|| apm.trim().length == 0 || nac.length == 0
			|| rfc.trim().length == 0 || ing.length == 0 || sex == 0
			|| ldc == 0 || pue == 0 || civ == 0 || dom.trim().length == 0
			|| edo == -1) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 11 - Puesto  */
function requeridosC11() {
	var cve = document.getElementById("frmGeneral:txtCveC11").value;
	var des = document.getElementById("frmGeneral:txtDescC11").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC11").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC11").value;

	if (cve.trim().length == 0 || des.trim().length == 0 || ldc == 0
			|| pai == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 12 - Linea de Transporte  */
function requeridosC12() {
	var cve = document.getElementById("frmGeneral:txtCveC12").value;
	var des = document.getElementById("frmGeneral:txtDescC12").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC12").value;

	if (cve.trim().length == 0 || pai == 0 || des.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 13 - Informante  */
function requeridosC13() {
	var des = document.getElementById("frmGeneral:txtDescC13").value;
	var tin = document.getElementById("frmGeneral:cmbxTipoInformanteC13").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC13").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC13").value;

	if (tin == 0 || des.trim().length == 0 || ldc == 0 || pai == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 14 - Sub-Bodega  */
function requeridosC14() {
	var cve = document.getElementById("frmGeneral:txtCveC14").value;
	var des = document.getElementById("frmGeneral:txtDescC14").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC14").value;
	var zon = document.getElementById("frmGeneral:cmbxZonaC14").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC14").value;
	var res = document.getElementById("frmGeneral:cmbxRespSubbodC14").value;
	if (cve.trim().length == 0 || des.trim().length == 0 || ldc == 0
			|| pai == 0 || zon == 0 || res == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 15 - Destinatario  */
function requeridosC15() {
	var nom = document.getElementById("frmGeneral:txtNombreC15").value;
	var app = document.getElementById("frmGeneral:txtApPaternoC15").value;
	var apm = document.getElementById("frmGeneral:txtApMaternoC15").value;
	var cve = document.getElementById("frmGeneral:txtCveC15").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC15").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC15").value;
	if (nom.trim().length == 0 || app.trim().length == 0
			|| apm.trim().length == 0 || cve.trim().length == 0 || ldc == 0
			|| pai == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 16 - Tipo Destinatario  */
function requeridosC16() {
	var cve = document.getElementById("frmGeneral:txtCveC16").value;
	var des = document.getElementById("frmGeneral:txtDescC16").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC16").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC16").value;
	if (cve.trim().length == 0 || des.trim().length == 0 || ldc == 0
			|| pai == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 17 - Reporte por Tipo Destinatario  */
function requeridosC17() {
	/**var cve = document.getElementById("frmGeneral:txtCveC17").value;*/
	var cve = document.getElementById("frmGeneral:cmbxReportes17").value;
	var tip = document.getElementById("frmGeneral:cmbxTipoDestC17").value;
	if (cve == 0 || tip == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 18 - Reporte  */
function requeridosC18() {
	var nom = document.getElementById("frmGeneral:txtNombreC18").value;
	var rut = document.getElementById("frmGeneral:txtRutaTempC18").value;
	var tem = document.getElementById("frmGeneral:txtNombreTempC18").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC18").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC18").value;
	var des = document.getElementById("frmGeneral:txtDescC18").value;
	if (nom.trim().length == 0 || rut.trim().length == 0
			|| tem.trim().length == 0 || ldc == 0 || pai == 0
			|| des.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 19 - LDC  */
function requeridosC19() {
	var cve = document.getElementById("frmGeneral:txtClaveC19").value;
	var des = document.getElementById("frmGeneral:txtDescC19").value;
	var raz = document.getElementById("frmGeneral:txtRazonSocC19").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC19").value;
	var seg = document.getElementById("frmGeneral:txtTipoSegC19").value;
	if (cve.trim().length == 0 || des.trim().length == 0
			|| raz.trim().length == 0 || pai == 0 || seg.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 20 - Usuario  */
function requeridosC20() {
	var usr = document.getElementById("frmGeneral:txtUserC20").value;
	var pas = document.getElementById("frmGeneral:txtPasswordC20").value;
	var emp = document.getElementById("frmGeneral:cmbxEmpleadoC20").value;
	var tip = document.getElementById("frmGeneral:cmbxTipoUserC20").value;
	var pai = document.getElementById("frmGeneral:cmbxPaisC20").value;
	var per = document.getElementById("frmGeneral:cmbxPerfilC20").value;
	var ldc = document.getElementById("frmGeneral:cmbxLDCC20").value;
	if (usr.length == 0 || pas.length == 0 || emp == 0 || tip == 0 || per == 0
			|| ldc == 0 || pai == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 21 - Perfil  */
function requeridosC21() {
	var cve = document.getElementById("frmGeneral:txtClaveC21").value;
	var des = document.getElementById("frmGeneral:txtDescC21").value;

	if (cve.trim().length == 0 || des.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}
}
/** Validar Campos Requeridos y mascaras de Catalog 22 - Denominaciones  */
function requeridosC22() {
	var cve = document.getElementById("frmGeneral:txtDenominacionC22").value;

	if (cve.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	}

}

/** Validar Campos Requeridos y mascaras de Catalog Rutas Alternas */
function requeridosRutasAlternas() {
	var rutaOTS = document
			.getElementById("frmGeneral:txtCatRutaAlternaRutaOTS").value;
	var zona = document.getElementById("frmGeneral:cmbxCatRutaAlternaZona").value;
	var rutaSOS = document
			.getElementById("frmGeneral:txtCatRutaAlternaRutaSOS").value;
	var orden = document.getElementById("frmGeneral:cmbxCatRutaAlternaOrden").value;

	if (zona == 0 || orden == 0 || rutaOTS.trim().length == 0
			|| rutaSOS.trim().length == 0) {
		alert("Todos los campos marcados con * son requeridos y no deben contener solo espacios.");
		return false;
	} else if (/\D/.test(rutaOTS) || /\D/.test(rutaOTS)) {
		alert("Las rutas deben de ser numéricas.");
		return false;
	}
}

function mandarEnter(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13) {
		Richfaces.showModalPanel('mp');
		//document.getElementById('frmGeneral:frmCatalog2:'+id).click();
	}
}

function quitarEnter(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13)
		return false;
}

function validaEnter(e, pControl) {
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera

	if (tecla == 13) {
		Richfaces.showModalPanel('mp');
		document.getElementById(pControl).click();
		return false;
	}
}

function validaEnter2(pControl) {
	document.getElementById(pControl).click();
	return false;
}
