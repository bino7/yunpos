package com.yunpos.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public final class AESUtils {

	private static final byte[] IV = "0908070605040302".getBytes();
	public static final String key = "xiaoniu@o2o.com";
	
	public static String encrypt(String raw){
		try {
			return encrypt(key,raw);
		} catch (Exception e) {
			e.printStackTrace();
			return raw;
		}
	}
	
	public static String decrypt(String encrypt){
		try {
			return decrypt(key, encrypt);
		} catch (Exception e) {
			e.printStackTrace();
			return encrypt;
		}
	}
	/**
	 * 
	 * @param key
	 * @param raw
	 * @return
	 */
	public static String encrypt(String key, String raw) {

		try {
			SecretKeySpec spec = createAESSecretKey(key);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivspec = new IvParameterSpec(IV);
			cipher.init(Cipher.ENCRYPT_MODE, spec, ivspec);
			return Base64.encodeBase64String(cipher.doFinal(raw.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("AES encrypt occur errors!", e);
		}
	}

	private static SecretKeySpec createAESSecretKey(String key) {
		byte[] result = new byte[16];
		byte[] bytes = key.getBytes();
		System.arraycopy(bytes, 0, result, 0, Math.min(bytes.length, 16));
		return new SecretKeySpec(result, "AES");
	}

	/**
	 * 
	 * @param key
	 * @param encrypt
	 * @return
	 */
	public static String decrypt(String key, String encrypt) {
		try {

			byte[] bytes = Base64.decodeBase64(encrypt);
			SecretKeySpec spec = createAESSecretKey(key);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivspec = new IvParameterSpec(IV);
			cipher.init(Cipher.DECRYPT_MODE, spec, ivspec);
			byte[] rawBytes = cipher.doFinal(bytes);
			return new String(rawBytes);
		} catch (Exception e) {
			throw new RuntimeException("AES decrypt occur errors!", e);
		}
	}

	private AESUtils() {}
}
