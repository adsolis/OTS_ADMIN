function validarNumeroSecuencia() {

	var inputSecuenciaMaxima = document
			.getElementById("formRepresentantesCambiarSecuencia:numeroSecuenciaMaxima");
	var inputSecuenciaAnterior = document
			.getElementById("formEdicion:inputNuevaSecuencia");
	var secuenciaMaxima = parseInt(inputSecuenciaMaxima.value);
	var secuenciaAnterior = parseInt(inputSecuenciaAnterior.value);

	if (inputSecuenciaAnterior.value.length == 0) {
		alert('Favor de proporcionar el nuevo número de secuencia para la representante');
		inputSecuenciaAnterior.focus();
		return false;
	}
	if (isNaN(inputSecuenciaAnterior.value)) {
		alert('Favor de proporcionar el nuevo número de secuencia para la representante');
		inputSecuenciaAnterior.focus();
		return false;
	}
	if (inputSecuenciaAnterior.value == 0) {
		alert('Número de secuencia no valido. El número de secuencia debe ser mayor que cero');
		inputSecuenciaAnterior.focus();
		return false;
	}
	if (secuenciaAnterior > secuenciaMaxima) {
		alert('Número de secuencia no valido. Valor superior a la secuencia que se tiene como máximo para esta ruta');
		inputSecuenciaAnterior.focus();
		return false;
	}
}

function confirmarRegistros() {

	var select = document.getElementById("formRepresentantes:CbxRutas");
	var idRuta = select.selectedIndex;
	var claveRuta = select.options[idRuta].text;
	if (idRuta > 0) {
		if (!confirm('Se asignará la ruta "' + claveRuta
				+ '" a las Representantes seleccionadas, ¿Desea continuar?')) {
			return false;
		}
	} else {
		alert("Por favor, seleccione representante(s) y/o ruta");
		return false;
	}
}

function obtenerCheckBoxSeleccionados() {

	var node_list = document.getElementsByTagName('input');
	var noChecked = 0;

	for (var i = 0; i < node_list.length; i++) {
		var node = node_list[i];
		if (node.getAttribute('type') == 'checkbox') {
			if (node.checked) {
				noChecked = noChecked + 1;
			}
		}
	}

	if (noChecked > 0) {
		return confirmarRegistros();
	} else {
		alert("Por favor, seleccione representante(s) y/o ruta");
		return false;
	}
}

function confirmarRegistrosCambiarSecuencia() {
	if (!confirm('Se actualizara la secuencia de las representantes, ¿Desea continuar?')) {
		return false;
	}
}