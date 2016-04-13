package com.datacode.avon_ots_admin.replicacion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;



public class EncriptadorDES {
	byte[] buf = new byte[1024];
	byte[] bufback = new byte[1024];
	byte b = 5;
	Cipher ecipher;
	Cipher dcipher;

	public EncriptadorDES(SecretKey key) throws Exception {
		byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
				0x72, 0x6F, 0x5A };
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	}

	public void encrypt(InputStream in, OutputStream out) throws Exception {
		//out = new CipherOutputStream(out, ecipher);

		int numRead = 0;
		while ((numRead = in.read(buf)) >= 0) {
			int j = 0;
			for (int i = buf.length - 1; i >= 0 ; i--) {
				
				bufback[i] = (byte) (buf[i] + b);

			}
			out.write(bufback, 0, numRead);
		}
		out.close();
		in.close();
	}

	public void decrypt(InputStream in, OutputStream out) throws Exception {
	//	in = new CipherInputStream(in, dcipher);

		int numRead = 0;
		while ((numRead = in.read(buf)) >= 0) {
			int j = 0;
			for (int i = buf.length - 1; i >= 0 ; i--) {
				bufback[i] = (byte) (buf[i] - b);
			}	
			out.write(bufback, 0, numRead);
		}
		out.close();
		in.close();
	}

	public static void main(String[] argv) throws Exception {
		SecretKey key = KeyGenerator.getInstance("DES").generateKey();
		key.getAlgorithm();
		
		EncriptadorDES encrypter = new EncriptadorDES(key);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		encrypter.encrypt(new ByteArrayInputStream("server2".getBytes()), out);
		System.out.println(new String(out.toByteArray()));
		ByteArrayOutputStream dest = new ByteArrayOutputStream();
		encrypter.decrypt(new ByteArrayInputStream(out.toByteArray()), dest);
		
		String res = new String(dest.toByteArray());
		System.out.println(res);
	}
}
