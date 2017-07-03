package com.example.json.dingwei.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PreferenceUtil {

	public static final String PROFILE_NAME =  "profile_name ";

	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String SECRET_KEY_HASH_TRANSFORMATION = "SHA-256";
	private static final String CHARSET = "UTF-8";
	private static final String DEFAULT_VALUE = "OKtHovhloHqi7s0Tm6tjE9cRTvpzTNiT";

	public static final int MODE_ENCRYPT_NONE = 0;
	public static final int MODE_ENCRYPT_ALL = 1;
	public static final int MODE_ENCRYPT_PARTIAL = 2;
	
	private final static String STORAGE_ENCRYPTED_KEY = "yi_moments_encrypted_key";

	private Cipher cipherWriter;
	private Cipher cipherReader;

	private static PreferenceUtil mInstance;

	private int mEncryptMode;
	private SharedPreferences mPref;

	private PreferenceUtil(Context context, int encryptMode) {
		this.mEncryptMode = encryptMode;
		this.mPref = context.getSharedPreferences(PROFILE_NAME, Context.MODE_PRIVATE);

		if(encryptMode == MODE_ENCRYPT_ALL){
			initCiphers();
		}
	}

	public static void initInstance(Context context) {
		initInstance(context, MODE_ENCRYPT_NONE);
	}

	public synchronized static void initInstance(Context context, int encryptMode) {
		if(mInstance == null){
			mInstance = new PreferenceUtil(context, encryptMode);
		}
	}

	public static PreferenceUtil getInstance() {
		return mInstance;
	}


	public void putString(String key, String value) {
		String convertValue = value;
		if(mEncryptMode == MODE_ENCRYPT_ALL) {
			convertValue = encrypt(value);
		}
		if(convertValue != null) {
			Editor editor = mPref.edit();
			editor.putString(key, convertValue);
			editor.commit();
		}
	}

	public void putLong(String key, long value) {
		if(mEncryptMode == MODE_ENCRYPT_ALL){
			putString(key, String.valueOf(value));
		}else {
			Editor editor = mPref.edit();
			editor.putLong(key, value);
			editor.commit();
		}
	}

	public void putInt(String key, int value) {
		if(mEncryptMode == MODE_ENCRYPT_ALL){
			putString(key, String.valueOf(value));
		}else {
			Editor editor = mPref.edit();
			editor.putInt(key, value);
			editor.commit();
		}
	}

	public void putBoolean(String key, boolean value) {
		if(mEncryptMode == MODE_ENCRYPT_ALL){
			putString(key, String.valueOf(value));
		}else {
			Editor editor = mPref.edit();
			editor.putBoolean(key, value);
			editor.commit();
		}
	}

	public String getString(String key) {
		return getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		if(mEncryptMode != MODE_ENCRYPT_ALL)
			return mPref.getString(key, defaultValue);

		String value = mPref.getString(key, DEFAULT_VALUE);
		if(value.equals(DEFAULT_VALUE))//not exist
			return defaultValue;
		String retValue = decrypt(value);
		if(retValue == null)
			retValue = defaultValue;
		return retValue;
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	public int getInt(String key, int defaultValue) {
		if(mEncryptMode != MODE_ENCRYPT_ALL)
			return mPref.getInt(key, defaultValue);

		String value = mPref.getString(key, DEFAULT_VALUE);
		if(value.equals(DEFAULT_VALUE))//not exist
			return defaultValue;
		String strValue = decrypt(value);
		if(strValue == null)//decrypt error
			return defaultValue;
		int intValue = defaultValue;
		try {
			intValue = Integer.parseInt(strValue);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}

		return intValue;
	}

	public long getLong(String key) {
		return getLong(key, 0);
	}

	public long getLong(String key, long defaultValue) {
		if(mEncryptMode != MODE_ENCRYPT_ALL)
			return mPref.getLong(key, defaultValue);

		String value = mPref.getString(key, DEFAULT_VALUE);
		if(value.equals(DEFAULT_VALUE))//not exist
			return defaultValue;
		String strValue = decrypt(value);
		if(strValue == null)//decrypt error
			return defaultValue;
		long lValue = defaultValue;
		try {
			lValue = Long.parseLong(strValue);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}

		return lValue;
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if(mEncryptMode != MODE_ENCRYPT_ALL)
			return mPref.getBoolean(key, defaultValue);

		String value = mPref.getString(key, DEFAULT_VALUE);
		if(value.equals(DEFAULT_VALUE))//not exist
			return defaultValue;
		String strValue = decrypt(value);
		if(strValue == null)//decrypt error
			return defaultValue;
		boolean bValue = defaultValue;
		try {
			bValue = Boolean.parseBoolean(strValue);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}

		return bValue;
	}

	public boolean contains(String key) {
		return mPref.contains(key);
	}

	public void remove(String key) {
		Editor editor = mPref.edit();
		editor.remove(key);
		editor.commit();
	}

	public void clear() {
		Editor editor = mPref.edit();
		editor.clear();
		editor.commit();
	}

	private void initCiphers() {
		try {
			cipherWriter = Cipher.getInstance(TRANSFORMATION);
			cipherReader = Cipher.getInstance(TRANSFORMATION);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		IvParameterSpec ivSpec = getIv();
		SecretKeySpec secretKey = getSecretKey();

		try {
			cipherWriter.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
			cipherReader.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	private IvParameterSpec getIv() {
		byte[] iv = new byte[cipherWriter.getBlockSize()];
		System.arraycopy("gcQu4mcDjQkPjcX1YY2X6xNaWyiWF0dUNbA".getBytes(), 0, iv, 0, cipherWriter.getBlockSize());
		return new IvParameterSpec(iv);
	}

	private SecretKeySpec getSecretKey(){
		byte[] keyBytes = createKeyBytes(getEncryptedKey());
		return new SecretKeySpec(keyBytes, TRANSFORMATION);
	}

	private byte[] createKeyBytes(String key){
		byte[] keyBytes = "gcQu4mcDjQkPjcX1YY2X6xNaWyiWF0dD".getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance(SECRET_KEY_HASH_TRANSFORMATION);
			md.reset();
			keyBytes = md.digest(key.getBytes(CHARSET));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return keyBytes;
	}

	private String getEncryptedKey(){
		return STORAGE_ENCRYPTED_KEY;
	}

	protected synchronized String encrypt(String value){
		byte[] secureValue;
		String secureValueEncoded = null;
		try {
			secureValue = cipherWriter.doFinal(value.getBytes(CHARSET));
			secureValueEncoded = Base64.encodeToString(secureValue, Base64.NO_WRAP);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			initCiphers();
		} catch (BadPaddingException e) {
			e.printStackTrace();
			initCiphers();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			initCiphers();
		} catch (IllegalStateException e){
			e.printStackTrace();
			initCiphers();
		}

		return secureValueEncoded;
	}

	protected synchronized String decrypt(String securedEncodedValue) {
		String plainText = null;
		byte[] securedValue;
		byte[] value = new byte[0];

		try {
			securedValue = Base64.decode(securedEncodedValue, Base64.NO_WRAP);
			value = cipherReader.doFinal(securedValue);
			plainText = new String(value, CHARSET);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			initCiphers();
		} catch (BadPaddingException e) {
			e.printStackTrace();
			initCiphers();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			initCiphers();
		} catch (IllegalArgumentException e){
			e.printStackTrace();
			initCiphers();
		}

		return plainText;
	}
}
