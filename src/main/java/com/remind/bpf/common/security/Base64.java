
package com.remind.bpf.common.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;

/**
 * Base64
 * 
 * <P>
 * 对字符串的编码及解码
 * </P>
 * 
 * @version 0.0.1
 */
public abstract class Base64
{
	// ----------------------------------------------------- Properties

	/**
	 * Log4j
	 */
	private static final Log log = LogFactory.getLog( Base64.class );

	// ----------------------------------------------------- Constructors

	/**
	 * 隐藏构造函数防止实例化
	 */
	private Base64()
	{
	}

	// ----------------------------------------------------- Methods

	/**
	 * 对字符串进行Base64编码
	 * 
	 * @param srcStr 源字符串
	 * @return Base64编码字符串
	 */
	public static String encode( String srcStr )
	{
		if ( srcStr == null )
		{
			throw new NullPointerException( "the base64 encode src string is not be null" );
		}

		return ( new sun.misc.BASE64Encoder() ).encode( srcStr.getBytes() );
	}

	/**
	 * 对Base64编码字符串进行解码
	 * 
	 * @param srcStr Base64编码字符串
	 * @return 源字符串
	 */
	public static String decode( String srcStr )
	{
		if ( srcStr == null )
		{
			throw new NullPointerException( "the base64 decode src string is not be null" );
		}

		final BASE64Decoder decoder = new BASE64Decoder();

		try
		{
			byte[] b = decoder.decodeBuffer( srcStr );
			return new String( b );
		}
		catch ( Exception e )
		{
			if ( log.isErrorEnabled() )
			{
				log.error( "has a error during base64 decode:" + e.getMessage(), e );
			}

			throw new IllegalStateException( "has a error during base64 decode:", e );
		}
	}
}
