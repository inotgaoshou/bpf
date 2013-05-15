
package com.remind.bpf.common.util;

import java.util.Properties;

/**
 * SecurityProperty
 * 
 * <P>
 * 支持加密的.properties配置文件
 * </P>
 * 
 * <P>
 * 加密规则同AppFrame类似:满足<pwd>密文</pwd>
 * <P>
 * 
 * @version 0.0.1
 */
public class SecurityProperties extends Properties
{
	// ----------------------------------------------------- Properties

	/**
	 * 解密支持
	 */
	private SecuritySupport securitySupport = DESSecuritySupport.getInstance();

	// ----------------------------------------------------- Constructors

	// ----------------------------------------------------- Methods

	/**
	 * 覆写读取加密的字段
	 * 
	 * @param key 字段名
	 * 
	 * @return 源值
	 */
	public String getProperty( String key )
	{
		Object oval = super.get( key );
		String sval = ( oval instanceof String ) ? (String) oval : null;

		if ( ( sval == null ) && ( defaults != null ) )
		{
			sval = defaults.getProperty( key );
		}

		return securitySupport.decryptProperty( sval );
	}
}
