package com.datacode.avon_ots_admin.replicacion;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilRest {
	private static Logger logger = Logger.getLogger(UtilRest.class);
	@SuppressWarnings({  "rawtypes" })
	public static Object procesaMapRespuesta (HttpResponse response, Class claseRes)
			throws IOException {
		
		String json;
		
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new IOException("Error en servicio REST:" + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
		}
		
		json = inputString(response.getEntity().getContent());		
		logger.info("RESPUESTA:" + json);
		try {
			return mapRespuesta(json, claseRes);
		} catch (Exception e) {
			throw new IOException();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mapRespuesta(String res, Class claseRes)
			throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(res, claseRes);

	}

	public static String toJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			return "";
		}
	}

	private static String inputString(InputStream io) throws IOException {
		StringBuffer builder = new StringBuffer();
		byte in[] = new byte[4096];
		int tam = 0;
		do {
			tam = io.read(in);
			if (tam > 0) {
				builder.append(new String(in, 0, tam, "UTF-8"));
			}
		} while (tam > 0);
		return builder.toString();
	}
}
