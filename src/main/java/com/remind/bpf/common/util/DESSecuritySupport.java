
package com.remind.bpf.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.remind.bpf.common.security.DES;


/**
 * DESSecuritySupport
 * 
 * <P>
 * DES解密支持实现
 * </P>
 * 
 * @version 0.0.1
 */
public class DESSecuritySupport implements SecuritySupport
{
	// ----------------------------------------------------- Properties

	/**
	 * Log4j
	 */
	private static final Log log = LogFactory.getLog( DESSecuritySupport.class );

	/**
	 * 单例模式
	 */
	private static SecuritySupport _instance = new DESSecuritySupport();

	/**
	 * 匹配模式
	 */
	private final Pattern pattern;

	// ----------------------------------------------------- Constructors

	/**
	 * 构造普通解密支持
	 */
	private DESSecuritySupport()
	{
		pattern = Pattern.compile( DECRYPT_REGEX );
	}

	/**
	 * 获取引用
	 * 
	 * @return 实现类引用
	 */
	public static SecuritySupport getInstance()
	{
		return _instance;
	}

	// ----------------------------------------------------- Methods

	/**
	 * 加密属性值
	 * 
	 * @param sval 源属性值
	 * 
	 * @return 加密属性值
	 */
	public String encryptProperty( String sval )
	{
		return ENCRYPT_REGEX.replace( "*", DES.encrypt( sval ) );
	}

	/**
	 * 解密属性值
	 * 
	 * @param sval 源属性值
	 * 
	 * @return 解密属性值
	 */
	public String decryptProperty( String sval )
	{
		final Matcher matcher = pattern.matcher( sval );

		if ( matcher.find() )
		{
			sval = DES.decrypt( sval.substring( 5, sval.length() - 6 ) );
		}

		return sval;
	}

	/**
	 * 测试方法
	 * 
	 * @param args 参数列
	 */
	public static void main( String[] args )
	{
		String sval = "<pwd>habingsin</pwd>";

		log.debug( "1234567890:" + DES.encrypt( "1234567890" ) );
		log.debug( "abcdefghijklmnopqrstuvwxyz:" + DES.encrypt( "abcdefghijklmnopqrstuvwxyz" ) );
		log.debug( ",./<>?:" + DES.encrypt( ",./<>?" ) );
		log.debug( "~!@#$%^&*()_+`[]\\{}|'\":;:" + DES.encrypt( "~!@#$%^&*()_+`[]\\{}|'\":;" ) );

		String regex = "<pwd>.+</pwd>";
		Pattern pattern = Pattern.compile( regex );

		Matcher matcher = pattern.matcher( "<pwd>YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo=</pwd>" );
		log.debug( matcher.find() + "," + matcher.start() + "," + matcher.end() );

		matcher = pattern.matcher( "<pwd>habingsin</pwd>" );
		if ( matcher.find() )
		{
			log.debug( sval.substring( 5, sval.length() - 6 ) );
		}
//dbcp.user=bomc31_gz_dev1
	//	dbcp.password=992A6EEE0FB7F94934ECD46851D79F86
		DESSecuritySupport test = new DESSecuritySupport();
		String result = test.decryptProperty( "<pwd>B5690D5E6588678916811DC2E579EA08</pwd>" );
		log.debug( "decrypt:" + result );
		log.debug( "encrypt:" + test.encryptProperty( "asiainfo" ) );
		System.out.println(DES.encrypt("bomc31_gz_dev1"));
		System.out.println(DES.decrypt("bomc31_gz_dev1"));
	}
}