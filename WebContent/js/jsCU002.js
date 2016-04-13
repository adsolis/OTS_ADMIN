//CU002.01.02 Recibir Productos de Ventas Anticipadas

function validarCamposVacios() {

	
	inputDescripcion = document
			.getElementById("veasFormDetalles:veasTxtDescripcion");
	inputNoCajas = document.getElementById("veasFormDetalles:veasTxtNoCajas");

	if (inputDescripcion.value.length == 0) {
		alert("Introduzca una descripci�n");
		inputDescripcion.focus();
		return false;
	}

	if (inputNoCajas.value.length == 0) {
		alert("Introduzca un n�mero de cajas");
		inputNoCajas.focus();
		return false;
	}

	if (inputNoCajas.value == 0) {
		alert("El n�mero de cajas debe ser mayor que cero");
		inputNoCajas.value = "";
		inputNoCajas.focus();
		return false;
	}
}

function ejecutarGeneracionCodigosVEAS() {

	activarGeneracion = document
			.getElementById("veasFormBotones:activarGeneracion");
	lnkGenerarCodigosBarras = document
			.getElementById("veasFormBotones:lnkGenerarCodigosBarrasVEAS");

	if (lnkGenerarCodigosBarras != null && activarGeneracion != null) {
		if (activarGeneracion.value == "1") {
			if (document.createEvent) {
				var evObj = document.createEvent('MouseEvents');
				// evObj.initEvent( 'click', false, false );
				evObj.initMouseEvent("click", true, true, window, 0, 0, 0, 0,
						0, false, false, false, false, 0, null);
				lnkGenerarCodigosBarras.dispatchEvent(evObj);
			} else if (document.createEventObject) {
				lnkGenerarCodigosBarras.fireEvent('onclick');
			}
		}
	}
}

function confirmarGuardadoVEAS() {

	if (confirm('�Est� seguro de guardar las recepciones de productos anticipados?')) {
		// ejecutarGeneracionCodigosVEAS();
	} else {
		return false;
	}
}

// FIN CU002.01.02 Recibir Productos de Ventas Anticipadas

// CU002.02.01 Cerrar zona
function confirmaGuardar() {
	var total = document.getElementById("frmPrincipal:frmTabla:TxtCantCodigo");
	var escaneados = document
			.getElementById("frmPrincipal:frmTabla:TxtCantEscaneado");
	if (total.value == escaneados.value) {
		// return true;
		// return confirm('cajas iguales');
		// alert('CAJAS IGUALES');
		return false;
	} else {
		// return confirm('A�n se tienen �tems por escanear, �desea
		// verificar?');
		// alert('NO IGUALES');
		return false;
	}
}
// CU002.02.02 - Entrega en ventanilla
function validaRepresentante() {
	registro = document.getElementById("frmPrincipal:TxtAccount");
	nombre = document.getElementById("frmPrincipal:TxtNombre");
	if (registro.value.lenght == 0) {
		alert('Ingrese registro');
		registro.focus();
		return false;
	}
	if (nombre.value.lenght == 0) {
		alert('Ingrese nombre');
		nombre.focus();
		return false;
	}
}

function posicionarCursor() {
	inputAccount = document.getElementById("frmPrincipal:txtAccount");
	inputAccount.focus();
	return true;
}

function quitarEnterEntregaVentanilla(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13)
		return false;
}
function mandarEnterEntregaVentanilla(e) {

	tecla = (document.all) ? e.keyCode : e.which;
	inputAccount = document.getElementById("frmPrincipal:txtAccount");

	if (tecla == 13) {
		document.getElementById('frmPrincipal:btnConsultar').click();
	}
}

function quitarEnterCodigoLeido(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13)
		return false;
}
function mandarEnterCodigoLeido(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13) {
		document.getElementById('frmCod:btnValida').click();
	}
}

// Inicio CerrarZona

function resaltarRowTabla() {
	obtenerRowSeleccionado();
}

function obtenerRowSeleccionado() {

	var codigoLeido = document.getElementById('frmPrincipal:TxtCodLeido').value;
	// se aplica regla al codigo de barras
	var tamanio = 0;
	tamanio = codigoLeido.length;
	switch (tamanio) {
	case 5:
		codigoLeido = codigoLeido + "00";
		break;
	case 7:
		//Se reviso el d�a 05/06/2014 con ad�n mu�oz que para este tipo de c�digos con longitud 7 no debe de haber modificiaci�n.
		//codigoLeido = codigoLeido.substring(0, 5) + "00";
		break;
	case 8:
		codigoLeido = codigoLeido.substring(3, 8) + "00";
		break;
	case 10:
		//Se reviso el d�a 05/06/2014 con ad�n mu�oz que para este tipo de c�digos con longitud 10 se borran los primeros 3 
		//caracteres solamente.
		codigo = codigo.substring(3, 10);
		//codigo = codigo.substring(0, 5) + "00";
		break;
	default:
		codigoLeido;
		break;
	}
	//
	var rows = document.getElementById('frmTabla:GrdLista')
			.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

	for ( var i = 0; i < rows.length; i++) {
		var valorCelda = document.getElementById('frmTabla:GrdLista:' + i
				+ ':outTextCodigoBarras').childNodes[0].nodeValue;
		if (valorCelda == codigoLeido) {
			if (rows[i].bgColor.toUpperCase() != '#FBD6EA') {
				resaltarFondoRow(rows[i]);
				break;
			}
		}
	}

	// document.getElementById('frmPrincipal:TxtCodLeido').value = "";
	// codigoLeido.focus();
}

function resaltarFondoRow(row) {
	row.bgColor = '#FBD6EA';
}

function resaltarRowsSeleccionados() {

	var rows = document.getElementById('frmTabla:GrdLista')
			.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

	for ( var i = 0; i < rowSeleccionadas.length; i++) {
		resaltarFondoRow(rows[rowSeleccionadas[i]]);
	}
}

function quitarEnter(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13)
		return false;
}
function mandarEnter(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13) {
		document.getElementById('frmPrincipal:btnValida').click();
	}
}

// Fin CerrarZona

// Inicio CU002.01.01
// Valida los campos requeridos al dar click en Guardar para los Premios
function validarRequeridosGuardarPremio() {
	txtCantidadPremios = document
			.getElementById("frmCaptura:txtCantidadPremios");
	txtDescripcionPremios = document
			.getElementById("frmCaptura:txtDescripcionPremios");
	txtPrograma = document.getElementById("frmCaptura:txtPrograma");
	cmbCampaniaPremio = document.getElementById("frmCaptura:cmbCampaniaPremio");

	// Validaciones
	if (txtCantidadPremios.value.length == 0) {
		alert('Debe poner un valor para Cantidad en Premios');
		txtCantidadPremios.focus();
		return false;
	}
	if (txtDescripcionPremios.value.length == 0) {
		alert('Debe poner un valor para Descripci�n en Premios');
		txtDescripcionPremios.focus();
		return false;
	}
	if (txtPrograma.value.length == 0) {
		alert('Debe poner un valor para Programa en Premios');
		txtPrograma.focus();
		return false;
	}
	if (cmbCampaniaPremio.selectedIndex == 0) {
		alert('Debe seleccionar una Campa�a para el Premio');
		cmbCampaniaPremio.focus();
		return false;
	}
}
// Valida los campos requeridos al dar click en Guardar para los Ajustes
function validarRequeridosGuardarAjuste() {
	// Combos
	cmbCampania = document.getElementById("frmPrincipal:cmbCampania");
	cmbZona = document.getElementById("frmPrincipal:cmbZona");
	// Cajas de texto de cantidades
	txtCosmeticoBuenEstado = document
			.getElementById("frmCaptura:txtCosmeticoBuenEstado");
	txtCosmeticoMalEstado = document
			.getElementById("frmCaptura:txtCosmeticoMalEstado");
	txtBienestarBellezaBuenEstado = document
			.getElementById("frmCaptura:txtBienestarBellezaBuenEstado");
	txtBienestarBellezaMalEstado = document
			.getElementById("frmCaptura:txtBienestarBellezaMalEstado");
	txtHogarBuenEstado = document
			.getElementById("frmCaptura:txtHogarBuenEstado");
	txtHogarMalEstado = document.getElementById("frmCaptura:txtHogarMalEstado");
	txtModaBuenEstado = document.getElementById("frmCaptura:txtModaBuenEstado");
	txtModaMalEstado = document.getElementById("frmCaptura:txtModaMalEstado");
	txtLenceriaBuenEstado = document
			.getElementById("frmCaptura:txtLenceriaBuenEstado");
	txtLenceriaMalEstado = document
			.getElementById("frmCaptura:txtLenceriaMalEstado");
	txtJoyeriaBuenEstado = document
			.getElementById("frmCaptura:txtJoyeriaBuenEstado");
	txtJoyeriaMalEstado = document
			.getElementById("frmCaptura:txtJoyeriaMalEstado");
	txtBienestarCasaModaBuenEstado = document
			.getElementById("frmCaptura:txtBienestarCasaModaBuenEstado");
	txtBienestarCasaModaMalEstado = document
			.getElementById("frmCaptura:txtBienestarCasaModaMalEstado");
	// Cajas de texto de totales
	txtTotalCajasEnviarBelleza = document
			.getElementById("frmCaptura:txtTotalCajasEnviarBelleza");
	txtTotalCajasEnviarCasaModa = document
			.getElementById("frmCaptura:txtTotalCajasEnviarCasaModa");
	txtTotalCajasEnviarPremios = document
			.getElementById("frmCaptura:txtTotalCajasEnviarPremios");
	// Subtotales
	lblSubtotalBellezaBuenEstado = document
			.getElementById("frmCaptura:lblSubtotalBellezaBuenEstado");
	lblSubtotalBellezaMalEstado = document
			.getElementById("frmCaptura:lblSubtotalBellezaMalEstado");
	lblSubtotalCasaModaBuenEstado = document
			.getElementById("frmCaptura:lblSubtotalCasaModaBuenEstado");
	lblSubtotalCasaModaMalEstado = document
			.getElementById("frmCaptura:lblSubtotalCasaModaMalEstado");

	// Validaciones
	if (cmbCampania.selectedIndex == 0) {
		alert('Debe seleccionar una Campa�a');
		cmbCampania.focus();
		return false;
	}
	if (cmbZona.selectedIndex == 0) {
		alert('Debe seleccionar una Zona');
		cmbZona.focus();
		return false;
	}

	if (txtCosmeticoBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Belleza-Cosmetico-Buen Estado');
		txtCosmeticoBuenEstado.focus();
		return false;
	}
	if (txtCosmeticoMalEstado.value.length == 0) {
		alert('Debe poner un valor para Belleza-Cosmetico-Mal Estado');
		txtCosmeticoMalEstado.focus();
		return false;
	}
	if (txtBienestarBellezaBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Belleza-Bienestar-Buen Estado');
		txtBienestarBellezaBuenEstado.focus();
		return false;
	}
	if (txtBienestarBellezaMalEstado.value.length == 0) {
		alert('Debe poner un valor para Belleza-Bienestar-Mal Estado');
		txtBienestarBellezaMalEstado.focus();
		return false;
	}
	if (txtHogarBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Hogar-Buen Estado');
		txtHogarBuenEstado.focus();
		return false;
	}
	if (txtHogarMalEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Hogar-Mal Estado');
		txtHogarMalEstado.focus();
		return false;
	}
	if (txtModaBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Moda-Buen Estado');
		txtModaBuenEstado.focus();
		return false;
	}
	if (txtModaMalEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Moda-Mal Estado');
		txtModaMalEstado.focus();
		return false;
	}
	if (txtLenceriaBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Lencer�a-Buen Estado');
		txtLenceriaBuenEstado.focus();
		return false;
	}
	if (txtLenceriaMalEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Lencer�a-Mal Estado');
		txtLenceriaMalEstado.focus();
		return false;
	}
	if (txtJoyeriaBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Joyer�a-Buen Estado');
		txtJoyeriaBuenEstado.focus();
		return false;
	}
	if (txtJoyeriaMalEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Joyer�a-Buen Estado');
		txtJoyeriaMalEstado.focus();
		return false;
	}
	if (txtBienestarCasaModaBuenEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Bienestar-Buen Estado');
		txtBienestarCasaModaBuenEstado.focus();
		return false;
	}
	if (txtBienestarCasaModaMalEstado.value.length == 0) {
		alert('Debe poner un valor para Casa y Moda-Bienestar-Mal Estado');
		txtBienestarCasaModaMalEstado.focus();
		return false;
	}

	if (txtTotalCajasEnviarBelleza.value.length == 0) {
		alert('Debe poner un valor para el Total de cajas a enviar de Belleza');
		txtTotalCajasEnviarBelleza.focus();
		return false;
	}
	if (txtTotalCajasEnviarCasaModa.value.length == 0) {
		alert('Debe poner un valor para el Total de cajas a enviar de Casa y Moda');
		txtTotalCajasEnviarCasaModa.focus();
		return false;
	}
	if (txtTotalCajasEnviarPremios.value.length == 0) {
		alert('Debe poner un valor para el Total de cajas a enviar de Premios');
		txtTotalCajasEnviarPremios.focus();
		return false;
	}

	// Inicializamos valor subtotales
	sumaSubtotales = 0;
	sumaSubtotales = Number(lblSubtotalBellezaBuenEstado.value)
			+ Number(lblSubtotalBellezaMalEstado.value)
			+ Number(lblSubtotalCasaModaBuenEstado.value)
			+ Number(lblSubtotalCasaModaMalEstado.value);
	if (sumaSubtotales == 0) {
		alert('Favor de proporcionar la informaci�n correspondiente a Ajustes y/o Premios');
		return false;
	}

	return confirm('�Est� seguro de guardar el ajuste?');
}
// Suma los subtotales a mostrar
function sumarSubtotales() {
	// Obtenemos los controles
	txtCosmeticoBuenEstado = document
			.getElementById("frmCaptura:txtCosmeticoBuenEstado");
	txtCosmeticoMalEstado = document
			.getElementById("frmCaptura:txtCosmeticoMalEstado");
	txtBienestarBellezaBuenEstado = document
			.getElementById("frmCaptura:txtBienestarBellezaBuenEstado");
	txtBienestarBellezaMalEstado = document
			.getElementById("frmCaptura:txtBienestarBellezaMalEstado");

	txtHogarBuenEstado = document
			.getElementById("frmCaptura:txtHogarBuenEstado");
	txtHogarMalEstado = document.getElementById("frmCaptura:txtHogarMalEstado");
	txtModaBuenEstado = document.getElementById("frmCaptura:txtModaBuenEstado");
	txtModaMalEstado = document.getElementById("frmCaptura:txtModaMalEstado");
	txtLenceriaBuenEstado = document
			.getElementById("frmCaptura:txtLenceriaBuenEstado");
	txtLenceriaMalEstado = document
			.getElementById("frmCaptura:txtLenceriaMalEstado");
	txtJoyeriaBuenEstado = document
			.getElementById("frmCaptura:txtJoyeriaBuenEstado");
	txtJoyeriaMalEstado = document
			.getElementById("frmCaptura:txtJoyeriaMalEstado");
	txtBienestarCasaModaBuenEstado = document
			.getElementById("frmCaptura:txtBienestarCasaModaBuenEstado");
	txtBienestarCasaModaMalEstado = document
			.getElementById("frmCaptura:txtBienestarCasaModaMalEstado");

	lblSubtotalBellezaBuenEstado = document
			.getElementById("frmCaptura:lblSubtotalBellezaBuenEstado");
	lblSubtotalBellezaMalEstado = document
			.getElementById("frmCaptura:lblSubtotalBellezaMalEstado");
	lblSubtotalCasaModaBuenEstado = document
			.getElementById("frmCaptura:lblSubtotalCasaModaBuenEstado");
	lblSubtotalCasaModaMalEstado = document
			.getElementById("frmCaptura:lblSubtotalCasaModaMalEstado");

	// Inicializamos valores
	bellezaBE = 0;
	bellezaME = 0;
	casaModaBE = 0;
	casaModaME = 0;

	// Validamos y sumamos controles
	if (!isNaN(txtCosmeticoBuenEstado.value))
		bellezaBE += Number(txtCosmeticoBuenEstado.value);
	if (!isNaN(txtCosmeticoMalEstado.value))
		bellezaME += Number(txtCosmeticoMalEstado.value);
	if (!isNaN(txtBienestarBellezaBuenEstado.value))
		bellezaBE += Number(txtBienestarBellezaBuenEstado.value);
	if (!isNaN(txtBienestarBellezaMalEstado.value))
		bellezaME += Number(txtBienestarBellezaMalEstado.value);

	if (!isNaN(txtHogarBuenEstado.value))
		casaModaBE += Number(txtHogarBuenEstado.value);
	if (!isNaN(txtHogarMalEstado.value))
		casaModaME += Number(txtHogarMalEstado.value);
	if (!isNaN(txtModaBuenEstado.value))
		casaModaBE += Number(txtModaBuenEstado.value);
	if (!isNaN(txtModaMalEstado.value))
		casaModaME += Number(txtModaMalEstado.value);
	if (!isNaN(txtLenceriaBuenEstado.value))
		casaModaBE += Number(txtLenceriaBuenEstado.value);
	if (!isNaN(txtLenceriaMalEstado.value))
		casaModaME += Number(txtLenceriaMalEstado.value);
	if (!isNaN(txtJoyeriaBuenEstado.value))
		casaModaBE += Number(txtJoyeriaBuenEstado.value);
	if (!isNaN(txtJoyeriaMalEstado.value))
		casaModaME += Number(txtJoyeriaMalEstado.value);
	if (!isNaN(txtBienestarCasaModaBuenEstado.value))
		casaModaBE += Number(txtBienestarCasaModaBuenEstado.value);
	if (!isNaN(txtBienestarCasaModaMalEstado.value))
		casaModaME += Number(txtBienestarCasaModaMalEstado.value);

	// Asignamos texto a subtotales
	lblSubtotalBellezaBuenEstado.innerText = bellezaBE.toString();
	lblSubtotalBellezaMalEstado.innerText = bellezaME.toString();
	lblSubtotalCasaModaBuenEstado.innerText = casaModaBE.toString();
	lblSubtotalCasaModaMalEstado.innerText = casaModaME.toString();
}

// Valida que se haya generado el link para generar c�digos y que se active la
// generaci�n autom�tica, y da click a la liga para generar
function ejecutarGeneracionCodigos() {
	activarGeneracion = document.getElementById("frmCaptura:activarGeneracion");
	lnkGenerarCodigosBarras = document
			.getElementById("frmCaptura:lnkGenerarCodigosBarras");

	if (lnkGenerarCodigosBarras != null && activarGeneracion != null) {
		if (activarGeneracion.value == "1") {
			if (document.createEvent) {
				var evObj = document.createEvent('MouseEvents');
				// evObj.initEvent( 'click', false, false );
				evObj.initMouseEvent("click", true, true, window, 0, 0, 0, 0,
						0, false, false, false, false, 0, null);
				lnkGenerarCodigosBarras.dispatchEvent(evObj);
			} else if (document.createEventObject) {
				lnkGenerarCodigosBarras.fireEvent('onclick');
			}
		}
	}

}
// Fin CU002.01.01

// Inicio CU002.01.03
// Valida los campos requeridos al dar click en Guardar Item
function validarRequeridosGuardarItem_LiqSubBodega() {
	txtCantidad = document.getElementById("frmModificar:txtCantidad");
	txtEntregada = document.getElementById("frmModificar:txtEntregada");
	txtFechaEntregada = document
			.getElementById("frmModificar:txtFechaEntregada");
	txtRecibida = document.getElementById("frmModificar:txtRecibida");
	txtCausaFaltante = document.getElementById("frmModificar:txtCausaFaltante");
	txtTipo = document.getElementById("frmModificar:txtTipo");

	// Validaciones
	if (txtEntregada.value.length == 0) {
		alert('Debe poner un valor para Cantidad Entregada');
		txtEntregada.focus();
		return false;
	}
	if (txtFechaEntregada.value.length == 0) {
		alert('Debe poner una Fecha de Entrega');
		txtFechaEntregada.focus();
		return false;
	}
	if (txtRecibida.value.length == 0) {
		alert('Debe poner un valor para Cantidad Recibida');
		txtRecibida.focus();
		return false;
	}

	if (txtTipo.value == 'CAJA') {
		if (Number(txtEntregada.value) == Number(txtCantidad.value)
				&& (Number(txtRecibida.value) > 0 || txtCausaFaltante.value.length != 0)) {
			alert('No debe de existir informaci�n en Recibida o Causa Faltante porque se entreg� la cantidad dejada');
			// txtEntregada.focus();
			return false;
		}
		if (Number(txtEntregada.value) < Number(txtCantidad.value)
				&& (Number(txtRecibida.value) == 0 && txtCausaFaltante.value.length == 0)) {
			alert('Debe de existir informaci�n en Recibida o Causa Faltante porque se entreg� cantidad menor a la cantidad dejada en sub bodega');
			// txtEntregada.focus();
			return false;
		}
	}

	return true;
}

function validarCampoCantidad_LiqSubBodega(obj) {
	txtCantidad = document.getElementById("frmModificar:txtCantidad");
	txtEntregada = document.getElementById("frmModificar:txtEntregada");
	txtRecibida = document.getElementById("frmModificar:txtRecibida");
	txtCausaFaltante = document.getElementById("frmModificar:txtCausaFaltante");
	txtTipo = document.getElementById("frmModificar:txtTipo");

	if (txtTipo.value.length != 0) {
		if (isNaN(obj.value) || obj.value.length == 0) {
			alert('El dato no es num�rico o es menor a cero, favor de corregir');
			obj.focus();
			return;
		}
		if (Number(obj.value) > Number(txtCantidad.value)) {
			alert('Se est� registrando una cantidad como entregada/recibida mayor a la cantidad que fue dejada en la sub bodega');
			obj.focus();
			return;
		}

		if (Number(txtEntregada.value) == Number(txtCantidad.value)
				&& (Number(txtRecibida.value) > 0 || txtCausaFaltante.value.length != 0)) {
			alert('No debe de existir informaci�n en Recibida o Causa Faltante porque se entreg� la cantidad dejada');
			// txtEntregada.focus();
			return;
		}
	}
}

function confirmarGuardado_LiqSubBodega() {
	return confirm('�Est� seguro de guardar la liquidaci�n de sub bodega?');
}

// Fin CU002.01.03

function validaMonto(e, field){
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8, 46];//Caracter especial
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8, 39, 37, 46];//Caracter especial para validar retroceso, suprimir, flechas adelante y atras
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var numeros = "0123456789";//Caracteres a validar
	
	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
    	if(key == especiales[i]) {
    		tecla_especial = true;
    		break;
		}
	}
    //Verifica que el caracter presionado sea valido
    if(numeros.indexOf(tecla) == -1 && !tecla_especial){
    	return false;	
    }else{
    	 if (key == 46){ 
    		 if (field.value == ""){ 
    			 return false; 
    		 }
    		 regexp = /^[0-9]+$/; 
    		 return regexp.test(field.value); 
    	 }
    }    
}

/**
 * Funcion para validar los numeros
 * @author Andres.Alvarez
 * @date 14/05/2014
 * @param e: Obtiene el valor del teclado
 * @returns {Boolean}: Devuelve falso si presiona un caracter no valido
 */
function validaNumeros(e){ 
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8];//Caracter especial
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8,39,37,46];//Caracter especial para validar retroceso, suprimir, flechas adelante y atras
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var numeros = "0123456789";//Caracteres a validar

	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
    	if(key == especiales[i]) {
    		tecla_especial = true;
    		break;
		}
	}
    //Verifica que el caracter presionado sea valido
    if(numeros.indexOf(tecla) == -1 && !tecla_especial){
    	return false;	
    }    
}

/**
 * Funcion para validar las letras
 * @author Andres.Alvarez
 * @date 14/05/2014
 * @param e: Obtiene el valor del teclado
 * @returns {Boolean}: Devuelve falso si presiona un caracter no valido
 */
function validaLetras(e) {
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8];//Caracter especial
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8,39,37,46];//Caracter especial para validar retroceso, suprimir, flechas adelante y atras
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var letras = " �����abcdefghijklmn�opqrstuvwxyz";//Caracteres a validar
	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
        if(key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    //Verifica que el caracter presionado sea valido
    if(letras.indexOf(tecla) == -1 && !tecla_especial){
    	return false;
    }        
}

/**
 * Funcion para validar letras y numeros
 * @author Andres.Alvarez
 * @date 28/05/2014
 * @param e: Obtiene el valor del teclado
 * @returns {Boolean}: Devuelve falso si presiona un caracter no valido
 */
function validaLetrasYNumeros(e){
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8, 44];//Caracter especial retroceso y coma
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8, 37, 39, 44, 46];//Caracter especial para validar retroceso, suprimir, flechas adelante, atras y coma
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var letras = " �����abcdefghijklmn�opqrstuvwxyz0123456789";//Caracteres a validar
	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
        if(key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    //Verifica que el caracter presionado sea valido
    if(letras.indexOf(tecla) == -1 && !tecla_especial){
    	return false;
    }        
}

/**
 * Funcion para validar formato de fecha
 * @author Andres.Alvarez
 * @date 28/05/2014
 * @param e: Obtiene el valor del teclado
 * @returns {Boolean}: Devuelve falso si presiona un caracter no valido
 */
function validaFormatoFecha(e){
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8];//Caracter especial
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8,39,37,46];//Caracter especial para validar retroceso, suprimir, flechas adelante y atras
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var formato = "/0123456789";//Caracteres a validar
	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
        if(key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    //Verifica que el caracter presionado sea valido
    if(formato.indexOf(tecla) == -1 && !tecla_especial){
    	return false;
    }      
}

//UCW013_1: Entregar orden en ventanilla 

/**
 * Funcion para verificar codigo
 * Esta validacion la solicito el cliente
 * @author Andres.Alvarez
 * @date 28/05/2014
 */
function verificaCodigo(codigo){
	var tamanio = 0;
	tamanio = codigo.length;
	switch (tamanio) {
	case 5:
		codigo = codigo + "00";
		break;
	case 7:
		//Se reviso el d�a 05/06/2014 con ad�n mu�oz que para este tipo de c�digos con longitud 7 no debe de haber modificiaci�n.
        //nuevoCodigo = codigoB.Remove(5, 2) + "00";
        //codigo = codigo.substring(0, 5) + "00";
		break;	
	case 8:
		codigo = codigo.substring(3, 8) + "00";
		break;
	case 10:
		//Se reviso el d�a 05/06/2014 con ad�n mu�oz que para este tipo de c�digos con longitud 10 se borran los primeros 3 
		//caracteres solamente.
		codigo = codigo.substring(3, 10);
		//codigo = codigo.substring(0, 5) + "00";
		break;
	default:
		codigo;
		break;
	}
	return codigo; 
}

/**
 * Valida que el usuario ingreso solo numeros y ejecute la accion del boton
 * @param e
 * @returns {Boolean}
 */
function entregaVentanillaMasivaKeyPress(e, pControl) {
	var tecla = (document.all) ? e.keyCode : e.which;//keyCode para IE which para Netscape/Firefox/Opera
	
	if (tecla == 13) {
		if(pControl == 'Consultar'){
			document.getElementById('frmCriterioRepresentante:btn' + pControl)
			.click();	
		}else{
			return validaCodigoLeido(pControl);	
		}
		
	}else{
		return	validaNumeros(e);//Valida numeros y retroceso
	}	
}


/**
 * Valida Filas con respecto al codigo leido o ingresado
 * @author Andres.Alvarez
 * @date 16/05/2014
 * @param pControl: Obtiene el nombre de la forma que lo esta llamando
 * @returns {Boolean}: Devuelve falso para no realizar un postback
 * @modification jr.gonzalez 23/09/2014: Se agregan validaci�n para codigo FSC y EAN13
 */
function validaCodigoLeido(pControl) {
	var vCodigoLeido = document.getElementById('frmDetalles:txtCodigo' + pControl );
	
	if(vCodigoLeido.value != "")
	{
		//Variables
		var vDuplicado = false;//Valida que no se haya leido mas de 1 vez el codigo capturado
		var vPosicion = -1;//Obtiene la posicion de la fila en el grid
		var vExiste = false;//Valida si existe el codigo capturado
		var vValidaPremiosYUnitarios = false;//Valida que la cantidad, ya sea de Premios o Unitarios no supere la cantidad indicada por el servidor
		var vValorCelda = null;//Obtiene el valor de la celda por medio de corrimiento de filas en el grid
		var vValorCeldaFSC = null;//Obtiene el valor de la celda por medio de corrimiento de filas en el grid
		var vValorLeido = null;//Obtiene el la cantidad de veces que se ha leido el codigo capturado
		var vValorEntrada = null;//Obtiene el valor que se encuentra en la caja de texto, ya sea de Premios o Unitarios
		var vValorFacturado = null;//Obtiene el valor total a facturar, ya sea de Premios o Unitarios
		var vValido = document.getElementById('frmDetalles:img' + pControl + 'Valido');//Muestra la imagen de aceptacion si el codigo capturado es correcto
		var vInvalido = document.getElementById('frmDetalles:img' + pControl + 'Invalido');//Muestra la imagen de error si el codigo capturado es incorrecto
		var vValorPendiente = document.getElementById('frmDetalles:lblPendientes' + pControl );//Resta el valor de los codigo que estan pendientes por leer
		var vValorEntregado = document.getElementById('frmDetalles:lblEntregados' + pControl );//Se va incrementando si el codigo capturado fue correcto
		var vError = document.getElementById('frmDetalles:lblError' + pControl );//Muestra etiqueta si hubo un error en el codigo capturado
		var vCantidadCB = 0;
		// se aplica regla al codigo de barras
		vCodigoLeido.value = verificaCodigo(vCodigoLeido.value);
		//Obtiene las filas de la lista
		var vDr;
		
		if(pControl == 'Cajas')
		{
			vDr = _arregloCajas != null ?
				_arregloCajas : 
				document.getElementById('frmDetalles:dtg' + pControl).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		}
		else
		{
			vDr = _arregloUnitarios != null ?
				_arregloUnitarios : 
				document.getElementById('frmDetalles:dtg' + pControl).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		}
		
		//Ciclo para verificar cuantos CB del mismo existe
		for ( var i = 0; i < vDr.length; i++) 
		{
			//Si es caja
			if (pControl == 'Cajas')
			{
				vValorCelda = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':codigoBarra' + pControl ).childNodes[0].nodeValue;
				//Si coincide el valor del renglon con el valor le�do
				if (vValorCelda == vCodigoLeido.value) 
				{
					vCantidadCB++;
				}
			}
			//Si es unitario o premio
			else
			{
				//Si es n�mero de 13 caracteres se compara contra EAN13
				if (vCodigoLeido.value.length == 13)
				{
					vValorCelda = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':codigoEAN13' + pControl ).childNodes[0].nodeValue;
					//Si coincide el valor del renglon con el valor le�do
					if (vValorCelda == vCodigoLeido.value)
					{
						vCantidadCB++;
					}
				}
				//Validaci�n contra FSC
				else 
				{
					vValorCelda = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':codigoFSC' + pControl ).childNodes[0].nodeValue;
					//Si coincide el valor del renglon con el valor le�do
					if (vValorCelda == vCodigoLeido.value)
					{
						vCantidadCB++;
					}
				}
			}
		}
		
		//Ciclo para verificar si existe el codigo leido
		for ( i = 0; i < vDr.length; i++)
		{
			//vValorLeido = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblLeido' + pControl );		
			//vValorCelda = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':codigoBarra' + pControl ).childNodes[0].nodeValue;
			if(pControl == 'Cajas')
			{
				vValorLeido = vDr[i].childNodes[4].textContent;
				vValorCelda = vDr[i].childNodes[2].textContent;	
			}
			else
			{
				vValorLeido = vDr[i].childNodes[8].textContent;
				vValorCeldaFSC = vDr[i].childNodes[2].textContent;
				vValorCelda = vDr[i].childNodes[3].textContent;
				vValorEntrada = vDr[i].childNodes[7].childNodes[0].value;
				vValorFacturado = vDr[i].childNodes[6].textContent;
				//vValorEntrada = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':txtCantidadEntrada' + pControl);
				//vValorFacturado =  document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblCantidadFacturada' + pControl);
			}
			//Valida que el codigo capturado se encuentre el grid
			if (vValorCelda == vCodigoLeido.value || vValorCeldaFSC == vCodigoLeido.value)
			{
				vPosicion = i;
				vExiste = true;
				//Valida si el codigo capturado ya fue leido
				if (vDr[i].bgColor.toUpperCase() != '#FBD6EA')
				{
					if(pControl != 'Cajas')
					{
						vDr[i].childNodes[6].childNodes[0].focus();
						//vValorEntrada.focus();
					}
					resaltarFondoRow(vDr[i]);
					break;
				}
				else
				{
					vDuplicado = true;
					break;
				}
			}
		}
		//Valida que el codigo capturado se encuentre en el grid
		if(vExiste)
		{
			//Valida que el codigo capturado no se haya leido mas de una vez
			if(!vDuplicado)
			{
				vError.textContent = " ";
				vValorEntregado.childNodes[0].nodeValue++;
				//vValorLeido.childNodes[0].nodeValue++;
				vValorPendiente.childNodes[0].nodeValue--;
				vInvalido.style.visibility = "hidden";
				vValido.style.visibility = "visible";
				//Valida el tipo de formulario que lo esta llamando para tomar el control correspondiente
				if(pControl == 'Cajas')
				{
					vDr[vPosicion].childNodes[4].textContent = parseInt(vDr[vPosicion].childNodes[4].textContent) + 1;
					vDr[vPosicion].childNodes[3].childNodes[0].checked = true;
					vDr[vPosicion].childNodes[3].childNodes[0].focus();
					//document.getElementById('frmDetalles:dtg' + pControl + ':' + vPosicion + ':checkBoxEscaneado').checked = true;
					//document.getElementById('frmDetalles:dtg' + pControl + ':' + vPosicion + ':checkBoxEscaneado').focus();
				}
				else
				{//En esta condicion se validan los formularios de Unitarios y Premios
					vDr[vPosicion].childNodes[8].textContent = parseInt(vDr[vPosicion].childNodes[8].textContent) + 1;
					vDr[vPosicion].childNodes[7].childNodes[0].value = parseInt(vDr[vPosicion].childNodes[7].childNodes[0].value) + 1;//vValorEntrada.value++;
				}		
			}
			else
			{//Si el codigo capturado fue leido mas de una vez, habilita la imangen de error e indica el codigo duplicado 
				//Valida el tipo de formulario que lo esta llamando para tomar el control correspondiente
				if(pControl == 'Cajas')
				{
					vValidaPremiosYUnitarios = true;
					vDr[i].childNodes[4].style.color="red";	
				}
				else
				{
					if(vCantidadCB > 0)
					{
						vExiste = false;
						var vSumaValorLeido = null;
						//validaCantidadCodigoBarra(pControl);
						for (i = 0; i < vDr.length; i++)
						{
							vValorLeido = vDr[i].childNodes[8].textContent;
							vValorCeldaFSC = vDr[i].childNodes[2].textContent;
							vValorCelda = vDr[i].childNodes[3].textContent;
							vValorEntrada = vDr[i].childNodes[7].childNodes[0].value;
							vValorFacturado = vDr[i].childNodes[6].textContent;
							//vValorLeido = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblLeido' + pControl );
							//vValorCelda = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':codigoBarra' + pControl ).childNodes[0].nodeValue;
							//vValorEntrada = document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':txtCantidadEntrada' + pControl);
							//vValorFacturado =  document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblCantidadFacturada' + pControl);
							if (vValorCelda == vCodigoLeido.value || vValorCeldaFSC == vCodigoLeido.value)
							{
								if(vValorEntrada != parseInt(vValorFacturado))
								{
									vError.textContent = " ";
									vValorEntregado.childNodes[0].nodeValue++;
									vDr[i].childNodes[8].textContent = parseInt(vDr[i].childNodes[8].textContent) + 1;//vValorLeido.childNodes[0].nodeValue++;
									vValorPendiente.childNodes[0].nodeValue--;
									vInvalido.style.visibility = "hidden";
									vValido.style.visibility = "visible";
									vDr[i].childNodes[7].childNodes[0].value = parseInt(vDr[i].childNodes[7].childNodes[0].value) + 1;//vValorEntrada.value++;
									vDr[i].childNodes[7].childNodes[0].focus();
									resaltarFondoRow(vDr[i]);
									vExiste = false;
									_acceso = false;
									break;
								}
								else
								{
									vSumaValorLeido = vDr[i].childNodes[8].textContent; // document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblLeido' + pControl );
									vExiste = true;
								}
							}
						}
						if(vExiste)
						{
							vInvalido.style.visibility = "hidden";
							vValido.style.visibility = "hidden";
							vDr[vPosicion].childNodes[8].textContent = parseInt(vSumaValorLeido)+1; //vSumaValorLeido.childNodes[0].nodeValue++;
							vDr[vPosicion].childNodes[8].style.color="red"; //vSumaValorLeido.style.color="red";	
							vError.textContent = "Ya se ingreso la cantidad total a facturar.";
						}
					}
					else
					{
						vValorEntrada = parseInt(vDr[vPosicion].childNodes[7].textContent) + 1; 
						if(vValorEntrada > vValorFacturado)
						{
							vError.textContent = "No se puede ingresar mas de la cantidad facturada";
							vDr[vPosicion].childNodes[8].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
							vDr[vPosicion].childNodes[7].nodeValue =vDr[vPosicion].childNodes[6].textContent; //vValorEntrada.value = parseInt(vValorFacturado.textContent);
							vValorLeido.style.color="red";
							vValido.style.visibility = "hidden";
							vInvalido.style.visibility = "hidden";		
						}
						else
						{//Realiza la modificacion de etiquetas y control
							vValorEntregado.childNodes[0].nodeValue++;
							vDr[vPosicion].childNodes[8].textContent = parseInt(vDr[vPosicion].childNodes[8].textContent) + 1;//vValorLeido.childNodes[0].nodeValue++;
							vValorPendiente.childNodes[0].nodeValue--;
							//vValorEntrada.value++;
						}
					}
				}
				//Valida si el codigo es duplicado
				if(vValidaPremiosYUnitarios)
				{
					vDr[vPosicion].childNodes[5].textContent = parseInt(vDr[vPosicion].childNodes[5].textContent) + 1;//vValorLeido.childNodes[0].nodeValue++;
					vError.textContent = "C�digo duplicado: " + vCodigoLeido.value;				
					vValido.style.visibility = "hidden";
					vInvalido.style.visibility = "visible";				
				}
			}
		}
		else
		{//Si el codigo no se encuentra el grid habilita la imangen de error e indica el codigo incorrecto
			if(pControl == 'Cajas')
			{
				vError.textContent = "El c�digo " + vCodigoLeido.value + " de la caja no corresponde a ninguna de las �rdenes que est�n siendo entregadas en ventanilla.";	
			}
			else if (pControl == 'Unitarios')
			{
				vError.textContent = " El c�digo " + vCodigoLeido.value + " del unitario no corresponde a ninguno de los mostrados en la lista.";
			}
			else if (pControl == 'Premios')
			{
				vError.textContent = "El c�digo " + vCodigoLeido.value + " del premio no corresponde a ninguno de los mostrados en la lista.";
			}
			vValido.style.visibility = "hidden";
			vInvalido.style.visibility = "visible";		
		}
		vCodigoLeido.value = "";
		vCodigoLeido.focus();
	}
	return false;
}

/**
 * Valida si se presiona el checkbox
 * @param pCelda
 */
function validarCheckCaja(pCelda){
	
	document.getElementById('frmDetalles:lblErrorCajas').textContent = " ";
	document.getElementById('frmDetalles:imgCajasValido').style.visibility = "hidden";
	document.getElementById('frmDetalles:imgCajasInvalido').style.visibility = "hidden";
	var vDr = _arregloCajas != null ? _arregloCajas : document.getElementById('frmDetalles:dtgCajas').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	var vIndex = pCelda.parentNode.parentNode.rowIndex - 1;
	
	var vCheckBox =  vDr[vIndex].childNodes[3].childNodes[0].checked; //document.getElementById('frmDetalles:dtgCajas:' + vIndex + ':checkBoxEscaneado');
	var vValorEntregado = document.getElementById('frmDetalles:lblEntregadosCajas');//Se va incrementando si el codigo capturado fue correcto
	var vValorLeido =  vDr[vIndex].childNodes[4].textContent; // document.getElementById('frmDetalles:dtgCajas:' + vIndex + ':lblLeidoCajas');
	var vValorPendiente = document.getElementById('frmDetalles:lblPendientesCajas');//Resta el valor de los codigo que estan pendientes por leer
	
	if(!vCheckBox){				
		vDr[vIndex].bgColor = '#FFFFFF';
		//vCheckBox.checked = false;
		vValorPendiente.childNodes[0].nodeValue++;
		vValorEntregado.childNodes[0].nodeValue--;
		vDr[vIndex].childNodes[4].textContent = 0;
	}else{
		vDr[vIndex].bgColor = '#FBD6EA';
		//vCheckBox.checked = true;
		vValorPendiente.childNodes[0].nodeValue--;
		vValorEntregado.childNodes[0].nodeValue++;
		vDr[vIndex].childNodes[4].textContent = parseInt(vValorLeido)+1;
	}
	if(vValorLeido>1){
		vDr[vIndex].childNodes[4].style.color="red";
	}else{
		vDr[vIndex].childNodes[4].style.color="black";
	}
}

function validarCajaTexto(pCelda, pControl){
	
	document.getElementById('frmDetalles:img' + pControl + 'Valido').style.visibility = "hidden";
	document.getElementById('frmDetalles:img' + pControl + 'Invalido').style.visibility = "hidden";
	var vError = document.getElementById('frmDetalles:lblError' + pControl );
	var vDr = _arregloUnitarios != null ? _arregloUnitarios : document.getElementById('frmDetalles:dtg' + pControl).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	var vIndex = pCelda.parentNode.parentNode.rowIndex - 1;
	
	if(pControl == 'Cajas')
	{
		var vValorEntregado = document.getElementById('frmDetalles:lblEntregados' + pControl);//Se va incrementando si el codigo capturado fue correcto
		//var vValorLeido = vDr[vIndex].childNodes[7].textContent;// document.getElementById('frmDetalles:dtg' + pControl + ':' + vIndex + ':lblLeido' + pControl);
		var vValorPendiente = document.getElementById('frmDetalles:lblPendientes' + pControl);//Resta el valor de los codigo que estan pendientes por leer
		var vValorEntrada = vDr[vIndex].childNodes[6].childNodes[0].value;//document.getElementById('frmDetalles:dtg' + pControl + ':' + vIndex + ':txtCantidadEntrada' + pControl);
		var vValorFacturado = vDr[vIndex].childNodes[5].textContent;// document.getElementById('frmDetalles:dtg' + pControl + ':' + vIndex + ':lblCantidadFacturada' + pControl);
		var vTotal = 0;
		var vExistencia = 0;
		vError.textContent = " ";
		if(vValorEntrada > 0)
		{
			if(vValorEntrada > vValorFacturado)
			{
				vDr[vIndex].bgColor = '#FBD6EA';
				vError.textContent = "No se puede ingresar mas de la cantidad facturada";
				vDr[vIndex].childNodes[7].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
				vDr[vIndex].childNodes[6].childNodes[0].value = vValorFacturado;//vValorEntrada.value = parseInt(vValorFacturado.textContent);			
			}
			else
			{//Realiza la modificacion de etiquetas y control
				vDr[vIndex].bgColor = '#FBD6EA';
				vDr[vIndex].childNodes[7].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
			}
		}
		else
		{
			vDr[vIndex].childNodes[7].style.color="black";//vValorLeido.style.color="black";
			vDr[vIndex].bgColor = '#FFFFFF';	
			vDr[vIndex].childNodes[7].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
		}
		
		for ( var i = 0; i < vDr.length; i++)
		{
			vTotal += parseInt(vDr[i].childNodes[5].textContent);//parseInt(document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblCantidadFacturada' + pControl).textContent);
			vExistencia += parseInt(vDr[i].childNodes[6].childNodes[0].value);//parseInt(document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':txtCantidadEntrada' + pControl).value);
		}
		vValorEntregado.childNodes[0].nodeValue = vExistencia;
		vValorPendiente.childNodes[0].nodeValue = vTotal - vExistencia;
	}
	else
	{
		var vValorEntregado = document.getElementById('frmDetalles:lblEntregados' + pControl);//Se va incrementando si el codigo capturado fue correcto
		var vValorPendiente = document.getElementById('frmDetalles:lblPendientes' + pControl);//Resta el valor de los codigo que estan pendientes por leer
		var vValorEntrada = vDr[vIndex].childNodes[7].childNodes[0].value;//document.getElementById('frmDetalles:dtg' + pControl + ':' + vIndex + ':txtCantidadEntrada' + pControl);
		var vValorFacturado = vDr[vIndex].childNodes[6].textContent;// document.getElementById('frmDetalles:dtg' + pControl + ':' + vIndex + ':lblCantidadFacturada' + pControl);
		var vTotal = 0;
		var vExistencia = 0;
		vError.textContent = " ";
		if(vValorEntrada > 0)
		{
			if(vValorEntrada > vValorFacturado)
			{
				vDr[vIndex].bgColor = '#FBD6EA';
				vError.textContent = "No se puede ingresar mas de la cantidad facturada";
				vDr[vIndex].childNodes[8].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
				vDr[vIndex].childNodes[7].childNodes[0].value = vValorFacturado;//vValorEntrada.value = parseInt(vValorFacturado.textContent);			
			}
			else
			{//Realiza la modificacion de etiquetas y control
				vDr[vIndex].bgColor = '#FBD6EA';
				vDr[vIndex].childNodes[8].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
			}
		}
		else
		{
			vDr[vIndex].childNodes[8].style.color="black";//vValorLeido.style.color="black";
			vDr[vIndex].bgColor = '#FFFFFF';	
			vDr[vIndex].childNodes[8].textContent = vValorEntrada;//vValorLeido.childNodes[0].nodeValue = parseInt(vValorEntrada.value);
		}
		
		for ( var i = 0; i < vDr.length; i++) 
		{
			vTotal += parseInt(vDr[i].childNodes[6].textContent);//parseInt(document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':lblCantidadFacturada' + pControl).textContent);
			vExistencia += parseInt(vDr[i].childNodes[7].childNodes[0].value);//parseInt(document.getElementById('frmDetalles:dtg' + pControl + ':' + i + ':txtCantidadEntrada' + pControl).value);
		}
		vValorEntregado.childNodes[0].nodeValue = vExistencia;
		vValorPendiente.childNodes[0].nodeValue = vTotal - vExistencia;
	}
}

//UCW013_2: Liquidar �rdenes entregadas en ventanilla 

function liquidacionVentanillaMasivaKeyPress(e, pControl) {
	var tecla = (document.all) ? e.keyCode : e.which;//keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == 13) {
		if(pControl == 'Buscar'){
			validaRegistro();
			return  false;
		}
		
	}else{
		return	validaNumeros(e);//Valida numeros y retroceso
	}	
}

function validaRegistro(){
	var vPosicion = -1;//Obtiene la posicion de la fila en el grid
	var vCodigoLeido = document.getElementById('frmBuscarRepresentante:txtregistroBusqueda');
	var vDr = document.getElementById('frmBuscarRepresentante:dtgDetalleRepresentante').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	var vError = document.getElementById('frmBuscarRepresentante:lblMensajeError1');
	var vExiste = false;
	try{
		//Ciclo para verificar si existe el codigo leido
		for ( var i = 0; i < vDr.length; i++) {
			var vValorCelda = document.getElementById('frmBuscarRepresentante:dtgDetalleRepresentante:' + i + ':lblRegistro').childNodes[0].nodeValue;
			if(Number(vValorCelda) == Number(vCodigoLeido.value)){
				vPosicion = i;
				vExiste = true;
				break;
			}
		}
		if(vExiste){
			var btn = document.getElementById('frmBuscarRepresentante:dtgDetalleRepresentante:' + vPosicion + ':btnDetalle');
			vCodigoLeido.value = '';
			document.getElementById('frmDetalles:cobBanco').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').disabled = true;
			btn.click();			
		}else{
			if(vCodigoLeido.value !=''){
				vError.textContent = "No existe el registro "+ Number(vCodigoLeido.value);				
				vError.innerText = "No existe el registro "+ Number(vCodigoLeido.value);
				vCodigoLeido.value ='';
				return false;
			}else{
				return true;
			}
		}
	}catch(ex){
		vError.textContent = 'No existe el registro '+ vCodigoLeido.value;
	}
}

/**
 * Valida el cambio del combo
 * @author Andres.Alvarez
 * @date 23/05/2014
 * @param pControl
 */
function validarCombo(pControl){
	var vPosicion = document.getElementById('frmDetalles:cob'+ pControl).options.selectedIndex; //Obtiene el index del combo
	var vValor = document.getElementById('frmDetalles:cob'+ pControl).options[vPosicion].text; //Obtiene el texto del combo
	//Valida que el tipo de contro se TipoPago
	if(pControl == 'TipoPago'){
		//Valida que el combo tengo el valor indicado
		if(vValor.indexOf('Banco') != -1){
			document.getElementById('frmDetalles:cobBanco').disabled = false;
			document.getElementById('frmDetalles:txtFolioCobranza').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').value='';
			document.getElementById('frmDetalles:cobBanco').focus();
		}
		else if(vValor.indexOf('Pago en efectivo') != -1){
			document.getElementById('frmDetalles:cobBanco').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').value='';
			document.getElementById('frmDetalles:cobBanco').options.selectedIndex = 0;
			document.getElementById('frmDetalles:txtMonto').focus();
		}
		else if(vValor.indexOf('Recibo de cobranza') != -1){
			document.getElementById('frmDetalles:cobBanco').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').disabled = false;
			document.getElementById('frmDetalles:cobBanco').options.selectedIndex = 0;
			document.getElementById('frmDetalles:txtFolioCobranza').focus();
		}
		else{
			document.getElementById('frmDetalles:cobBanco').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').disabled = true;
			document.getElementById('frmDetalles:txtFolioCobranza').value='';
			document.getElementById('frmDetalles:cobBanco').options.selectedIndex = 0;
		}
	}
	else if(pControl == 'QuienRecibe'){
		if(vValor.indexOf('Otros') != -1){
			document.getElementById('frmDetalles:txtOtros').style.visibility = "visible";
		}else{
			document.getElementById('frmDetalles:txtOtros').style.visibility = "hidden";
		}
	}
}


function validarConfirm(pControl){
	var vExiste = false;
	var vError = document.getElementById('frmDetalles:lblMensajeError2');
	var vPosicion = document.getElementById('frmDetalles:cobQuienRecibe').options.selectedIndex; //Obtiene el index del combo
	var vValor = document.getElementById('frmDetalles:cobQuienRecibe').options[vPosicion].text; //Obtiene el texto del combo
	if(pControl == 'Liquidar'){
		if(vPosicion == 0 || (vValor.indexOf('Otros') != -1 && document.getElementById('frmDetalles:txtOtros').value == '')){
		vError.textContent ='Debe seleccionar persona que Recibe.';
		return false;
		}else{
			vError.textContent = '';
			vExiste = true;
		}
		if(vExiste){
			var vMontoCobrar = document.getElementById('frmDetalles:lblMontoCobrar').textContent;
			var vMontoTotal = document.getElementById('frmDetalles:lblMontoTotal').textContent;
			if(parseInt(vMontoTotal.substring(1, vMontoTotal.length)) > parseInt(vMontoCobrar.substring(1, vMontoCobrar.length))){
				var vDiferencia = parseFloat(vMontoTotal.substring(1, vMontoTotal.length)) - parseFloat(vMontoCobrar.substring(1, vMontoCobrar.length));
				if(!confirm('El monto a pagar tiene un excedente de $' + vDiferencia.toFixed(2) + ' con el adeudo de la orden. �Desea continuar con la liquidaci�n de la orden?')){
					return false;
				}
			}else if(parseInt(vMontoTotal.substring(1, vMontoTotal.length)) < parseInt(vMontoCobrar.substring(1, vMontoCobrar.length))){
				var vDiferencia = parseFloat(vMontoCobrar.substring(1, vMontoCobrar.length)) - parseFloat(vMontoTotal.substring(1, vMontoTotal.length));
				if(!confirm('El monto a pagar tiene un faltante de $' + vDiferencia.toFixed(2) + ' con el adeudo de la orden. �Desea continuar con la liquidaci�n de la orden?')){
					return false;
				}
			}else if(!confirm('�Desea liquidar el registro?')){
				return false;
			}
		}
	}else if(pControl == 'Cancelar'){
		if(!confirm('�Desea cancelar la liquidaci�n?')){
			return false;
		}else{
			var vPosicion= [document.getElementById('frmDetalles:cobTipoPago').options.selectedIndex
			                , document.getElementById('frmDetalles:cobBanco').options.selectedIndex
			                , document.getElementById('frmDetalles:cobQuienRecibe').options.selectedIndex];//Obtiene el index del combo				
			
			//Obtiene el texto del combo
			document.getElementById('frmDetalles:cobTipoPago').options[vPosicion[0]].value = 0;
			document.getElementById('frmDetalles:cobBanco').options[vPosicion[1]].value = 0;
			document.getElementById('frmDetalles:cobQuienRecibe').options[vPosicion[2]].value = 0;
			
			document.getElementById('frmDetalles:lblComentarios').value = '';
			document.getElementById('frmDetalles:txtFolioCobranza').value = '';
			document.getElementById('frmDetalles:txtFechaPago').value = '';
			document.getElementById('frmDetalles:txtMonto').value = '';
		}
	}
}

function validarLiquidacion(){
	var vMontoIngresado = document.getElementById('frmDetalles:txtMonto');
	var vMontoCobrar = document.getElementById('frmDetalles:lblMontoCobrar');
	var vFolioCobranza = document.getElementById('frmDetalles:txtFolioCobranza');
	var vFechaPago = document.getElementById('frmDetalles:txtFechaPago');
	var vError = document.getElementById('frmDetalles:lblMensajeError2');
	var vPosicionBanco = document.getElementById('frmDetalles:cobBanco').options.selectedIndex;  
	var vPosicionTipoPago = document.getElementById('frmDetalles:cobTipoPago').options.selectedIndex; 
	var vValorTipoPago = document.getElementById('frmDetalles:cobTipoPago').options[vPosicionTipoPago].text; 
	
	
	if(vPosicionTipoPago == 0){
		vError.textContent ='Debe ingresar un Tipo de Pago.';		
	}else if(vValorTipoPago.indexOf("Banco") != -1 && vPosicionBanco == 0){
		vError.textContent ='Debe ingresar un Banco.';
	}else if(vValorTipoPago.indexOf("Recibo") != -1 && vFolioCobranza.value == ""){
		vError.textContent ='Debe ingresar un Folio de Cobranza.';		
	}else if(vFechaPago.value == ""){
		vError.textContent ='Debe ingresar una Fecha.';
	}else if(vMontoIngresado.value == ""){
		vError.textContent ='Debe ingresar un Monto.';
	}else if((vValorTipoPago.indexOf("Banco") != -1 && vFolioCobranza.value != "") || (vValorTipoPago.indexOf("Recibo") != -1 && vPosicionBanco > 0)){
		vError.textContent ='Verifique el tipo de pago.';
	//}else if(parseFloat(vMontoIngresado.value) > parseFloat(vMontoCobrar.textContent.replace("$", ""))){
	//	vError.textContent ='El monto agregado no puede ser mayor al monto de la orden';
	}else if(parseFloat(vMontoIngresado.value) <= 0){
		vError.textContent ='El monto ingresado debe ser mayor a cero.';
	}else{
		return true;
	}
	return false;
}

//Ordenamientod de tablas para las columnas Registro y Nombre
var sortedOn = -1;//indica la posicion de la columna que va a ordenar
var _arregloCajas;
var _arregloUnitarios;
var _ordenaAsc = false;
var _ordenaDesc = false;

function SortTable(pTabla, sortOn, nombreColumna) {
/*	frmGridDetalleRepresentante:dtgDetalleRepresentante
	Registro posicion 1
	Nombre posicion 2
	frmDetalles:dtgCajas
	Registro posicion 0
	Nombre posicion 1
 * */
	var _posicionRegistro;
	var _posicionNombre;
	var table = document.getElementById(pTabla);
	var tbody = table.getElementsByTagName('tbody')[0];
	var rows = tbody.getElementsByTagName('tr');
	var rowArray = new Array();
	
	for (var i=0, length=rows.length; i<length; i++) {
		rowArray[i] = rows[i].cloneNode(true);
	}
	
	if (sortOn == sortedOn) {
		rowArray.reverse();
		sortedOn = -1;
		//Asigna la imagen correspondiente al ordenamiento seleccionado
		if(pTabla == 'frmBuscarRepresentante:dtgDetalleRepresentante'){
			_posicionRegistro = 3;
			_posicionNombre = 4;
		}
		else if(pTabla == 'frmGridDetalleRepresentante:dtgDetalleRepresentante'){
			_posicionRegistro = 1;
			_posicionNombre = 2;
		}
		else{
			_posicionRegistro = 0;
			_posicionNombre = 1;
		}
		//Muestra la imagen depediendo la seleccion
		if(nombreColumna == 'Registro'){
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[2].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[2].style.visibility = "visible";
		}else{
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[2].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[2].style.visibility = "visible";
		}
	}
	else {
		sortedOn = sortOn;
		if (nombreColumna == 'Registro') {
			rowArray.sort(RowCompareNumbers);
		}
		else {
			rowArray.sort(RowCompare);
		}
		
		if(pTabla == 'frmBuscarRepresentante:dtgDetalleRepresentante'){
			_posicionRegistro = 3;
			_posicionNombre = 4;
		}
		else if(pTabla == 'frmGridDetalleRepresentante:dtgDetalleRepresentante'){
			_posicionRegistro = 1;
			_posicionNombre = 2;
		}
		else{
			_posicionRegistro = 0;
			_posicionNombre = 1;
		}
		//Muestra la imagen depediendo la seleccion
		if(nombreColumna == 'Registro'){
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[2].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[0].style.visibility = "visible";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[2].style.visibility = "hidden";
		}else{
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[0].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionRegistro].firstChild.childNodes[2].style.visibility = "hidden";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[0].style.visibility = "visible";
			table.tHead.childNodes[0].childNodes[_posicionNombre].firstChild.childNodes[2].style.visibility = "hidden";
		}		
	}

	if(pTabla == 'frmDetalles:dtgCajas'){
		_arregloCajas = rowArray;		
	}
	else if(pTabla == 'frmDetalles:dtgUnitarios'){
		_arregloUnitarios = rowArray;
	}
	var newTbody = document.createElement('tbody');
	for (var i=0, length=rowArray.length; i<length; i++) {
		newTbody.appendChild(rowArray[i]);
	}
	
	table.replaceChild(newTbody, tbody);
	return false;
}

function RowCompare(a, b) {

	var aVal = a.getElementsByTagName('td')[sortedOn].firstChild.textContent;
	var bVal = b.getElementsByTagName('td')[sortedOn].firstChild.textContent;
	return (aVal == bVal ? 0 : (aVal > bVal ? 1 : -1));
}

function RowCompareNumbers(a, b) {

	var aVal = parseInt(a.getElementsByTagName('td')[sortedOn].firstChild.textContent);
	var bVal = parseInt(b.getElementsByTagName('td')[sortedOn].firstChild.textContent);
	return (aVal - bVal);
}

/**
 * Valida que el usuario ingreso solo numeros y ejecute la accion del boton
 * @param e
 * @returns {Boolean}
 */
function liquidacionRepartoWebKeyPress(e, pControl) {
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == 13) {
		if (pControl == 'Consultar') {
			document.getElementById('frmLiquidacionRepartoWeb:btn' + pControl).click();	
		} else {
			return validaCodigoLeido(pControl);	
		}
	} else {
		return validaNumeros(e); //Valida numeros y retroceso
	}
}

/**
 * Obtiene la lista de rutas por zona.
 * @param e
 * @returns {Boolean}
 */
function obtenerListaRutasPorZona(e, pControl) {
	if (pControl == 'ObtenerListaRutas') {
		document.getElementById('frmLiquidacionRepartoWeb:btn' + pControl).click();	
	}
}

/**
 * Muestra u oculta el combo Quien Informa
 * @param e
 * @param pControl
 */
function cambiaRazonRepartoWeb() {
	document.getElementById('frmLiquidacionRepartoWeb:btnValidarRazonesDevolucion').click();
}

function liqRepWebAceptarDetalle() {
	if (confirm("\u00BFEst\u00E1 seguro de guardar informaci\u00F3n de la orden?")) {
		if (document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != "0") {
			document.getElementById("frmLiquidacionRepartoWeb:btnDevolverOrden").click();
		} else {
			document.getElementById("frmLiquidacionRepartoWeb:btnLiquidarOrdenPago").click();
		}
	}
	liqRepWebOnChangeCambiaRazon();
}

function lrwValidarItemsSeleccionados() {
	return "";
}

function eliminarTiposDePago() {
	if (document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != "0") {
		document.getElementById('frmLiquidacionRepartoWeb:btnEliminarTodosLosPagos').click();
	} else {
		liqRepWebOnChangeCambiaRazon();
	}
}

function liqRepWebHabilitarTiposPagos() {
	document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = false;
	document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = false;
	document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = false;
	document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = false;
	document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = false;
}

function liqRepWebOnChangeCambiaRazon2() {
	if (document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != "0") {
		document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:checkHijackedCash').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = true;
		if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = false;
		}
	} else {
		document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = false;
		document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = false;
		document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = false;
		document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = false;
		document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = false;
		document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = false;
		if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = true;
		}
	}
	document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').disabled = false;
	liquidacionRepartoWebOnChangeTipoPago2();
	if (document.getElementById('frmLiquidacionRepartoWeb:deshabilitarEdicionCampo').value == "true"
		|| document.getElementById('frmLiquidacionRepartoWeb:deshabilitarEdicionCampo').value == true) {
		document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = true;
		document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = true;
		if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = true;
		}
	}
	onchangeMontoLiqRepWeb();
	validacionRoboCajasLiqRepWeb();
}

function validacionRoboCajasLiqRepWeb() {
	// Validacion robo cajas
	var varValorExistenPagos = document.getElementById('frmLiquidacionRepartoWeb:valorExistenPagos');
	if ((varValorExistenPagos == undefined || varValorExistenPagos == null || varValorExistenPagos.value == false || varValorExistenPagos.value == "false")) {
		if (document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas') != undefined && document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas') != null) {
			document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').disabled = false;			
		}
	} else {
		if (document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas') != undefined && document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas') != null) {
			document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').checked = false;
		}
	}
}

function liquidacionRepartoWebOnChangeTipoPago2() {
	if (document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago') != null) {
		var numeroOpcion = document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').options.selectedIndex;
		var tipoPago = document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').options[numeroOpcion].text;
		if (tipoPago.indexOf('Banco') > -1) {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
		} else if (tipoPago.indexOf('Recibo de cobranza') > -1) {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = false;
		} else if (tipoPago.indexOf('Pago en efectivo') > -1) {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
		} else {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
		}
	}
}

function liqRepWebOnChangeCambiaRazon() {
	onchangeMontoLiqRepWeb();
	if (document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion') != null
			&& document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != null
			&& document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != undefined) {
		if (document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value != "0") {
			/** DEVOLUCION */
			//Se limpian los controles
			document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').value = "0";
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').value = "0";
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').value = "";
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').value = "0";
			document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value = "";
			document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').value = "";
			document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:checkHijackedCash').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').disabled = false;
			if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
				document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = false;
			}
		} else {
			/** ENTREGA */
			document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:checkRoboCajas').disabled = true;
			if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
				document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = true;
			}
		}
		liquidacionRepartoWebOnChangeTipoPago();
		if (document.getElementById('frmLiquidacionRepartoWeb:deshabilitarEdicionCampo').value == "true"
			|| document.getElementById('frmLiquidacionRepartoWeb:deshabilitarEdicionCampo').value == true) {
			document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:txtFechaPago').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfMonto').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:cbQuienRecibe').disabled = true;
//			document.getElementById('frmLiquidacionRepartoWeb:taComentarios').disabled = true;
			if (document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion') != null) {
				document.getElementById('frmLiquidacionRepartoWeb:cbQuienInformaDevolucion').disabled = true;
			}
		}
	}
//	if (document.getElementById("frmLiquidacionRepartoWeb:valorExistenPagos") != null
//			&& document.getElementById("frmLiquidacionRepartoWeb:valorExistenPagos") != "undefined"
//			&& document.getElementById("frmLiquidacionRepartoWeb:valorExistenPagos").value != "undefined") {
//		if (document.getElementById("frmLiquidacionRepartoWeb:valorExistenPagos").value == "true" || document.getElementById("frmLiquidacionRepartoWeb:valorExistenPagos").value == true) {
//			// Si existen pagos
//			document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').disabled = true;
//		} else {
//			// No existen pagos
//			if (document.getElementById('frmLiquidacionRepartoWeb:deshabilitarDevolucionesSegLiq').value != "true"
//					&& document.getElementById('frmLiquidacionRepartoWeb:deshabilitarDevolucionesSegLiq').value != true) {
//				document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').disabled = false;
//			} else {
//				document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').disabled = true;
//				document.getElementById('frmLiquidacionRepartoWeb:cbRazonesDevolucion').value = "0";
//				document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').disabled = false;
//				liqRepWebOnChangeCambiaRazon();
//			}
//		}
//	}
	onchangeMontoLiqRepWeb();
	validacionRoboCajasLiqRepWeb();
}

function validarRegresoAceptarDetalle() {
//	alert("Se permite el alta: " + document.getElementById('frmLiquidacionRepartoWeb:permitirAceptarOrden').value);
	if (document.getElementById('frmLiquidacionRepartoWeb:permitirAceptarOrden') == undefined
			|| document.getElementById('frmLiquidacionRepartoWeb:permitirAceptarOrden') == null
			|| document.getElementById('frmLiquidacionRepartoWeb:permitirAceptarOrden').value == false
			|| document.getElementById('frmLiquidacionRepartoWeb:permitirAceptarOrden').value == "false") {
		Richfaces.showModalPanel('modPanRepLiqRepWebAceptarNo');
	} else {
		Richfaces.showModalPanel('modPanRepLiqRepWebAceptar');
	}
}

/**
 * Habilita y deshabilita componentes dependiendo del tipo de pago
 */
function liquidacionRepartoWebOnChangeTipoPago() {
	if (document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago') != null) {
		var numeroOpcion = document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').options.selectedIndex;
		var tipoPago = document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').options[numeroOpcion].text;
		document.getElementById('frmLiquidacionRepartoWeb:cbBanco').value = 0;
		document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').value = "";
		if (document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').value == "1") {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = false;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
			habilitarSecuenciaEfectivo(false);
		} else if (document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').value == "2") {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = false;
			habilitarSecuenciaEfectivo(false);
		} else if (document.getElementById('frmLiquidacionRepartoWeb:cbTipoDePago').value == "3") {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
			habilitarSecuenciaEfectivo(true);
		} else {
			document.getElementById('frmLiquidacionRepartoWeb:cbBanco').disabled = true;
			document.getElementById('frmLiquidacionRepartoWeb:tfFolioCobranza').disabled = true;
			habilitarSecuenciaEfectivo(false);
		}
	} else {
		habilitarSecuenciaEfectivo(false);
	}
}

function hacerFocoElementoLiqRepWeb(idElemento) {
	if (document.getElementById("frmLiquidacionRepartoWeb:" + idElemento) != null && document.getElementById("frmLiquidacionRepartoWeb:" + idElemento) != "undefined") {
		document.getElementById("frmLiquidacionRepartoWeb:" + idElemento).focus();
	}
}

function habilitarSecuenciaEfectivo(esEfectivo) {
	var existePagoEfectivo = false;
	if (document.getElementById("frmLiquidacionRepartoWeb:valorNoExistePagoEfectivo").value == "true" || document.getElementById("frmLiquidacionRepartoWeb:valorNoExistePagoEfectivo").value == true) {
		existePagoEfectivo = false;
	} else {
		existePagoEfectivo = true;
	}
	if (!existePagoEfectivo) {
		if (esEfectivo == true || esEfectivo == "true") {
			document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").disabled = "";
			document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").style.visibility = "";
			document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").disabled = "";
			document.getElementById("frmLiquidacionRepartoWeb:lblCashSequence").style.visibility = "";
		} else {
			document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").disabled = "true";
			document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").style.visibility = "hidden";
			document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").disabled = "true";
			document.getElementById("frmLiquidacionRepartoWeb:lblCashSequence").style.visibility = "hidden";
		}
		//
		document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").checked = false;
		document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").disabled = "true";
	} else {
		document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").disabled = "";
		document.getElementById("frmLiquidacionRepartoWeb:tfCashSequence").style.visibility = "";
		document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").disabled = "";
		document.getElementById("frmLiquidacionRepartoWeb:lblCashSequence").style.visibility = "";
		//
		document.getElementById("frmLiquidacionRepartoWeb:checkHijackedCash").disabled = "";
	}
}

function validaMontoCapturado() {
	
	var diferencia = 0;
	var montoCapturado = 0;
	var montoCobrar = Number(document.getElementById("frmPagoCampaniaAnterior:TxtMontoaCobrar").value.substring(2));
	var tabla = document.getElementById("frmPagoCampaniaAnterior:GrdPagos");
	
	if(tabla.rows[1].cells.length > 1 ||
			tabla.rows[1].cells.length == undefined) {
		
		for(var i = 1; i < tabla.rows.length; i++) {
			montoCapturado += Number(tabla.rows[i].cells[4].innerText.substring(2));
		}
		
		diferencia = montoCobrar - montoCapturado;
		
		if(diferencia > 1 || diferencia < -1) {
			if(!confirm("Existe una diferencia de $" 
					+ Number(diferencia).toFixed(2) + " entre el monto registrado y el monto requerido. \u00BFDesea continuar?")) {
				return false;
			} else {
				document.getElementById("frmPagoCampaniaAnterior:BtnGuardar").click();
				return true;
			}
		} else {
			document.getElementById("frmPagoCampaniaAnterior:BtnGuardar").click();
			return true;
		}
		
	} else {
		
		if (montoCobrar > 0) {
			alert("Se debe capturar al menos un pago.");
			return false;
		}
		
	}
}

function limpiaCampo(idCampo) {
	document.getElementById(idCampo).value = "";
}

function numeroDecimal(e) {
	var k;
	document.all ? k = e.keyCode : k = e.which;
	return (k == 8 || k == 0 || (k >= 48 && k <= 57) || k == 46);
}

function limpiaNumeroDecimal(idElemento) {
	var regexp = /^\d+(\.\d{0,2})?$/;
	var val = document.getElementById(idElemento).value;
	if (val.length > 0) {
		if (!regexp.test(val)) {
			document.getElementById(idElemento).value = '';
			alert("Valor incorrecto, favor de capturar una cantidad.");
			return false;
		}
	} 
}

function lrwConfLiq() {
	var remanente = document.getElementById('frmLiquidacionRepartoWeb:hidRemanente').value;
	if (remanente < 1.0) {
		document.getElementById('frmLiquidacionRepartoWeb:btnLiquidarOrdenPago').click();
	} else {
		if (confirm('Existe un remanente de ' + remanente + '. \u00BFDesea liquidar la orden?')) {
			document.getElementById('frmLiquidacionRepartoWeb:btnLiquidarOrdenPago').click();
		}
	}
}


/************************** ENTREGA DE FALTANTES ******************************/

/**
 * Valida que el usuario ingreso solo numeros y ejecute la accion del boton
 * @param e
 * @returns {Boolean}
 */
function entregaFaltantesKeyPress(e, pControl) {
	entregaFaltantesOnChangeAccount();
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == 13) {
		if (pControl == 'Consultar') {
			document.getElementById('frmEntregaFaltantes:btn' + pControl).click();	
		} else {
			return validaCodigoLeido(pControl);	
		}
	} else {
		return validaNumeros(e); //Valida numeros y retroceso
	}
}

function entregaFaltantesOnChangeAccount() {
	document.getElementById('frmEntregaFaltantes:tfNombre').value="";
}

function entregaFaltantesOnChangeNombre() {
	document.getElementById('frmEntregaFaltantes:tfRegistro').value="";
}

function resaltarFondoRowEntFal(row, color) {
	row.bgColor = color;
}

function borrarPalomitas() {
	document.getElementById('frmEntregaFaltantes:codCorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codCorrectoPremio').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoPremio').style.visibility = "hidden";
}

var entFalM3 = "El c\u00F3digo <CODIGO> ingresado no corresponde a ninguno de la lista.";

function entFalCodigoBarras() {
	document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = "";
	var cantidadDeCajas = parseInt(document.getElementById('frmEntregaFaltantes:cantidadCajas').value);
	for (var i = 0; i < cantidadDeCajas; i++) {
		resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblCajas').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "");
	}
	var cantidadDePremios = parseInt(document.getElementById('frmEntregaFaltantes:cantidadPremios').value);
	for (var i = 0; i < cantidadDePremios; i++) {
		resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblPremiosUnitarios').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "");
	}
	document.getElementById('frmEntregaFaltantes:codCorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codCorrectoPremio').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoPremio').style.visibility = "hidden";
	var cajaOk = false;
	if (document.getElementById('frmEntregaFaltantes:tfCodigoBarras').value != "") {
		var codigoBarras = document.getElementById('frmEntregaFaltantes:tfCodigoBarras').value;
		document.getElementById('frmEntregaFaltantes:tfCodigoBarras').value = "";
		for (var i = 0; i < cantidadDeCajas; i++) {
			if (document.getElementById("frmEntregaFaltantes:tblCajas:" + i + ":codigoBarrasCaja").value == codigoBarras) {
				document.getElementById("frmEntregaFaltantes:tblCajas:" + i + ":caja").checked = true;
				resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblCajas').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "#FBD6EA");
				cajaOk = true;
				break;
			}
		}
		if (cajaOk) {
			document.getElementById('frmEntregaFaltantes:codCorrectoCaja').style.visibility = "";
			document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = "";
		} else {
			document.getElementById('frmEntregaFaltantes:codIncorrectoCaja').style.visibility = "";
			document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = entFalM3.replace("<CODIGO>", codigoBarras);
		}
	}
}

function entFalCodigo() {
	document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = "";
	var cantidadDeCajas = parseInt(document.getElementById('frmEntregaFaltantes:cantidadCajas').value);
	for (var i = 0; i < cantidadDeCajas; i++) {
		resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblCajas').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "");
	}
	var cantidadDePremios = parseInt(document.getElementById('frmEntregaFaltantes:cantidadPremios').value);
	for (var i = 0; i < cantidadDePremios; i++) {
		resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblPremiosUnitarios').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "");
	}
	document.getElementById('frmEntregaFaltantes:codCorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoCaja').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codCorrectoPremio').style.visibility = "hidden";
	document.getElementById('frmEntregaFaltantes:codIncorrectoPremio').style.visibility = "hidden";
	var premioOk = false;
	if (document.getElementById('frmEntregaFaltantes:tfCodigo').value != "") {
		var codigo = document.getElementById('frmEntregaFaltantes:tfCodigo').value;
		document.getElementById('frmEntregaFaltantes:tfCodigo').value = "";
		for (var i = 0; i < cantidadDePremios; i++) {
			if (document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":idPremioFSC").value == codigo
					|| document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":idPremioEAN13").value == codigo) {
				if (parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":cantidadFacturadosPremio").value) > parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value)) {
					document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value = (parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value) + 1) + "";
					resaltarFondoRowEntFal(document.getElementById('frmEntregaFaltantes:tblPremiosUnitarios').getElementsByTagName('tbody')[0].getElementsByTagName('tr')[i], "#FBD6EA");
					premioOk = true;
				} else {
					premioOk = false;
					alert('No se puede ingresar m\u00E1s de la cantidad facturada');
				}
				break;
			}
		}
		if (premioOk) {
			document.getElementById('frmEntregaFaltantes:codCorrectoPremio').style.visibility = "";
			document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = "";
		} else {
			document.getElementById('frmEntregaFaltantes:codIncorrectoPremio').style.visibility = "";
			document.getElementById('frmEntregaFaltantes:lblErrorCodigos').innerHTML = entFalM3.replace("<CODIGO>", codigo);
		}
	}
	
	validarEntregasPremios();
}

function validarEntregasPremios() {
	var cantidadDePremios = parseInt(document.getElementById('frmEntregaFaltantes:cantidadPremios').value);
	for (var i = 0; i < cantidadDePremios; i++) {
		if (parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value) > parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":cantidadFacturadosPremio").value)) {
			document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value = parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":cantidadFacturadosPremio").value);
		} else if (parseInt(document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value) < 0) {
			document.getElementById("frmEntregaFaltantes:tblPremiosUnitarios:" + i + ":premio").value = 0;
		}
	}
}


/**
 * Valida que el usuario ingreso solo numeros y ejecute la accion del boton
 * @param e
 * @returns {Boolean}
 */
function remitosVentanillaKeyPress(e) {
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == 13) {
		document.getElementById("formRemitosVentanilla:btnConsultarRegistroRem").click();
		return false;
	} else {
		return validaNumeros(e); //Valida numeros y retroceso
	}
}

function validarAccount() {
	var cantidadRepresentantes = parseInt(document.getElementById('formRemitosVentanilla:cantidadRepresentantes').value);
	var registro = document.getElementById("formRemitosVentanilla:tfRegistro").value;
	document.getElementById("formRemitosVentanilla:tfRegistro").value = "";
	var seEncontro = false;
	for (var i = 0; i < cantidadRepresentantes; i++) {
		if (document.getElementById("formRemitosVentanilla:tblRepresentantes:" + i + ":ihAccount").value == registro) {
			seEncontro = true;
			document.getElementById("formRemitosVentanilla:tblRepresentantes:" + i + ":ihAccount").click();
			document.getElementById("formRemitosVentanilla:btnDetalleRemito").click();
			break;
		}
	}
	if (seEncontro == false) {
		alert("No se encontr\u00F3 remito para la representante indicada: " + registro);
	}
}

function validaCantRecol(e) {
	return validaNumeros(e);
}

function validarRecoleccion() {
	var causaNoRecoleccion = document.getElementById('formRemitosVentanilla:cbCausasNoRecoleccion').value;
	if (causaNoRecoleccion != null && causaNoRecoleccion != "0") {
		document.getElementById('formRemitosVentanilla:cantidadRecolectados').value = "0";
		document.getElementById('formRemitosVentanilla:taCommentsRecol').value = "";
		document.getElementById('formRemitosVentanilla:cantidadRecolectados').disabled = "disabled";
		document.getElementById('formRemitosVentanilla:taCommentsRecol').disabled = "disabled";
	} else {
		document.getElementById('formRemitosVentanilla:cantidadRecolectados').disabled = "";
		document.getElementById('formRemitosVentanilla:taCommentsRecol').disabled = "";
	}
	var cantidadARecolectar = parseInt(document.getElementById('formRemitosVentanilla:ihCantidadARecolectar').value);
	var cantidadRecolectada = parseInt(document.getElementById('formRemitosVentanilla:cantidadRecolectados').value);
	if (cantidadRecolectada != null && cantidadRecolectada > 0) {
		if (cantidadRecolectada > cantidadARecolectar) {
			document.getElementById('formRemitosVentanilla:cantidadRecolectados').value = cantidadARecolectar;
			alert("Por favor captura la cantidad a recibir de manera correcta(mayor a cero y menor o iguala cantidad a recolectar " + cantidadARecolectar + ")");
		}
	}
}

function limitTextArea(element, limit) {
	if (element.value.length > limit) {
		element.value = element.value.substring(0, limit);
	}
}

function ordenarTablaLiqRepWeb(valorOrd) {
	Richfaces.showModalPanel('mp');
	//ordenamientoJavascriptLiqRepWeb(valorOrd); //Javascript
	ordenamientoJavaLiqRepWeb(valorOrd); //Java
}

function ordenarTablaNumericamenteLiqRepWeb(valorOrd) {
	Richfaces.showModalPanel('mp');
	//ordenamientoJavascriptLiqRepWeb(valorOrd); //Javascript
	ordenamientoJavaNumericamenteLiqRepWeb(valorOrd); //Java
}

function ordenamientoJavaLiqRepWeb(valorOrd) {
	document.getElementById("frmLiquidacionRepartoWeb:valorOrdenamientoLiqRepWeb").value = "" + valorOrd;
	document.getElementById("frmLiquidacionRepartoWeb:btnOrdenarOrdenes").click();
}

function oncompleteOrdenamientoJavaLiqRepWeb() {
	var esOrdenamiento = "";
	if (document.getElementById("frmLiquidacionRepartoWeb:valueEsOrdenamiento") != null && document.getElementById("frmLiquidacionRepartoWeb:valueEsOrdenamiento") != "undefined") {
		if (document.getElementById("frmLiquidacionRepartoWeb:valueEsOrdenamiento").value != null && document.getElementById("frmLiquidacionRepartoWeb:valueEsOrdenamiento").value != "undefined") {
			esOrdenamiento = document.getElementById("frmLiquidacionRepartoWeb:valueEsOrdenamiento").value;
		}
	}
	if (esOrdenamiento == 1 || esOrdenamiento == '1') {
		ocultarBotonesOrdenamientoLiqRepWeb();
		var valorOrd = document.getElementById("frmLiquidacionRepartoWeb:valorOrdenamientoLiqRepWeb").value;
		var tipoOrdenamiento = document.getElementById("frmLiquidacionRepartoWeb:tipoOrdenamientoLiqRepWeb").value;
		if (tipoOrdenamiento == "Asc") {
			if (document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Desc") != null
					&& document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Desc") != "undefined"
					&& document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Desc").value != "undefined") {
				document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Desc").style.visibility = "";
			}
		} else {
			if (document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc") != null
					&& document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc") != "undefined"
					&& document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc").value != "undefined") {
				document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc").style.visibility = "";
			}
		}
	}
}

function ordenamientoJavascriptLiqRepWeb(valorOrd) {
	ocultarBotonesOrdenamientoLiqRepWeb();
	document.getElementById("frmLiquidacionRepartoWeb:valorDeOrden").value = valorOrd;
	if (document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value == null
			|| document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value == "") {
		document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value = "Asc";
		document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc").style.visibility = "";
	} else {
		if (document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value == "Asc") {
			document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value = "Desc";
			document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Desc").style.visibility = "";
		} else {
			document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value = "Asc";
			document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:" + valorOrd + "Asc").style.visibility = "";
		}
	}
	ordenarTablaPorCampo(valorOrd, document.getElementById("frmLiquidacionRepartoWeb:tipoDeOrden").value);
}

function obtenerNumeroColumnaLiqRepWeb(nombreCampo) {
	var numeroColumna = -1;
	if (nombreCampo == "registro") {
		numeroColumna = 0;
	} else if (nombreCampo == "nombre") {
		numeroColumna = 1;
	} else if (nombreCampo == "rutasec") {
		numeroColumna = 2;
	} else if (nombreCampo == "bloq") {
		numeroColumna = 3;
	} else if (nombreCampo == "montoorden") {
		numeroColumna = 4;
	} else if (nombreCampo == "valormodif") {
		numeroColumna = 5;
	} else if (nombreCampo == "digitosmodif") {
		numeroColumna = 6;
	} else if (nombreCampo == "liquidacion") {
		numeroColumna = 7;
	} else if (nombreCampo == "tipopago") {
		numeroColumna = 8;
	} else if (nombreCampo == "liqbanco") {
		numeroColumna = 9;
	} else if (nombreCampo == "fechapago") {
		numeroColumna = 10;
	} else if (nombreCampo == "montopago") {
		numeroColumna = 11;
	} else if (nombreCampo == "devolucionMostrar") {
		numeroColumna = 12;
	} else if (nombreCampo == "cantidadItems") {
		numeroColumna = 13;
	} else if (nombreCampo == "cantidadCajas") {
		numeroColumna = 14;
	} else if (nombreCampo == "cantidadPU") {
		numeroColumna = 15;
	} else if (nombreCampo == "primeradevolucion") {
		numeroColumna = 16;
	} else if (nombreCampo == "comentario") {
		numeroColumna = 17;
	} else if (nombreCampo == "zona") {
		numeroColumna = 18;
	} else if (nombreCampo == "campania") {
		numeroColumna = 19;
	} else if (nombreCampo == "ordensegmentada") {
		numeroColumna = 20;
	}
	return numeroColumna;
}

function ordenarTablaPorCampo(nombreCampo, tipoOrdenamiento) {
	var numeroColumna = obtenerNumeroColumnaLiqRepWeb(nombreCampo);
	var tablaLiq = document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes");
	var cuerpoTablaLiq = tablaLiq.getElementsByTagName('tbody')[0];
	var filasTablaLiq = cuerpoTablaLiq.getElementsByTagName('tr');
	var huboCambio = false;
	var mostrar = "";
	mostrar += "numeroColumna: " + numeroColumna;
	mostrar += "tablaLiq: " + tablaLiq;
	mostrar += "cuerpoTablaLiq: " + cuerpoTablaLiq;
	mostrar += "filasTablaLiq: " + filasTablaLiq;
	//alert(mostrar);
	do {
		huboCambio = false;
		for (var i = 0; i < filasTablaLiq.length; i++) {
			if (i == 0) {
				continue;
			}
			var variableAnterior = filasTablaLiq[i - 1].childNodes[numeroColumna].childNodes[0].innerHTML.trim();
			var variableActual = filasTablaLiq[i].childNodes[numeroColumna].childNodes[0].innerHTML.trim();
			variableAnterior = variableAnterior.replace("$", "").trim();
			variableActual = variableActual.replace("$", "").trim();
			if (!isNaN(variableAnterior)
					&& !isNaN(variableActual)) {
				// Numerico
				var anterior = Number(variableAnterior);
				var actual = Number(variableActual);
				if ((tipoOrdenamiento == "Asc") && (anterior > actual)) {
					huboCambio = true;
					cambiarFilasLiq(i);
				} else if ((tipoOrdenamiento == "Desc") && (anterior < actual)) {
					huboCambio = true;
					cambiarFilasLiq(i);
				}
			} else {
				// Texto
				var anterior = variableAnterior;
				var actual = variableActual;
				if ((tipoOrdenamiento == "Asc") && (anterior > actual)) {
					huboCambio = true;
					cambiarFilasLiq(i);
				} else if ((tipoOrdenamiento == "Desc") && (anterior < actual)) {
					huboCambio = true;
					cambiarFilasLiq(i);
				}
			}
		}
	} while(huboCambio == true);
}

function ocultarBotonesOrdenamientoLiqRepWeb() {
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:registroNumericoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:registroNumericoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:nombreAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:nombreDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:rutaSecuenciaAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:rutaSecuenciaDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:rutaSosAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:rutaSosDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:bloqAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:bloqDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:montoOrdenAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:montoOrdenDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:valorModifAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:valorModifDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:digitosModifNumericoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:digitosModifNumericoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:liquidacionAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:liquidacionDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:liqbancoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:liqbancoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:fechaPagoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:fechaPagoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:quienRecibeAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:quienRecibeDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:montoPagadoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:montoPagadoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:devolucionMostrarAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:devolucionMostrarDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadItemsAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadItemsDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadCajasAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadCajasDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadPremiosUnitariosAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:cantidadPremiosUnitariosDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:primeraDevolucionAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:primeraDevolucionDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:comentarioAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:comentarioDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio1Asc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio1Desc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio2Asc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio2Desc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio3Asc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:domicilio3Desc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:estadoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:estadoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:seccionAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:seccionDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:descripcionZonaAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:descripcionZonaDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:campaniaAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:campaniaDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:ordenSegmentadaAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:ordenSegmentadaDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:roboAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:roboDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:secuenciaEfectivoAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:secuenciaEfectivoDesc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:remitosAsc").style.visibility = "hidden";
	document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes:remitosDesc").style.visibility = "hidden";
}

function cambiarFilasLiq(indice) {
	var tablaLiq = document.getElementById("frmLiquidacionRepartoWeb:tblOrdenes");
	var cuerpoTablaLiq = tablaLiq.getElementsByTagName('tbody')[0];
	var filasTablaLiq = cuerpoTablaLiq.getElementsByTagName('tr');	
	var filaTabla = filasTablaLiq[indice];
	var filaAnterior = filaTabla.previousElementSibling;
	var tablaLiq = filaTabla.parentNode;
	tablaLiq.insertBefore(filaTabla, filaAnterior);
}

function enviarLiquidacionesPendientes() {
	if (confirm("\u00BFDesea enviar las liquidaciones pendientes?")) {
		document.getElementById("frmLiquidacionRepartoWeb:btnEnviarLiqPendientes").click();
	}
}

function ponerFoco(idElemento) {
	document.getElementById("frmLiquidacionRepartoWeb:" + idElemento).focus();
}

function hacerClicBotonLiqRepWeb(e, idBoton) {
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == 13) {
		document.getElementById("frmLiquidacionRepartoWeb:" + idBoton).click();
	}
}

function liqRepWebOnchangeHijackedCash() {
	if (document.getElementById('frmLiquidacionRepartoWeb:btnOnChangeHijackedCash') != null || document.getElementById('frmLiquidacionRepartoWeb:btnOnChangeHijackedCash') != "undefined") {
		document.getElementById('frmLiquidacionRepartoWeb:btnOnChangeHijackedCash').click();
	}
}

function currencyFormatLiqRepWeb(num) {
    return "$" + num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}

function validaMontoLiqRepWeb(e, field, boton) {
	hacerClicBotonLiqRepWeb(e, boton);
	var key = e.keyCode || e.which;
	var especiales = null;
	if(window.event) // IE
	{
		especiales = [8, 46];//Caracter especial
	}
	else if(e.which >= 0) // Netscape/Firefox/Opera
	{
		especiales = [8, 39, 37, 46];//Caracter especial para validar retroceso, suprimir, flechas adelante y atras
	}
	var tecla = String.fromCharCode(key).toLowerCase();//Obtiene el valor de la tecla presionada
	var numeros = "0123456789";//Caracteres a validar
	
	var tecla_especial = false;//Variable para validar caracter especial
	//Ciclo para recorrer y validar caracteres especiales
    for(var i in especiales) {
    	if(key == especiales[i]) {
    		tecla_especial = true;
    		break;
		}
	}
    //Verifica que el caracter presionado sea valido
    if(numeros.indexOf(tecla) == -1 && !tecla_especial){
    	return false;	
    }else{
    	 if (key == 46){ 
    		 if (field.value == ""){ 
    			 return false; 
    		 }
    		 regexp = /^[0-9]+$/; 
    		 return regexp.test(field.value); 
    	 }
    }
}

function onchangeMontoLiqRepWeb() {
	if (document.getElementById('frmLiquidacionRepartoWeb:tfMonto') != null
    		&& document.getElementById('frmLiquidacionRepartoWeb:tfMonto') != "undefined") {
    	var valorMonto = document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value;
    	valorMonto = valorMonto.replace("$", "");
    	valorMonto = valorMonto.replace(",", "");
    	valorMonto = valorMonto.trim();
    	document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value = valorMonto;
    	if (document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value == "") {
    		document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value = "0";
    	}
    	document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value = currencyFormatLiqRepWeb(parseFloat(document.getElementById('frmLiquidacionRepartoWeb:tfMonto').value));
    }
}

function enrutadoSoloNumeros(e) {
	var tecla = (document.all) ? e.keyCode : e.which; //keyCode para IE which para Netscape/Firefox/Opera
	if (tecla == undefined || tecla == null) {
		return false;
	}
	// validaciones genericas
	if (tecla == 8 || tecla == 39 || tecla == 37 || tecla == 46) {
		return true;l
	}
	// aqui comienzan las validaciones
	if (tecla >= 48 && tecla <= 57) {
		return true;
	} else {
		return false;
	}
}

function enrutadoSoloNumerosOnChange(obj) {
	var cadenaFinal = "";
	for (var i = 0; i < obj.value.length; i++) {
		var asciiCode = obj.value.charCodeAt(i);
		if (asciiCode >= 48 && asciiCode <= 57) {
			cadenaFinal += obj.value.charAt(i);
		}
	}
	obj.value = cadenaFinal;
}
