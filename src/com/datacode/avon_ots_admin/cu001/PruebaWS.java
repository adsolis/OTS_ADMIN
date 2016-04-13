package com.datacode.avon_ots_admin.cu001;

import java.rmi.RemoteException;

import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.BO_CU001Stub;

public class PruebaWS {
	
	public static void main(String args[]) {
		String url = "http://localhost:8080/axis2/services/BO_CU001.BO_CU001HttpSoap12Endpoint/";
		System.out.println(url);
		url = Utils.modificarUrlServicioWeb(url);
		System.out.println(url);
	}
}
