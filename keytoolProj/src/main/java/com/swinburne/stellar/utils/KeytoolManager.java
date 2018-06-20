package com.swinburne.stellar.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeytoolManager {
	KeyStore ks = null;
	String keyStorePassword = "filepass2018";
	String keypass = "entrypass2018";
	javax.crypto.SecretKey mySecretKey;
	KeyStore.PrivateKeyEntry pkEntry ;
	KeyStore.ProtectionParameter protParam;
	KeyStore.ProtectionParameter keyProtParam;
	java.security.cert.Certificate fileCert;
	
	public KeytoolManager() {
		protParam = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());
		keyProtParam = new KeyStore.PasswordProtection(keypass.toCharArray());

	}
	
	public static void main(String[] args) {
		KeytoolManager ktm =  new KeytoolManager();
		
		try {
			ktm.loadJKSFile();
//ktm.addToken("keyval" , 
//		ktm.keyStorePassword, "key30");
			ktm.readToken("Key30");
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadJKSFile() throws KeyStoreException {
		ks = KeyStore.getInstance("JCEKS");

		// get user password and file input stream
		char[] password = getPassword();

		java.io.FileInputStream fis = null;
		try {
			fis = new java.io.FileInputStream(".\\keytoolProj\\src\\main\\resources\\myjks.jks");
			ks.load(fis, password);
			
			
			pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("key1", keyProtParam);
			fileCert = pkEntry.getCertificate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private char[] getPassword() {
		return "canvas2018".toCharArray();
	}
	
	private Key readToken(String alias) {
		Key retToken = null;
		
		try {
			retToken = ks.getKey(alias, this.keypass.toCharArray());
			System.out.println(new String(retToken.getEncoded()));
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return retToken;
	}


	private void addToken(String token, String tokenPass, String secretKeyAlias) {


		
		try {
//			ks.setEntry(secretKeyAlias, pkEntry, keyProtParam);
			SecretKey mySecretKey = new SecretKeySpec(token.getBytes(), 0,
					token.length(), "DES");

			  KeyStore.SecretKeyEntry skEntry =

			  new KeyStore.SecretKeyEntry(mySecretKey);

			  ks.setEntry(secretKeyAlias, skEntry,keyProtParam);
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// store away the keystore
		java.io.FileOutputStream fos = null;
		try {
			fos = new java.io.FileOutputStream(".\\\\keytoolProj\\\\src\\\\main\\\\resources\\\\myjks.jks");
			ks.store(fos, tokenPass.toCharArray());
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
