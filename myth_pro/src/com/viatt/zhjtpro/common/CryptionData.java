package com.viatt.zhjtpro.common;

import javax.crypto.*;
import javax.crypto.spec.*;

import sun.misc.*;

/**
 * 
 * <p>
 * Title:�ַ����
 * </p>
 * <p>
 * Description: ���ַ���м��ܽ��ܲ���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 *   
 * @author ������ 2005��9��7��
 * @version 1.0
 */
public class CryptionData
{
	public static final String INIT_PWD = "888888";

	private String EncryptionString = "www.ccb.com.cn";

	// ��ʸ������Ҫ8�ֽڣ����ɱ����Ա�Լ�����
	private final byte[] EncryptionIV = "ABCDEFGH".getBytes();

	// �������ܱ�׼
	private final static String DES = "DES/CBC/PKCS5Padding";

	public CryptionData()
	{
	}

	public CryptionData(String EncryptionString)
	{
		this.EncryptionString = EncryptionString;
	}

	/**
	 * ����һ���ֽ�����
	 * 
	 * @param ԭ����
	 *            byte[]
	 * @throws Exception
	 * @return ���ܺ������ byte[]
	 */
	public byte[] EncryptionByteData(byte[] SourceData) throws Exception
	{
		byte[] retByte = null;

		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);

		retByte = cipher.doFinal(SourceData);
		return retByte;
	}

	/**
	 * ����һ���ֽ�����
	 * 
	 * @param ԭ���
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public byte[] DecryptionByteData(byte[] SourceData) throws Exception
	{
		byte[] retByte = null;

		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.DECRYPT_MODE, securekey, spec);

		retByte = cipher.doFinal(SourceData);

		return retByte;
	}

	/**
	 * ����һ���ַ�
	 * 
	 * @param ԭ���
	 *            String
	 * @throws Exception
	 * @return ���ܺ���ַ� String
	 */
	public String EncryptionStringData(String SourceData) throws Exception
	{
		String retStr = null;
		byte[] retByte = null;

		byte[] sorData = SourceData.getBytes();

		retByte = EncryptionByteData(sorData);

		BASE64Encoder be = new BASE64Encoder();
		retStr = be.encode(retByte);

		return retStr;
		//return SourceData;
	}

	/**
	 * �ַ���ܵķ���
	 * 
	 * @param ԭ���ܺ���ַ�
	 *            String
	 * @throws Exception
	 * @return ���ܺ���ַ� String
	 */
	public String DecryptionStringData(String SourceData) throws Exception
	{
		String retStr = null;
		byte[] retByte = null;

		BASE64Decoder bd = new BASE64Decoder();
		byte[] sorData = bd.decodeBuffer(SourceData);
		retByte = DecryptionByteData(sorData);
		retStr = new String(retByte);
		return retStr;
		//return SourceData;
	}

}
