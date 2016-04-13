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

function validarNumeros(e) {
	var key;
	if (window.event) // IE
	{
		key = e.keyCode;
	} else if (e.which) // Netscape/Firefox/Opera
	{
		key = e.which;
	}

	if (key < 48 || key > 57) {
		return false;
	}
	return true;
}

function confirmaEliminarA() {
	if (confirm('¿Está seguro que desea eliminar el Reparto Especial seleccionado?')) {

		return true;
	} else {
		return false;
	}

}

function verificarMensajeNR() {
	var valor = document.getElementById("formNormal:valorNR").value;
	if (valor == "EVALUAR") {
		if (confirm("Corresponde a una orden NR, lo vas a sacar a reparto?")) {
			document.getElementById("formNormal:valorNR").value = "MANDAR";
		} else {
			document.getElementById("formNormal:valorNR").value = "NO MANDAR";
		}
	} else if (valor == "NO EVALUAR") {
		document.getElementById("formNormal:valorNR").value = "MANDAR";
	}
	document.getElementById("formNormal:btnConsultarRegistro2").click();

}

function cambiarValorNR() {

	document.getElementById("formNormal:valorNR").value = "aloja";

}
