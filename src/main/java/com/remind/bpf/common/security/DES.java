
package com.remind.bpf.common.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES
 * 
 * <P>
 * DES加密
 * </P>
 * 
 * @version 0.0.1
 */
public abstract class DES
{
	// ----------------------------------------------------- Properties

	private static final String PASSWORD_CRYPT_KEY = "bpf";

	private final static String DES = "DES";

	// ----------------------------------------------------- Constructors

	/**
	 * 隐藏构造函数
	 */
	private DES()
	{
	}

	// ----------------------------------------------------- Methods

	/**
	 * 加密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	protected static byte[] encrypt( byte[] src, byte[] key ) throws Exception
	{
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec( key );

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( DES );
		SecretKey securekey = keyFactory.generateSecret( dks );

		Cipher cipher = Cipher.getInstance( DES );
		cipher.init( Cipher.ENCRYPT_MODE, securekey, sr );

		return cipher.doFinal( src );
	}

	/**
	 * 解密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	protected static byte[] decrypt( byte[] src, byte[] key ) throws Exception
	{
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec( key );

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( DES );
		SecretKey securekey = keyFactory.generateSecret( dks );

		Cipher cipher = Cipher.getInstance( DES );
		cipher.init( Cipher.DECRYPT_MODE, securekey, sr );

		return cipher.doFinal( src );
	}

	/**
	 * 密码解密
	 * 
	 * @param data 字符串
	 * @return 解密字符串 
	 */
	public final static String decrypt( String data )
	{
		try
		{
			return new String( decrypt( hex2byte( data.getBytes() ), PASSWORD_CRYPT_KEY.getBytes() ) );
		}
		catch ( Exception e )
		{
		}

		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password  字符串
	 * @return 加密字符串
	 */
	public final static String encrypt( String password )
	{
		try
		{
			return byte2hex( encrypt( password.getBytes(), PASSWORD_CRYPT_KEY.getBytes() ) );
		}
		catch ( Exception e )
		{
		}

		return null;
	}

	/**
	 * 转换为16进制
	 * 
	 * @param b
	 * @return 描述
	 */
	protected static String byte2hex( byte[] b )
	{
		String hs = "";
		String stmp = "";

		for ( int n = 0; n < b.length; n++ )
		{
			stmp = ( Integer.toHexString( b[n] & 0XFF ) );

			if ( stmp.length() == 1 )
			{
				hs = hs + "0" + stmp;
			}
			else
			{
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	/**
	 * 十六进制转字节
	 * 
	 * @param b 十六进制
	 * @return 字节
	 */
	protected static byte[] hex2byte( byte[] b )
	{
		if ( ( b.length % 2 ) != 0 )
		{
			throw new IllegalArgumentException( "des convert has a error." );
		}

		byte[] b2 = new byte[b.length / 2];

		for ( int n = 0; n < b.length; n += 2 )
		{
			String item = new String( b, n, 2 );
			b2[n / 2] = (byte) Integer.parseInt( item, 16 );
		}

		return b2;
	}
}
